package me.jangofetthd.try2think;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex.metrica.YandexMetrica;

public class Select_Lvl extends AppCompatActivity {
    public static Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16;
    private TextView view_levels,view_coins;
    public static int what;
    public static Button[] buttons = {b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16};
    public static int[] buttonsR = {R.id.b1,R.id.b2,R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7,R.id.b8,R.id.b9,R.id.b10,R.id.b11,R.id.b12,R.id.b13,R.id.b14,R.id.b15,R.id.b16};
    private int section_coins[] ={
            MainActivity.levels_ch1,
            MainActivity.levels_ch2,
            MainActivity.levels_ch3,
            MainActivity.levels_ch4,
            MainActivity.levels_ch5,
            MainActivity.levels_ch6,
            MainActivity.levels_ch7,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_section1_select);

        view_levels = (TextView)findViewById(R.id.levels);
        view_coins = (TextView)findViewById(R.id.coins);
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_summ));

        if (section_coins[Select_Lvl.what-1] == 16) {
            Toast.makeText(this, "Вы прошли раздел полностью!", Toast.LENGTH_SHORT).show();
        }
//        onResume();
    }
    protected void onResume() {
        super.onResume();
        YandexMetrica.onResumeActivity(this);
        DatabaseHelper.loadStateFromDatabase();
        for (int i=0; i<16; i++){
            lvl[] arrs = {DataBase.section1[i],DataBase.section2[i],DataBase.section3[i],DataBase.section4[i],DataBase.section5[i],DataBase.section6[i],DataBase.section7[i]};
            if (arrs[what-1].status == 1) {
                buttons[i]=(Button)findViewById(buttonsR[i]);
                buttons[i].setBackgroundResource(R.drawable.rectangle_rounded_all_complete);
            }
            if (arrs[what-1].lock!=0){
                buttons[i]=(Button)findViewById(buttonsR[i]);
                buttons[i].setBackgroundResource(R.drawable.rectangle_rounded_all_lock);
            }


        }
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_summ));
    }
    protected void onPause() {
        YandexMetrica.onPauseActivity(this);
        super.onPause();

    }
    public void onClickmenu(View v) {
        switch (v.getId()) {
            case R.id.b_menu:
                finish();
                break;
            case R.id.b_help:
                Toast.makeText(this, "Пройденные Вами уровни зеленого цвета", Toast.LENGTH_LONG).show();
                break;
        }
    }
    public void onClick(View v) {
                    Intent goLvL = new Intent(Select_Lvl.this, CreatorLvls.class);
                    for (int i=0; i<16; i++) {
                        lvl[] arrs = {DataBase.section1[i],DataBase.section2[i],DataBase.section3[i],DataBase.section4[i],DataBase.section5[i],DataBase.section6[i],DataBase.section7[i]};
                        if (v.getId() == buttonsR[i]) {
                            if (arrs[what-1].lock!=1){
                            CreatorLvls.i = i;
                            startActivity(goLvL);
                            } else Toast.makeText(this, "Будет доступно в следующем обновлении!", Toast.LENGTH_LONG).show();
                        }
                    }

    }
}

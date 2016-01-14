package me.jangofetthd.try2think;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex.metrica.YandexMetrica;

public class SelectSection extends AppCompatActivity {


    private TextView view_levels;
    private TextView view_coins;
    private static int sections_text[]={
            R.id.section1_text,
            R.id.section2_text,
            R.id.section3_text,
            R.id.section4_text,
            R.id.section5_text,
            R.id.section6_text,
            R.id.section7_text,
    };
    private int sections[] = {
            R.id.section1,
            R.id.section2,
            R.id.section3,
            R.id.section4,
            R.id.section5,
            R.id.section6,
            R.id.section7
    };
    private static int images_ids[] = {
            R.drawable.ss_razdel1,
            R.drawable.ss_razdel2,
            R.drawable.ss_razdel3,
            R.drawable.ss_razdel4,
            R.drawable.ss_razdel5,
            R.drawable.ss_razdel6,
            R.drawable.ss_razdel_wip,
    };
    private int section_coins1[] ={
            MainActivity.levels_ch1,
            MainActivity.levels_ch2,
            MainActivity.levels_ch3,
            MainActivity.levels_ch4,
            MainActivity.levels_ch5,
            MainActivity.levels_ch6,
            MainActivity.levels_ch7,
    };
    private String section_name[]={
            "Уровень 1",
            "Уровень 2",
            "Информатика",
            "История",
            "Досуг",
            "Профессии",
            "Пасха",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_select_ch1);
        getWindow().setBackgroundDrawable(null);    //Decrease redrawing
        view_levels = (TextView) findViewById(R.id.levels);
        view_coins = (TextView) findViewById(R.id.coins);

    }

    @Override
    protected void onResume() {
        super.onResume();
        YandexMetrica.onResumeActivity(this);
        new AppRate(this)
                //.setMinDaysUntilPrompt(7)
                .setMinLaunchesUntilPrompt(11)
                //.setShowIfAppHasCrashed(false)
                .init();

        section_coins1 = new int[]{
                MainActivity.levels_ch1,
                MainActivity.levels_ch2,
                MainActivity.levels_ch3,
                MainActivity.levels_ch4,
                MainActivity.levels_ch5,
                MainActivity.levels_ch6,
                MainActivity.levels_ch7,
        };
        for (int a=0; a<sections.length; a++){

            ((ImageView)findViewById(sections[a])).setImageDrawable(getResources().getDrawable(images_ids[a]));
            ((TextView)findViewById(sections_text[a])).setText(section_name[a]+ "\nПройдено " + Integer.toString(section_coins1[a]));
        }


        updateVar();
    }

    public void updateVar() {

        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_summ));

    }

    @Override
    protected void onPause() {
        YandexMetrica.onPauseActivity(this);
        super.onPause();
        updateVar();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_menu:
                Intent goMenu = new Intent(SelectSection.this, MainActivity.class);
                startActivity(goMenu);
                break;
            case R.id.b_help:
                Toast.makeText(this, "Выберите раздел", Toast.LENGTH_LONG).show();
                break;
            case R.id.section1:
                Select_Lvl.what = 1;
                Intent goSec1 = new Intent(SelectSection.this, Select_Lvl.class);
                startActivity(goSec1);
                break;
            case R.id.section2:
                if (MainActivity.levels_summ >= 10) {
                    //Toast.makeText(this, "В разработке...", Toast.LENGTH_LONG).show();
                    Select_Lvl.what = 2;
                    Intent goSec2 = new Intent(SelectSection.this, Select_Lvl.class);
                    startActivity(goSec2);
                } else
                    Toast.makeText(this, "Чтобы открыть этот раздел вы должны пройти 10 уровней", Toast.LENGTH_LONG).show();
                break;
            case R.id.section3:
                if (MainActivity.levels_summ >= 24) {
                    //Toast.makeText(this, "В разработке...", Toast.LENGTH_LONG).show();
                    Select_Lvl.what = 3;
                    Intent goSec3 = new Intent(SelectSection.this, Select_Lvl.class);
                    startActivity(goSec3);
                } else
                    Toast.makeText(this, "Чтобы открыть этот раздел вы должны пройти 24 уровня", Toast.LENGTH_LONG).show();
                break;
            case R.id.section4:
                if (MainActivity.levels_summ >= 40) {
                    //Toast.makeText(this, "В разработке...", Toast.LENGTH_LONG).show();
                    Select_Lvl.what = 4;
                    Intent goSec4 = new Intent(SelectSection.this, Select_Lvl.class);
                    startActivity(goSec4);
                } else
                    Toast.makeText(this, "Чтобы открыть этот раздел вы должны пройти 45 уровней", Toast.LENGTH_LONG).show();
                break;
            case R.id.section5:
                if (MainActivity.levels_summ>=56){
                    //Toast.makeText(this, "В разработке...", Toast.LENGTH_LONG).show();
                    Select_Lvl.what = 5;
                    Intent goSec5 = new Intent(SelectSection.this, Select_Lvl.class);
                    startActivity(goSec5);
                    }else Toast.makeText(this, "Чтобы открыть этот раздел вы должны пройти 56 уровней", Toast.LENGTH_LONG).show();
                break;
            case R.id.section6:
                if (MainActivity.levels_summ>=72){
                    Toast.makeText(this, "В разработке...", Toast.LENGTH_LONG).show();
                    Select_Lvl.what = 6;
                    Intent goSec6 = new Intent(SelectSection.this, Select_Lvl.class);
                    startActivity(goSec6);
                    }else Toast.makeText(this, "Чтобы открыть этот раздел вы должны пройти 72 уровня", Toast.LENGTH_LONG).show();
                break;
            case R.id.section7:
                //if (MainActivity.levels_summ>=90){
                Toast.makeText(this, "Будет доступно в одном из следующих обновлений", Toast.LENGTH_LONG).show();
                /*Select_Lvl.what = 7;
                Intent goSec7 = new Intent(SelectSection.this, Select_Lvl.class);
                startActivity(goSec7);*/
                //}else Toast.makeText(this, "Чтобы открыть этот раздел вы должны пройти 90 уровней", Toast.LENGTH_LONG).show();
                break;
        }
    }

}

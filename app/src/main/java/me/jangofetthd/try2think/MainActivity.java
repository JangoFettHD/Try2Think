package me.jangofetthd.try2think;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yandex.metrica.YandexMetrica;

public class MainActivity extends AppCompatActivity {

    public static int levels_ch1=0;
    public static int levels_ch2=0;
    public static int levels_ch3=0;
    public static int levels_ch4=0;
    public static int levels_ch5=0;
    public static int levels_ch6=0;
    public static int levels_ch7=0;
    public static int levels_summ=levels_ch1+levels_ch2+levels_ch3+levels_ch4+levels_ch5+levels_ch6+levels_ch7;
    public static int levels;
    public static int coins=25;
    private TextView view_levels;
    private TextView view_coins;

    private static MainActivity instance;

    private static DatabaseHelper mStateDatabase;
    private static SQLiteDatabase sdb;

    public static final String Podymai_Settings = "podymai";
    public static final String Podymai_Settings_coins = "coins";
    public static final String Podymai_Settings_lvlsdb = "lvlsdb";
    public static final String Podymai_Settings_levels_ch1 = "levels_ch1";
    public static final String Podymai_Settings_levels_ch2 = "levels_ch2";
    public static final String Podymai_Settings_levels_ch3 = "levels_ch3";
    public static final String Podymai_Settings_levels_ch4 = "levels_ch4";
    public static final String Podymai_Settings_levels_ch5 = "levels_ch5";
    public static final String Podymai_Settings_levels_ch6 = "levels_ch6";
    public static final String Podymai_Settings_levels_ch7 = "levels_ch7";
    public static SharedPreferences P_Main_settings;


    @Override
    protected void onPause() {
        YandexMetrica.onPauseActivity(this);
        super.onPause();
        saveSettings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        YandexMetrica.onResumeActivity(this);
        loadSettings();
        view_coins.setText(Integer.toString(coins));
        view_levels.setText(Integer.toString(levels_summ));

    }



    public static void saveSettings() {
        SharedPreferences.Editor editor = P_Main_settings.edit();
        editor.putInt(Podymai_Settings_coins, coins);
        editor.putInt(Podymai_Settings_levels_ch1, levels_ch1);
        editor.putInt(Podymai_Settings_levels_ch2, levels_ch2);
        editor.putInt(Podymai_Settings_levels_ch3, levels_ch3);
        editor.putInt(Podymai_Settings_levels_ch4, levels_ch4);
        editor.putInt(Podymai_Settings_levels_ch5, levels_ch5);
        editor.putInt(Podymai_Settings_levels_ch6, levels_ch6);
        editor.putInt(Podymai_Settings_levels_ch7, levels_ch7);
        //editor.putInt(Podymai_Settings_lvlsdb, (DataBase.templvl[PlayCompany.i].status));
        editor.apply();
    }


    public static void loadSettings() {
        if (P_Main_settings.contains(Podymai_Settings_coins) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch1) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch2) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch3) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch4) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch5) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch6) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch7) ||
                P_Main_settings.contains(Podymai_Settings_lvlsdb)) {
            coins = P_Main_settings.getInt(Podymai_Settings_coins, 0);
            levels_ch1 = P_Main_settings.getInt(Podymai_Settings_levels_ch1, 0);
            levels_ch2 = P_Main_settings.getInt(Podymai_Settings_levels_ch2, 0);
            levels_ch3 = P_Main_settings.getInt(Podymai_Settings_levels_ch3, 0);
            levels_ch4 = P_Main_settings.getInt(Podymai_Settings_levels_ch4, 0);
            levels_ch5 = P_Main_settings.getInt(Podymai_Settings_levels_ch5, 0);
            levels_ch6 = P_Main_settings.getInt(Podymai_Settings_levels_ch6, 0);
            levels_ch7 = P_Main_settings.getInt(Podymai_Settings_levels_ch7, 0);
            //DataBase.templvl[PlayCompany.i].status = P_Main_settings.getInt(Podymai_Settings_lvlsdb, 0);
            levels_summ = levels_ch1 + levels_ch2 + levels_ch3+levels_ch4 + levels_ch5 + levels_ch6 + levels_ch7;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        instance = this;
        setContentView(R.layout.activity_main);
// Инициализация AppMetrica SDK
        YandexMetrica.activate(getApplicationContext(), "da10a337-b22f-43ac-891f-ecd4411b5427");
        // Отслеживание активности пользователей

        mStateDatabase = new DatabaseHelper(this);
        sdb = mStateDatabase.getReadableDatabase();

        view_levels = (TextView)findViewById(R.id.levels);
        view_coins = (TextView)findViewById(R.id.coins);

        P_Main_settings = getSharedPreferences(Podymai_Settings, Context.MODE_PRIVATE);
        levels_summ=levels_ch1+levels_ch2+levels_ch3+levels_ch4 + levels_ch5 + levels_ch6 + levels_ch7;
        view_coins.setText(Integer.toString(coins));
        view_levels.setText(Integer.toString(levels_summ));

    }

    public void hack(View view) {
        Toast.makeText(this, ":-)!", Toast.LENGTH_SHORT).show();
        coins=+1000;
        levels_ch1=16;
        levels_ch2=16;
        levels_ch3=16;
        levels_ch4=16;
        levels_ch5=16;
        levels_ch6=16;
        levels_ch7=16;

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b_play:
                Intent goPlay = new Intent(MainActivity.this, SelectSection.class);
                startActivity(goPlay);
                break;
            case R.id.b_exit:
                moveTaskToBack(true);
                super.onDestroy();
                System.runFinalizersOnExit(true);
                System.exit(0);
                break;
            case R.id.version:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://jangofetthd.me"));
                startActivity(browserIntent);
                break;
            case R.id.b_magazine:
                Intent goTrain = new Intent(MainActivity.this, Training.class);
                startActivity(goTrain);
                break;
            case R.id.b_settings:
                String text = "Привет!\nЯ прошёл " + levels_summ + " уровней и мой счёт составил "
                        +coins+"!"+"\nОсмелишься ли ты бросить мне вызов в игре \"Подумай\"?\nСкачать: jangofetthd.me";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(intent, "Share with"));
                int sdk_Version = android.os.Build.VERSION.SDK_INT;
                if(sdk_Version < android.os.Build.VERSION_CODES.HONEYCOMB) {
                    android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboard.setText(text);   // Assuming that you are copying the text from a TextView
                    Toast.makeText(getApplicationContext(), "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
                }
                else {
                    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    android.content.ClipData clip = android.content.ClipData.newPlainText("Text Label", text);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "Текст скопирован в буфер!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public static Context getContext(){
        return instance;
    }
}

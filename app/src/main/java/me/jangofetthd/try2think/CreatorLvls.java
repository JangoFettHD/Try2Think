package me.jangofetthd.try2think;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bettervectordrawable.VectorDrawableCompat;

import com.yandex.metrica.YandexMetrica;

import java.util.Arrays;

public class CreatorLvls extends AppCompatActivity {

    //помощь
    private FrameLayout help_window;
    private String text_help="75$";
    private String text_answer="250$";
    private TextView view_levels;
    private TextView view_coins;
    private TextView helpw_text;
    private Button helpw_b_back;
    private Button helpw_b_help;
    private Button helpw_b_answer;

    //картинка ребуса
    public static ImageView imageView;

    //номер уровня из 16
    public static int i=0;

    //для циклов
    public lvl[] arrs = {DataBase.section1[i],DataBase.section2[i],DataBase.section3[i],DataBase.section4[i],DataBase.section5[i],DataBase.section6[i],DataBase.section7[i]};
    private int section_coins[] ={
            MainActivity.levels_ch1,
            MainActivity.levels_ch2,
            MainActivity.levels_ch3,
            MainActivity.levels_ch4,
            MainActivity.levels_ch5,
            MainActivity.levels_ch6,
            MainActivity.levels_ch7,
    };

    //меню сверху
    private Button b_back;
    private static EditText vvod;
    private Button b_next;

    @Override
    protected void onPause() {
        YandexMetrica.onPauseActivity(this);
        super.onPause();
        help_window.setVisibility(View.INVISIBLE);
        DatabaseHelper.saveStateToDatabase();

        if(imageView != null) {
            //((BitmapDrawable)imageView.getDrawable()).getBitmap().recycle();
            imageView.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_play_company);

        help_window = (FrameLayout)findViewById(R.id.help_window);
        imageView = (ImageView)findViewById(R.id.imageView);
        view_levels = (TextView)findViewById(R.id.levels);
        view_coins = (TextView)findViewById(R.id.coins);
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_summ));
        helpw_b_back = (Button) findViewById(R.id.helpw_hide);
        helpw_b_help = (Button) findViewById(R.id.helpw_help);
        helpw_b_answer = (Button) findViewById(R.id.helpw_answer);
        helpw_text = (TextView)findViewById(R.id.helpw_text);
        b_back = (Button)findViewById(R.id.b_menu);
        vvod = (EditText)findViewById(R.id.vvod);
        b_next = (Button)findViewById(R.id.b_next);

        //если уровень пройден
        if (arrs[Select_Lvl.what-1].status==1) {
            help_window.setVisibility(View.INVISIBLE);
            text_help = new String (arrs[Select_Lvl.what-1].help);
            text_answer = new String(arrs[Select_Lvl.what-1].correctAnswer);
            vvod.setKeyListener(null);
            vvod.setGravity(Gravity.CENTER);
            vvod.setText(arrs[Select_Lvl.what - 1].correctAnswer);
            b_next.setVisibility(View.INVISIBLE);
            b_next.setEnabled(false);
        }



        //для svg
        /*int[] ids = VectorDrawableCompat.findAllVectorResourceIdsSlow(getResources(), R.drawable.class);
        VectorDrawableCompat.enableResourceInterceptionFor(getResources(), ids);*/

        //Drawable drawable = getResources().getDrawable(R.drawable.cloud2);
        //imageView.setBackground(getResources().getDrawable(R.drawable.cloud2));
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        YandexMetrica.onResumeActivity(this);
        super.onResume();
        help_window.setVisibility(View.INVISIBLE);
        text_help=new String("75$");
        text_answer=new String("250$");
        helpw_b_help.setVisibility(View.VISIBLE);
        helpw_b_answer.setVisibility(View.VISIBLE);
        helpw_text.setText("Подсказка: " + text_help + "\nОтвет: " + text_answer);


        if (i != 17) {
//            if (imageView != null)
//                ((BitmapDrawable)imageView.getDrawable()).getBitmap().recycle();
            imageView.setImageDrawable(getResources().getDrawable(arrs[Select_Lvl.what - 1].image));
            //imageView.setImageBitmap(decodeResource(getResources(), arrs[Select_Lvl.what-1].image));
        }
        DatabaseHelper.loadStateFromDatabase();


    }


    public void win(){
        //определяем куда зачислить очки
        if (((arrs[Select_Lvl.what-1]).chapter)==1){MainActivity.levels_ch1 +=1;}
        if (((arrs[Select_Lvl.what-1]).chapter)==2){MainActivity.levels_ch2 +=1;}
        if (((arrs[Select_Lvl.what-1]).chapter)==3){MainActivity.levels_ch3 +=1;}
        if (((arrs[Select_Lvl.what-1]).chapter)==4){MainActivity.levels_ch4 +=1;}
        if (((arrs[Select_Lvl.what-1]).chapter)==5){MainActivity.levels_ch5 +=1;}
        if (((arrs[Select_Lvl.what-1]).chapter)==6){MainActivity.levels_ch6 +=1;}
        if (((arrs[Select_Lvl.what-1]).chapter)==7){MainActivity.levels_ch7 +=1;}

        //зачисляем$ы
        if (arrs[Select_Lvl.what-1].helpCount !=0){Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();} else {
            if (arrs[Select_Lvl.what - 1].tryCount == 0) {
                MainActivity.coins += 50;
                Toast.makeText(this, "Правильно! +50$!", Toast.LENGTH_SHORT).show();
            }
            if (arrs[Select_Lvl.what - 1].tryCount == 1) {
                MainActivity.coins += 25;
                Toast.makeText(this, "Правильно! +25$!", Toast.LENGTH_SHORT).show();
            }
            if (arrs[Select_Lvl.what - 1].tryCount > 1) {
                MainActivity.coins += 10;
                Toast.makeText(this, "Правильно! +10$!", Toast.LENGTH_SHORT).show();
            }
        }
        //присваиваем статус пройденного уровня
        arrs[Select_Lvl.what-1].status=1;
        //arrs[Select_Lvl.what-1].tryCount =0;

        //сохраняем$ы и очки
        MainActivity.saveSettings();
        MainActivity.loadSettings();

        //переходим на следующий уровень
        i++;
        if (i < 16) {
            arrs = new lvl[]{DataBase.section1[i], DataBase.section2[i], DataBase.section3[i], DataBase.section4[i], DataBase.section5[i], DataBase.section6[i], DataBase.section7[i]};
            if (arrs[Select_Lvl.what - 1].lock != 1) {
                if (i < 16) {
                    arrs = new lvl[]{DataBase.section1[i], DataBase.section2[i], DataBase.section3[i], DataBase.section4[i], DataBase.section5[i], DataBase.section6[i], DataBase.section7[i]};
                    imageView.setImageResource((arrs[Select_Lvl.what - 1]).image);
                    vvod.setText("");
                }

            } else i--;
        }
        if (i < 16) {arrs = new lvl[]{DataBase.section1[i], DataBase.section2[i], DataBase.section3[i], DataBase.section4[i], DataBase.section5[i], DataBase.section6[i], DataBase.section7[i]};}
        //выводим на экран$ы и очки
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_summ));

        //сохраняем данные
        DatabaseHelper.saveStateToDatabase();

        //если пройдены все уровни в разделе
        section_coins = new int[]{
                MainActivity.levels_ch1,
                MainActivity.levels_ch2,
                MainActivity.levels_ch3,
                MainActivity.levels_ch4,
                MainActivity.levels_ch5,
                MainActivity.levels_ch6,
                MainActivity.levels_ch7,
        };
        if (section_coins[Select_Lvl.what-1]==16) {
            Toast.makeText(this, "Поздравляем! Вы прошли весь раздел!", Toast.LENGTH_SHORT).show();
            Intent goMenu = new Intent(CreatorLvls.this, SelectSection.class);
            startActivity(goMenu);
        }

        //слов нет
        text_help=new String("75$");
        text_answer=new String("250$");
        helpw_b_help.setVisibility(View.VISIBLE);
        helpw_b_answer.setVisibility(View.VISIBLE);
        helpw_text.setText("Подсказка: " + text_help+"\nОтвет: " + text_answer);

        if (arrs[Select_Lvl.what-1].status==1){
            text_help = new String (arrs[Select_Lvl.what-1].help);
            text_answer = new String(arrs[Select_Lvl.what-1].correctAnswer);
            helpw_b_back.setVisibility(View.INVISIBLE);
            helpw_b_help.setVisibility(View.INVISIBLE);
            helpw_b_answer.setVisibility(View.INVISIBLE);
            vvod.setKeyListener(null);
            vvod.setGravity(Gravity.CENTER);
            vvod.setText(arrs[Select_Lvl.what - 1].correctAnswer);
            b_next.setVisibility(View.INVISIBLE);
            b_next.setEnabled(false);
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            //следующий уровень
            case R.id.b_next:
                if (arrs[Select_Lvl.what-1].status==1){Toast.makeText(this, "Вы проходили этот уровень", Toast.LENGTH_LONG).show(); this.finish();}else
                if (Arrays.asList((arrs[Select_Lvl.what-1]).answer).contains(vvod.getText().toString().replaceAll(" ", "").toLowerCase().replaceAll("ё", "е").replaceAll("-", "").replaceAll("й", "и"))) {
                    win();
                }else if (vvod.getText().length() == 0) {
                    Toast.makeText(this, "Введи ответ!", Toast.LENGTH_LONG).show();
                } else {
                    arrs[Select_Lvl.what-1].tryCount +=1;
                    Toast.makeText(this, "Неправильно! Подумай еще!", Toast.LENGTH_LONG).show();
                }
                break;
            //скрыть помощь
            case R.id.helpw_hide:
                help_window.setVisibility(View.INVISIBLE);
                break;
            //кнопка "подсказка" в помощи
            case R.id.helpw_help:
                if (MainActivity.coins - 75 <0){
                    Toast.makeText(getApplicationContext(), "Недостаточно$!", Toast.LENGTH_SHORT).show();
                }else {
                    helpw_b_help.setVisibility(View.INVISIBLE);
                    MainActivity.coins -= 75;
                    //сохраняем покупку
                    (arrs[Select_Lvl.what-1]).helpCount=1;
                    //выводим новые данные на экран
                    view_coins.setText(Integer.toString(MainActivity.coins));
                    text_help=((arrs[Select_Lvl.what-1]).help);
                    helpw_text.setText("Подсказка: " + text_help + "\nОтвет: " + text_answer);
                    DatabaseHelper.saveStateToDatabase();
                    MainActivity.saveSettings();
                }
                break;
            //кнопка "ответ" в помощи
            case R.id.helpw_answer:
                if (MainActivity.coins - 250 <0){
                    Toast.makeText(getApplicationContext(), "Недостаточно$!", Toast.LENGTH_SHORT).show();
                }else {
                    helpw_b_help.setVisibility(View.INVISIBLE);
                    helpw_b_answer.setVisibility(View.INVISIBLE);
                    MainActivity.coins -= 250;
                    //сохраняем покупку
                    (arrs[Select_Lvl.what-1]).helpCount=2;
                    //выводим новые данные на экран
                    view_coins.setText(Integer.toString(MainActivity.coins));
                    text_answer=((arrs[Select_Lvl.what-1]).correctAnswer);
                    text_help=((arrs[Select_Lvl.what-1]).help);
                    helpw_text.setText("Подсказка: " + text_help + "\nОтвет: " + text_answer);
                    //сохраняем$ы и покупки
                    MainActivity.saveSettings();
                    DatabaseHelper.saveStateToDatabase();
                }
                break;
            //кнопка назад
            case R.id.b_menu:
                super.onStop();
                onStop();
                finish();
                break;
            //если нажать вне помощи
            case R.id.help_window:
                help_window.setVisibility(View.INVISIBLE);
                break;
            //кнопка открытия помощи
            case R.id.b_help:
                //проверка статуса уровня
                if (arrs[Select_Lvl.what-1].status==1){
                    Toast.makeText(this, "Вы прошли этот уровень!", Toast.LENGTH_SHORT).show();
                } else {

                    //если куплена подсказка
                    if (((arrs[Select_Lvl.what - 1]).helpCount) == 1) {
                        helpw_b_help.setVisibility(View.INVISIBLE);
                        text_help = ((arrs[Select_Lvl.what - 1]).help);
                        helpw_text.setText("Подсказка: " + text_help + "\nОтвет: " + text_answer);
                    }
                    //если куплен ответ
                    if (((arrs[Select_Lvl.what - 1]).helpCount) == 2) {
                        helpw_b_help.setVisibility(View.INVISIBLE);
                        helpw_b_answer.setVisibility(View.INVISIBLE);
                        text_answer = ((arrs[Select_Lvl.what - 1]).correctAnswer);
                        text_help = ((arrs[Select_Lvl.what - 1]).help);
                        helpw_text.setText("Подсказка: " + text_help + "\nОтвет: " + text_answer);
                    }
                    //показать помощь
                    help_window.setVisibility(View.VISIBLE);
                }
                break;
        }
    }


    private static Bitmap decodeResource(Resources res, int id) {
        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        for (options.inSampleSize = 1; options.inSampleSize <= 32; options.inSampleSize++) {
            try {
                bitmap = BitmapFactory.decodeResource(res, id, options);
                Log.d("Lololo", "Decoded successfully for sampleSize " + options.inSampleSize);
                break;
            } catch (OutOfMemoryError outOfMemoryError) {
                // If an OutOfMemoryError occurred, we continue with for loop and next inSampleSize value
                Log.e("Lololo", "outOfMemoryError while reading file for sampleSize " + options.inSampleSize
                        + " retrying with higher value");
            }
        }
        return bitmap;
    }


}

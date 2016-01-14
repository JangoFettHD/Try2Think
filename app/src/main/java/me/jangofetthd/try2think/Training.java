package me.jangofetthd.try2think;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Training extends AppCompatActivity {
ImageView view;
    TextView num_REB, name_REB, descrip_REB;
    private TextView view_levels;
    private TextView view_coins;
    private Button next;
    private Button prev;
    public int z=0;
    public static int[] images = {R.drawable.prim1,R.drawable.prim2,R.drawable.prim3,R.drawable.prim206,R.drawable.prim4,
            R.drawable.prim5,R.drawable.prim207,R.drawable.prim208,R.drawable.prim209,R.drawable.prim210,R.drawable.prim211,
            R.drawable.prim212,R.drawable.prim213,R.drawable.prim214,R.drawable.prim215,R.drawable.prim216,R.drawable.prim217,
            R.drawable.prim218,R.drawable.prim219,R.drawable.prim220,R.drawable.prim221,R.drawable.prim222,R.drawable.prim223,
            R.drawable.prim224,R.drawable.prim225,R.drawable.prim226,R.drawable.prim227,R.drawable.prim228};

    public String[] nameREB = {"ОТ И ДО", "СУХОВ", "КОСТЬ", "ГРИБЫ", "РЫБА или АКУЛА", "СОН", "ОЧКИ", "ЯД", "СОН",
            "ИРА", "\"МРАК\" или \"МАРК\"", "ВОЛЯ", "НЕБО", "ВИД", "ЗНАК", "ЗАЯЦ", "ШПРИЦ", "БИЗЕ", "ПОЯС", "СОТА", "ПАРАД",
            "ПОЛК", "ДОМ", "СИНЬ", "ШУБА", "БАБКА", "БРИГ", "АРБУЗ"};

    public String[] descREB = {
            "Знаки препинания и пробелы в ребусе не учитываются",
            "Ребус читается слева направо, сверху вниз",
            "Название изображенных предметов читаются в именительном падеже",
            "Несколько одинаковых предметов на одном изображении читаются во множественном числе",
            "Предмет, изображенный в ребусе, может иметь несколько названий",
            "Если предмет на рисунке перевернут, то его название читают справа налево",
            "Если слева (внизу) от рисунка стоят запятые (одна или несколько), то отбрасываются первые буквы слова (по количеству запятых",
            "Если справа (сверху) от рисунка стоят перевёрнутые запятые (одна или несколько), то отбрасываются буквы в конце слова",
            "Если возле рисунка изображена зачёркнутая буква,то её необходимо исключить из названия предмета",
            "Если возле рисунка изображена зачёркнутая цифра, то необходимо исключить из названия предмета букву с таким порядковым номером",
            "Если возле рисунка изображена \"буква вместе со знаком сложения\", то необходимо эту букву вставить в название изображенного предмета",
            "Если возле рисунка изображена цифра, знак равенства и буква, это означает, что букву с указанным порядковым номером необходимо заменить на указанную в равенства",
            "Если буква или рисунок \"перечёркнуты\", в таком случае используется предлог \"не\"",
            "Если предметы, цифры или буквы изображены один в другом, то их названия читаются с добавлением предлога \"в\" (перед или между названий)",
            "Если предметы, цифры или буквы изображены один на другом, то их названия читаются с добавлением предлога \"на\", \"над\" или \"под\" (перед или между названий)",
            "Если один предмет, цифра или буква изображен за другим, то их названия читаются с добавлением предлога \"перед\" или \"за\" (перед или между названий)",
            "Если предметы, цифры или буквы изображены один (меньший) возле другого (большего), то их названия читаются с добавлением предлога \"у\" или \"при\" (перед или между названий)",
            "Если большая буква составлена из маленьких букв (много раз повторенных), то при чтении используется предлог \"из\" (перед или между названий)",
            "Если поверх одной (большой) буквы написана другая (маленькая, много раз повторенная), то при чтении используется предлог \"по\" (перед или между названий)",
            "Если рисунки, цифры или буквы \"движутся\" один к одному или один от одного, то при чтении используется предлог \"к\" или \"от\" (перед или между названий)",
            "Если две одинаковые буквы расположены \"рядом\", то при чтении используется существительное \"пара\"",
            "Иногда в ребусе можно использовать дробь",
            "Отдельные слоги в ребусе можно изображать при помощи цифр, нот, букв греческого алфавита, химических элементов и т.п.",
            "Иногда нужный объект на картинке указывается стрелкой. (инь-янь, напр.)",
            "Если возле рисунка перечислены цифры, то буквы из названия предмета следует читать в указанном цифрами порядке",
            "Если рядом с зачёркнутой буквой написана другая, то ее следует читать вместо зачёркнутой. Иногда в этом случае между буквами ставится знак равенства",
            "Если возле рисунка изображено \"две цифры со стрелками направленными в разные стороны\", в таком случае в названии рисунка необходимо указанные цифрами буквы поменять местами\"",
            "Ребус допускает комбинацию двух и более правил одновременно"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        getWindow().setBackgroundDrawable(null);    //Decrease redrawing

        view=(ImageView)findViewById(R.id.view);
        view.setImageResource(R.drawable.prim1);
        view_levels = (TextView)findViewById(R.id.levels);
        view_coins = (TextView)findViewById(R.id.coins);
        num_REB = (TextView)findViewById(R.id.num_REB);
        name_REB = (TextView)findViewById(R.id.name_REB);
        descrip_REB = (TextView)findViewById(R.id.descrip_REB);
        next = (Button)findViewById(R.id.next);
        prev = (Button)findViewById(R.id.prev);
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_summ));
        name_REB.setText("Ребус "+nameREB[0]);
        descrip_REB.setText(descREB[0]);
        if (z==0){prev.setVisibility(View.INVISIBLE);}
        if (z==27){next.setVisibility(View.INVISIBLE);}
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if (z<27){z++;}
                if (z==27){next.setVisibility(View.INVISIBLE);} else prev.setVisibility(View.VISIBLE);
                view.setImageResource(images[z]);
                num_REB.setText("Правило №" + (z+1));
                name_REB.setText("Ребус "+nameREB[z]);
                descrip_REB.setText(descREB[z]);
                break;
            case R.id.prev:
                if (z>0){z--;}
                if (z==0){prev.setVisibility(View.INVISIBLE);}else next.setVisibility(View.VISIBLE);
                view.setImageResource(images[z]);
                num_REB.setText("Правило №" + (z + 1));
                name_REB.setText("Ребус "+nameREB[z]);
                descrip_REB.setText(descREB[z]);
                break;
            case R.id.b_menu:
                super.onStop();
                onStop();
                finish();
                break;
            case R.id.b_help:
                Toast.makeText(this, "Чтобы узнать новые правила воспользуйтесь кнопками", Toast.LENGTH_LONG).show();
                break;
        }
    }
}

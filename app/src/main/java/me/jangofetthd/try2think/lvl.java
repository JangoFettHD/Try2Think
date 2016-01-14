package me.jangofetthd.try2think;

/**
 * Basic level structure for game
 * <p/>
 * Created by Mikhail on 15.11.2015.
 */
public class lvl {
    public int image;
    public String answer;
    public int chapter;
    public int status;
    public int tryCount;
    public int helpCount;
    public int lock;
    public String help;
    public String correctAnswer;

    lvl(int image, String answer, int chapter, int status, int tryCount, int helpCount, int lock, String help, String correctAnswer) {
        this.image = image;
        this.answer = answer;
        this.chapter = chapter;
        this.tryCount = tryCount;
        this.helpCount = helpCount;
        this.lock= lock;
        this.status = status;
        this.help = help;
        this.correctAnswer = correctAnswer;
    }
}

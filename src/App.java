/**
 * @author super
 */
public class App {
    public static void main(String[] args){
        String from = "./raw/3019244114.txt";
        String target = "./out/score.txt";

        Score score = new Score(from, target);
        score.writeToTxt();
        System.out.println("See ./out/score.txt to check it!");
    }
}

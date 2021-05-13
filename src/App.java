/**
 * @author super
 */
public class App {
    public static void main(String[] args) {
        String from;
        String target = "./out/score.txt";

        if (args.length > 1) {
            // TODO
            System.out.println("Invalid use!");
        } else if (args.length == 0) {
            from = "./raw/";

            AllScore allscore = new AllScore(from, target);
            allscore.writeToTxt();
            System.out.println("See ./out/score.txt to check it!");
        } else {
            from = "./raw/" + args[0] + ".txt";

            Score score = new Score(from, target);
            score.writeToTxt();
            System.out.println("See ./out/score.txt to check it!");
        }
    }
}

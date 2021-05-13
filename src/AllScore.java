import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * @author super
 */
public class AllScore {
    private final Map<String, List<String>> allStuScores = new HashMap<>();
    private final List<String> stuIds = new ArrayList<>();
    private final List<String> hwNums = new ArrayList<>();
    private static String FILENAME;
    private static String WRITE_TO_FILENAME;

    public AllScore(String from, String target) {
        FILENAME = from;
        WRITE_TO_FILENAME = target;
        setStuIds();
        setAllStuScores();
    }

    public void writeToTxt() {
        String formatContent = formatScore();
        try {
            FileWriter fw = new FileWriter(WRITE_TO_FILENAME);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(formatContent);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String formatScore() {
        StringBuffer result = new StringBuffer("学号\t\t\t");
        for (String hwNum : hwNums) {
            result.append(hwNum).append("\t\t\t");
        }
        result.append("\r");

        for (String stuId : stuIds) {
            result.append(stuId).append("\t");
            List<String> stuScores = new ArrayList<>();
            Set<Map.Entry<String, List<String>>> entrySet = allStuScores.entrySet();
            for (Map.Entry<String, List<String>> entry : entrySet) {
                if (entry.getKey().equals(stuId)) {
                    stuScores = entry.getValue();
                }
            }
            for (String stuScore : stuScores) {
                result.append(stuScore).append("\t\t");
            }
            // add \r
            result.append("\r");
        }
        return result.toString();
    }

    private void setAllStuScores() {
        for (String stuId : stuIds) {
            String stuPath = FILENAME + stuId + ".txt";
            Score score = new Score(stuPath, WRITE_TO_FILENAME);
            List<Homework> stuHomework = score.getStuHomework();
            List<String> stuScores = new ArrayList<>();
            for (Homework homework : stuHomework) {
                String hwNum = homework.getHwNum();
                if (!hwNums.contains(hwNum) && hwNum != null) {
                    hwNums.add(hwNum);
                }
                // remove total
                String hwScore = homework.getScore();
                stuScores.add(hwScore);
            }
            allStuScores.put(stuId, stuScores);
        }
    }

    private void setStuIds() {
        File file = new File(FILENAME);
        File[] tempList = file.listFiles();
        if (tempList == null) {
            System.out.println("no file error!");
            return;
        }

        for (File value : tempList) {
            if (value.isFile()) {
                // remove .txt, FILENAME
                String stuId = value.toString().replace(FILENAME, "").replace(".txt", "");
                if (stuId.matches("[0-9]+")) {
                    stuIds.add(stuId);
                }
            }
        }
        // a good method to sort a String List
        stuIds.sort(Comparator.naturalOrder());
    }
}

import java.io.*;
import java.util.*;

/**
 * @author super
 */
public class Score {
    private static String FILENAME;
    private static String WRITE_TO_FILENAME;
    private static final Set<Character> SCORE_CHAR_SET
            = new HashSet<>(Arrays.asList(
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/'));
    private List<Homework> stuHomework;

    public Score(String from, String target) {
        FILENAME = from;
        WRITE_TO_FILENAME = target;
        getStuHomework();
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

	private void getStuHomework() {
        String content = readFromTxt(FILENAME);
        String[] homeworks = content.split("\\=\\=\\=+");
        for (String homework : homeworks) {
            String checkDate = null;
            String md5 = null;
            String stuId = null;
            String hwNum = null;
            String score = null;
            String resultType = null;
            String resultDetail = null;

            for(int i = 0; i < homework.length() - 38; i++){
                String subStr = homework.substring(i, i + 4);
                switch (subStr) {
                    case ("批改日期"):
                        checkDate = homework.substring(i + 5, i + 24);
                        break;
                    case ("文件MD"):
                        md5 = homework.substring(i + 6, i + 38);
                        break;
                    case ("学号:3"):
                        stuId = homework.substring(i + 3, i + 13);
                        break;
                    case ("作业号:"):
                        hwNum = homework.substring(i + 4, i + 7);
                        break;
                    case ("得分/满"):
                        int pos;
                        for (pos = 6; pos < 14; pos++) {
                            if (!SCORE_CHAR_SET.contains(homework.charAt(i + pos))) {
                                break;
                            }
                        }
                        score = homework.substring(i + 6, i + pos);
                        break;
                    case ("结果类型"):
                        resultType = homework.substring(i + 5, i + 14).trim();
                        break;
                    default:
                }
            }
            stuHomework.add(new Homework(checkDate, md5, stuId, hwNum, score, resultType, resultDetail));
        }
    }

    private String formatScore() {
        StringBuffer result = new StringBuffer("作业号\t得分/满分\t学号\t\t\t\t批改日期\t\t\t\t\t结果\r");
        for (Homework homework : stuHomework) {
            result.append(homework);
        }
        return result.toString();
    }

	private String readFromTxt(String filename) {
		Reader reader = null;
		StringBuffer buf = new StringBuffer();
		try {
			char[] chars = new char[1024];
			reader = new InputStreamReader(new FileInputStream(filename), "gb2312");
			int readed = reader.read(chars);
			while (readed != -1) {
				buf.append(chars, 0, readed);
				readed = reader.read(chars);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(reader);
		}
		return buf.toString();
	}

	private void close(Closeable inout) {
		if (inout != null) {
			try {
				inout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * @author super
 */
public class Homework {
    private final String checkDate;
    private final String md5;
    private final String stuId;
    private final String hwNum;
    private final String score;
    private final String resultType;
    private final String resultDetail;

    public Homework(String checkDate, String md5, String stuId, String hwNum, String score, String resultType, String resultDetail) {
        this.checkDate = checkDate;
        this.md5 = md5;
        this.stuId = stuId;
        this.hwNum = hwNum;
        this.score = score;
        this.resultType = resultType;
        this.resultDetail = resultDetail;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public String getMd5() {
        return md5;
    }

    public String getStuId() {
        return stuId;
    }

    public String getHwNum() {
        return hwNum;
    }

    public String getScore() {
        return score;
    }

    public String getResultType() {
        return resultType;
    }

    public String getResultDetail() {
        return resultDetail;
    }

    @Override
    public String toString() {
        return hwNum+"\t\t"+score+"\t\t"+stuId+"\t\t"+checkDate+"\t\t"+resultType+"\t\t"+"\r";
    }
}

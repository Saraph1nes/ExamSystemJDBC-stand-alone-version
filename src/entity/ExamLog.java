package entity;

public class ExamLog {

    private int scoreid;
    private String studentNumber;
    private String studentName;
    private String studentClass;
    private float score;

    public ExamLog(){}

    public ExamLog(String studentNumber, String studentName, String studentClass, float score) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.score = score;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}

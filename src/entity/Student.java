package entity;

public class Student {
    private String userName;
    private String password;
    private String studentName;
    private String studentNumber;
    private String studentClass;
    private boolean existImage;
    private String studentImage;

    public Student() {
    }

    public Student(String userName, String password, String studentName, String studentNumber, String studentClass,boolean existImage) {
        this.userName = userName;
        this.password = password;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.studentClass = studentClass;
        this.existImage = existImage;
    }

    public Student(String userName, String password, String studentName, String studentNumber, String studentClass, boolean existImage, String studentImage) {
        this.userName = userName;
        this.password = password;
        this.studentName = studentName;
        this.studentNumber = studentNumber;
        this.studentClass = studentClass;
        this.existImage = existImage;
        this.studentImage = studentImage;
    }

    public String getExistImage() {
        if (existImage == true){
            return "有图片";
        }else {
            return "没图片";
        }
    }

    public void setExistImage(boolean existImage) {
        this.existImage = existImage;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }
}

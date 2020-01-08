package entity;

public class Question {

    private String title;
    private String answer;
    private int existpicture;
    private String picture;


    public Question() {
    }

    public Question(String title, String answer, int existpicture) {
        this.title = title;
        this.answer = answer;
        this.existpicture = existpicture;
    }

    public Question(String title, String answer, int existpicture, String picture) {
        this.title = title;
        this.answer = answer;
        this.existpicture = existpicture;
        this.picture = picture;
    }



    //重写question类中的两个方法 equals hashCode
    //将Question对象存入Hash Set集合内 让set集合去重

    @Override
    public int hashCode(){
        String thisTitle = this.title.substring(0,this.title.indexOf("<br>"));
        return thisTitle.hashCode();
    }
    @Override
    public boolean equals(Object object){
        if (this==object){
            return true;
        }
        //是否为同一类型
        if (object instanceof Question){
            Question anotherQuestion = (Question) object;
            String thisTitle = this.title.substring(0,this.title.indexOf("<br>"));
            String anotherTitle = anotherQuestion.title.substring(0,anotherQuestion.title.indexOf("<br>"));
            if (thisTitle.equals(anotherTitle)){
                return true;
            }
        }
        return false;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

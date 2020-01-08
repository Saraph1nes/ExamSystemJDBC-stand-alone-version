package dao;

import db.DBUtil;
import entity.Question;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class QuestionDao {

    //用set是防止抽取题目时重复
    private HashSet<Question > questionBox = new HashSet<>();

    //把set集合改成list以便于从题库随机抽取题目的时候排序
    ArrayList<Question> questionBoxx = new ArrayList<>(this.readQuestionFromDB());

    private HashSet<Question> readQuestionFromDB(){
        DBUtil db = new DBUtil();
        try {
            db.getConnection();
            String sql = "select * from question";
            ResultSet rs = db.executeQuery(sql,null);
            while (rs.next()){
                Question question = null;
                if (rs.getInt(3)==1){
                    question = new Question(rs.getString(1) ,rs.getString(2),rs.getInt(3),rs.getString(4));
                }else {
                    question = new Question(rs.getString(1) ,rs.getString(2),rs.getInt(3));
                }
                questionBox.add(question);
            }
            db.closeAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return questionBox;
    }

    public ArrayList<Question> getPaper(int count){
        HashSet<Question> paper= new HashSet<>();
        while (paper.size()!=count){
            Random random = new Random();
            int index = random.nextInt(questionBoxx.size());
            paper.add(questionBoxx.get(index));
        }
        return new ArrayList<>(paper);
    }
}

package service;

import dao.QuestionDao;
import entity.Question;
import utils.MySpring;

import java.util.ArrayList;

public class QuestionService {

    private QuestionDao questionDao = MySpring.getBean("dao.QuestionDao");

    public ArrayList<Question> getPaper(int count){
        return questionDao.getPaper(count);
    }
}

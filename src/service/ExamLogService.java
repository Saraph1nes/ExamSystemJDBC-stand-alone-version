package service;

import dao.ExamLogDao;
import entity.ExamLog;
import utils.MySpring;

public class ExamLogService {
    private static ExamLogDao examLogDao = MySpring.getBean("dao.ExamLogDao");

    public static void saveLogInfoToDB(ExamLog examLog) {
        examLogDao.saveLogToDB(examLog);
    }
}

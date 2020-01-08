package dao;

import db.DBUtil;
import entity.ExamLog;

public class ExamLogDao {
    //保存用户信息
    public static boolean saveLogToDB(ExamLog examLog){
        boolean r = false;
        DBUtil db = new DBUtil();
        try {
            db.getConnection();//链接
            String sql = "insert into examlog(studentName,studentClass,studentNumber,score,scoreid)values(?,?,?,?,?)";
            Object[] param = new Object[]{examLog.getStudentName(),examLog.getStudentClass(),examLog.getStudentNumber(),examLog.getScore(),null};
            if (db.executeUpdate(sql,param)>0){
                //保存成功，返回true
                r = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.closeAll();
        }
        return r;
    }


}

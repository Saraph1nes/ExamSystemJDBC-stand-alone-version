package dao;


import db.DBUtil;
import entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao {

    //保存用户信息
    public static boolean saveUserToDB(Student student){
        boolean r = false;
        DBUtil db = new DBUtil();
        try {
            db.getConnection();//链接
            if (student.getExistImage().equals("有图片")){
                String sql = "insert into studenttable(studentNumber,userName,password,studentName,studentClass,existImage,studentImage)values(?,?,?,?,?,?,?)";
                Object[] param = new Object[]{student.getStudentNumber(),student.getUserName(),student.getPassword(),student.getStudentName(),student.getStudentClass(),1,student.getStudentImage()};
                if (db.executeUpdate(sql,param)>0){
                    //保存成功，返回true
                    r = true;
                }
            }else {
                String sql = "insert into studenttable(studentNumber,userName,password,studentName,studentClass,existImage)values(?,?,?,?,?,?)";
                Object[] param = new Object[]{student.getStudentNumber(),student.getUserName(),student.getPassword(),student.getStudentName(),student.getStudentClass(),0};
                if (db.executeUpdate(sql,param)>0){
                    //保存成功，返回true
                    r = true;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.closeAll();
        }
        return r;
    }

    //根据用户名查询用户
    public Student selectOne(String userName){
        DBUtil db = new DBUtil();
        Student student = null;
        try {
            //获取链接
            db.getConnection();
            String sql = "select * from studenttable where userName = ?";
            //设置参数
            Object[] param = new Object[]{userName};
            //查询
            ResultSet rs = db.executeQuery(sql,param);
            if (rs.next()){
                student = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.closeAll();
        }
        return student;
    }
}
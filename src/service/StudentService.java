package service;

import dao.StudentDao;
import entity.Student;
import utils.MySpring;

public class StudentService {

    //包含一个dao对象作为属性
    private static StudentDao studentDao = MySpring.getBean("dao.StudentDao");


    //带照片用户信息
    public static void saveUserInfoToDB(Student student) {
        studentDao.saveUserToDB(student);
    }

    public static String login(String account,String password){
        //selectOne返回的是一个数组对象
        Student student = studentDao.selectOne(account);
        if (account.equals("") || password.equals("")) {
            return "用户名或密码不能为空";
        } else {//账号密码输入框不是空
            if (student!=null){//数据库有匹配的账号
                if (student.getPassword().trim().equals(password)){
                    return "登录成功";
                }
            }
            return "用户名或密码错误";
        }
    }

    public static String getMessage_name(String account){
        //selectOne返回的是一个数组对象
        Student student = studentDao.selectOne(account);
        return student.getStudentName();
    }

    public static  String getMessage_Numb(String account){
        //selectOne返回的是一个数组对象
        Student student = studentDao.selectOne(account);
        return student.getStudentNumber();
    }

    public static  String getMessage_Class(String account){
        Student student = studentDao.selectOne(account);
        return student.getStudentClass();
    }

    public static String getMessage_Image(String account){
        //selectOne返回的是一个数组对象
        Student student = studentDao.selectOne(account);
        return student.getStudentImage();
    }



}

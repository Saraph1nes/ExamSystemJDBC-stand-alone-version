package ui;

import entity.File;
import entity.Student;
import service.StudentService;
import utils.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//用户注册 姓名重名 图片重名判断还未完善

public class RegisterFrame extends BaseFrame {

    public RegisterFrame(){
        this.setFontAndSoOn();
        this.addElement();
        this.addListener();
        this.setFrameSelf();
    }

    public RegisterFrame(String title) {
        super(title);
        this.init();
    }

    private JPanel mainJP = new JPanel();
    private JPanel photoJP = new JPanel();

    private JLabel titleLabel = new JLabel("用户注册");

    private JLabel userNameLabel = new JLabel("用户名：");
    private JTextField userNameField = new JTextField(20);
    private JLabel passwordLabel = new JLabel("密码：");
    private JTextField passwordField = new JTextField(20);
    private JLabel confirmPasswordLabel = new JLabel("确认密码：");
    private JTextField confirmPasswordField = new JTextField(20);
    private JLabel NameLabel = new JLabel("姓名：");
    private JTextField NameField = new JTextField(20);
    private JLabel studentNumLabel = new JLabel("学号：");
    private JTextField studentNumField = new JTextField(20);
    private JLabel studentClassLabel = new JLabel("班级：");
    private JTextField studentClassField = new JTextField(20);
    private JLabel photoLabel = new JLabel("照片(选填)：");
    private JLabel photoShowLabel = new JLabel();

    private JButton addPhotoBtn = new JButton("添加");
    private JButton registerBtn = new JButton("注册");
    private JButton cancelBtn = new JButton("取消");

    File file = new File();

    @Override
    protected void setFontAndSoOn() {
        mainJP.setLayout(null);

        photoJP.setLayout(null);
        photoJP.setBounds(0,365,300,180);
        photoJP.setBorder(BorderFactory.createLineBorder(Color.black));

        titleLabel.setBounds(90,20,180,25);
        titleLabel.setFont(new Font("黑体",Font.BOLD,24));

        userNameLabel.setBounds(20,70,100,25);
        userNameLabel.setFont(new Font("黑体",Font.BOLD,16));
        userNameField.setBounds(100,70,160,25);

        passwordLabel.setBounds(20,120,100,25);
        passwordLabel.setFont(new Font("黑体",Font.BOLD,16));
        passwordField.setBounds(100,120,160,25);

        confirmPasswordLabel.setBounds(20,170,100,25);
        confirmPasswordLabel.setFont(new Font("黑体",Font.BOLD,16));
        confirmPasswordField.setBounds(100,170,160,25);

        NameLabel.setBounds(20,220,100,25);
        NameLabel.setFont(new Font("黑体",Font.BOLD,16));
        NameField.setBounds(100,220,160,25);

        studentNumLabel.setBounds(20,270,100,25);
        studentNumLabel.setFont(new Font("黑体",Font.BOLD,16));
        studentNumField.setBounds(100,270,160,25);

        studentClassLabel.setBounds(20,320,100,25);
        studentClassLabel.setFont(new Font("黑体",Font.BOLD,16));
        studentClassField.setBounds(100,320,160,25);

        registerBtn.setBounds(30,560,90,25);
        registerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelBtn.setBounds(170,560,90,25);
        cancelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        photoLabel.setBounds(20,20,150,25);
        photoLabel.setFont(new Font("黑体",Font.BOLD,16));

        addPhotoBtn.setBounds(200,20,60,25);
        addPhotoBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        photoShowLabel.setBounds(70,60,150,100);
        photoShowLabel.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    protected void addElement() {
        //一个主区域，一个图片添加区域
        mainJP.add(titleLabel);
        mainJP.add(userNameLabel);
        mainJP.add(userNameField);
        mainJP.add(passwordLabel);
        mainJP.add(passwordField);
        mainJP.add(confirmPasswordLabel);
        mainJP.add(confirmPasswordField);
        mainJP.add(NameLabel);
        mainJP.add(NameField);
        mainJP.add(studentNumLabel);
        mainJP.add(studentNumField);
        mainJP.add(studentClassLabel);
        mainJP.add(studentClassField);

        mainJP.add(registerBtn);
        mainJP.add(cancelBtn);

        photoJP.add(photoLabel);
        photoJP.add(addPhotoBtn);
        photoJP.add(photoShowLabel);

        mainJP.add(photoJP);

        this.add(mainJP);
    }

    @Override
    protected void addListener() {

        //选择图片
        addPhotoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showPhoto();//展示
            }
        });

        //注册
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String userName = userNameField.getText();
                String password = passwordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                String name = NameField.getText();
                String studentNum = studentNumField.getText();
                String studentClass = studentClassField.getText();
                String photoName = file.getFileName();

                if (userName.equals("") || password.equals("") || confirmPassword.equals("") || name.equals("") || studentNum.equals("") || studentClass.equals("")) {
                    JOptionPane.showMessageDialog(RegisterFrame.this,"用户信息创建失败,除头像外不能为空");
                } else {//输入框不是空
                    if (!password.equals(confirmPassword)){//两个密码不一样
                        JOptionPane.showMessageDialog(RegisterFrame.this,"您输入的两个密码不一致，请重新输入");
                    }else {//不为空且密码一样
                        if (photoName != null){
                            //存用户信息(带照片)
                            Student s = new Student(userName,password,name,studentNum,studentClass,true,photoName);
                            StudentService.saveUserInfoToDB(s);
                            savePhotoToDB();//存照片
                            JOptionPane.showMessageDialog(RegisterFrame.this, "用户创建成功!");
                            RegisterFrame.this.setVisible(false);
                            new LoginFrame("考试系统");
                        }else{
                            Student s = new Student(userName,password,name,studentNum,studentClass,false);
                            //存用户信息(无照片)
                            StudentService.saveUserInfoToDB(s);
                            JOptionPane.showMessageDialog(RegisterFrame.this, "用户创建成功!");
                            RegisterFrame.this.setVisible(false);
                            new LoginFrame("考试系统");
                        }
                    }
                }
            }
        });

        //取消
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegisterFrame.this.setVisible(false);
                new LoginFrame("考试系统");
            }
        });
    }

    @Override
    protected void setFrameSelf() {
        this.setSize(300,640);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //处理用户图片
    private ImageIcon drawUserImage(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(150,100,Image.SCALE_DEFAULT));
        return imageIcon;
    }

    //展示照片
    private void showPhoto() {
        JFileChooser jFileChooser = new JFileChooser();
        int i = jFileChooser.showOpenDialog(RegisterFrame.this);
        java.io.File selectedFile = jFileChooser.getSelectedFile();//获得选中的文件对象

        String AbsolutePath = selectedFile.getAbsolutePath();
        String fName = selectedFile.getName();

        file.setSelectedAbsolutePath(AbsolutePath);
        file.setFileName(fName);
        if (i == jFileChooser.APPROVE_OPTION) {
//            System.out.println(selectedFile.getAbsolutePath());
            photoShowLabel.setIcon(this.drawUserImage(AbsolutePath));//显示图片
        }
    }

    //把照片信息存储到库中
    private void savePhotoToDB(){
        try {
            this.copyJpg(file.getSelectedAbsolutePath(), "images\\userImage\\"+ file.getFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //复制图片到文件夹
    private void copyJpg(String jpgPath, String aimPath) throws IOException {
        InputStream inputStream=new FileInputStream(jpgPath);
        OutputStream outputStream=new FileOutputStream(aimPath);
        int count = 0;
        while (count == 0){
            count = inputStream.available();
        }
        byte[] b=new byte[count];
        inputStream.read(b);
        outputStream.write(b);
        outputStream.close();
        inputStream.close();
    }
}
package ui;

import service.StudentService;
import utils.BaseFrame;
import utils.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginFrame extends BaseFrame {

    public LoginFrame(){
        this.setFontAndSoOn();
        this.addElement();
        this.addListener();
        this.setFrameSelf();
    }

    public LoginFrame(String title){
        super(title);
        this.init();
    }

    private JPanel p = new JPanel();
    private JLabel lblTitle = new JLabel("在线考试系统");
    private JLabel lblAccount = new JLabel("用户名：");
    private JLabel lblPwd = new JLabel("密 码：");
    private JTextField txtAccount = new JTextField(20);
    private JPasswordField txtPwd = new JPasswordField(20);
    private JButton btnLogin = new JButton("登录");
    private JButton btnRegister = new JButton("注册");
    private JButton btnCancle = new JButton("重置");


    @Override
    protected void setFontAndSoOn() {
        p.setLayout(null);
        lblTitle.setBounds(60,20,180,25);
        lblTitle.setFont(new Font("黑体",Font.BOLD,24));
        txtPwd.setEchoChar('*');
        lblAccount.setBounds(30,60,60,25);
        lblPwd.setBounds(30,90,60,25);
        txtAccount.setBounds(95,60,150,25);
        txtPwd.setBounds(95, 90, 150, 25);
        btnLogin.setBounds(30,130,70,25);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancle.setBounds(110,130,70,25);
        btnCancle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRegister.setBounds(190,130,70,25);
        btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void addElement() {
        p.add(lblTitle);
        p.add(lblAccount);
        p.add(lblPwd);
        p.add(txtAccount);
        p.add(txtPwd);
        p.add(btnLogin);
        p.add(btnCancle);
        p.add(btnRegister);
        p.setBackground(Color.white);
        this.add(p);
    }

    @Override
    protected void addListener() {
        btnRegister.addActionListener(new RegisterListener());
        btnCancle.addActionListener(new CancleListener());
        btnLogin.addActionListener(new LoginListener());
        txtAccount.addKeyListener(new LoginKeyListener());
        txtPwd.addKeyListener(new LoginKeyListener());
    }

    @Override
    protected void setFrameSelf() {

        this.setSize(300,220);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //登录监听类
    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //获取用户输入的账号密码
            String account = txtAccount.getText();
            String password = new String(txtPwd.getPassword());
            //调用之前service层的登录方法
            String result = StudentService.login(account,password);

            //判断最终结果
            switch (result){
                case "用户名或密码不能为空":
                case "用户名或密码错误":
                    //弹出对话框登陆失败
                    JOptionPane.showMessageDialog(p,result);
                    break;
                case "登录成功":
                    String userNameInfo = StudentService.getMessage_name(account);
                    LoginFrame.this.setVisible(false);
                    //弹出考试界面
                    ExamFrame.setAccountInfo(account);
                    new ExamFrame(userNameInfo+"的考试页面");
                    System.out.println("登录成功");
                    break;
            }
        }
    }

    //注册按钮监听类
    private class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            LoginFrame.this.setVisible(false);
            new RegisterFrame("注册");
        }
    }

    //重置按钮监听类
    private class CancleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            txtAccount.setText("");
            txtPwd.setText("");
        }
    }

    //键盘监听
    private class LoginKeyListener implements KeyListener {

        @Override
        public void keyReleased(KeyEvent keyEvent) { }

        @Override
        public void keyTyped(KeyEvent keyEvent) { }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if(keyEvent.getKeyChar() == keyEvent.VK_ENTER){
                //获取用户输入的账号密码
                String account = txtAccount.getText();
                String password = new String(txtPwd.getPassword());
                //调用之前service层的登录方法
                String result = StudentService.login(account,password);

                //判断最终结果
                switch (result){
                    case "用户名或密码不能为空":
                    case "用户名或密码错误":
                        //弹出对话框登陆失败
                        JOptionPane.showMessageDialog(p,result);
                        break;
                    case "登录成功":
                        String userNameInfo = StudentService.getMessage_name(account);
                        LoginFrame.this.setVisible(false);
                        //弹出考试界面
                        ExamFrame.setAccountInfo(account);
                        new ExamFrame(userNameInfo+"的考试页面");
                        System.out.println("登录成功");
                        break;
                }
            }
        }
    }

    public static void main(String args[]){
        new LoginFrame("登录");
    }
}

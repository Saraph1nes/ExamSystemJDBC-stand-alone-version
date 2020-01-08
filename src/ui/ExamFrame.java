package ui;

import entity.ExamLog;
import entity.Question;
import service.ExamLogService;
import service.QuestionService;
import service.StudentService;
import utils.BaseFrame;
import utils.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExamFrame extends BaseFrame {

    //获取QuestionService对象
    private QuestionService service = MySpring.getBean("service.QuestionService");
    private ArrayList<Question> paper = service.getPaper(10);
    private String[] answers = new String[paper.size()];

    //当前题目序号
    private int nowNum = 0;
    //试题总数
    private int totalCount = paper.size();
    //已经回答的题数
    private int answerCount = 0;
    //未答的题数
    private int unanswerCount = totalCount;

    //设置考试时间(Min)
    private int time = 1;

    private TimeControlThread timeControlThread = new TimeControlThread();

    //静态的用户名传参
    private static String acc;

    public ExamFrame(){
        this.setFontAndSoOn();
        this.addElement();
        this.addListener();
        this.setFrameSelf();
    }

    public ExamFrame(String title){
        super(title);
        this.init();
    }


    //panel区域的分割
    private JPanel mainJP = new JPanel();
    private JPanel messageJP = new JPanel();
    private JPanel btnJP = new JPanel();

    //在messageJP中又分成上中下三部分，上面是用户信息，中间是考试题数，下面是考试剩余时间
    private JPanel mess_userJP = new JPanel();
    private JPanel mess_testJP = new JPanel();
    private JPanel mess_pictureJP = new JPanel();

    //添加主要答题的组件
    private JTextArea examArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(examArea);

    //信息展示区
    //照片,姓名，学号，
    //用户照片，用户姓名，用户识别号(学号，工号等)，提示当前题号，题目总数，已答题数，剩余题数，提示剩余时间,倒计时。

    private StudentService userService = new StudentService();

    //获取属性
    public static void setAccountInfo(String account){
        acc = account;
    }

    private String userPhotoImage = userService.getMessage_Image(acc);
    private JLabel userPhotoLabel = new JLabel();

    private String userNameInfo = userService.getMessage_name(acc);
    private JLabel nameLabel = new JLabel("姓名："+userNameInfo);
    private String userClassInfo = userService.getMessage_Class(acc);
    private JLabel classLabel = new JLabel("班级："+userClassInfo);
    private String userNumberInfo = userService.getMessage_Numb(acc);
    private JLabel idLabel = new JLabel("学号："+userNumberInfo);
    //当前题号，题目总数，已答题数，剩余题数
    private JLabel nowNumLabel = new JLabel("当前题号：");
    private JLabel totalCountLabel = new JLabel("题目总数：");
    private JLabel answerCountLabel = new JLabel("已答题数：");
    private JLabel unAnswerLabel = new JLabel("剩余题数：");
    //剩余时间
    private JLabel timeLabel = new JLabel("剩余时间：");
    //显示剩余时间
    private JLabel realTimeLabel = new JLabel("00:00:00");
    //显示当前题号，题目总数，已答题数，剩余题数
    private JTextField nowNumField = new JTextField();
    private JTextField totalCountField = new JTextField();
    private JTextField answerCountField = new JTextField();
    private JTextField unAnswerField = new JTextField();

    //考试图片
    private JLabel ExamPictureLabel = new JLabel();

    //下方按钮区
    private JButton aBtn = new JButton("A");
    private JButton bBtn = new JButton("B");
    private JButton cBtn = new JButton("C");
    private JButton dBtn = new JButton("D");

    private JButton prevBtn = new JButton("上一题");
    private JButton submitBtn = new JButton("提交试卷");
    private JButton nextBtn = new JButton("下一题");



    @Override
    protected void setFontAndSoOn() {
        mainJP.setLayout(null);
        mainJP.setBackground(Color.gray);
        messageJP.setLayout(null);
        btnJP.setLayout(null);
        mess_userJP.setLayout(null);
        mess_testJP.setLayout(null);
        mess_pictureJP.setLayout(null);


        //题目部分
        //设置每个组件的大小颜色背景
        scrollPane.setBounds(20,20,890,550);
        examArea.setFont(new Font("黑体",Font.BOLD,30));
        examArea.setEnabled(false);
        this.showQuestionAndPicture();

        //考试信息部分
        //设置message区域的位置
        messageJP.setBounds(930,20,240,730);
        messageJP.setBorder(BorderFactory.createLineBorder(Color.gray));

        //按钮部分
        //设置button区域的位置
        btnJP.setBounds(20,590,890,160);
        btnJP.setBorder(BorderFactory.createLineBorder(Color.gray));

        aBtn.setBounds(90,30,120,30);
        aBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bBtn.setBounds(270,30,120,30);
        bBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cBtn.setBounds(450,30,120,30);
        cBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dBtn.setBounds(630,30,120,30);
        dBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        prevBtn.setBounds(90,80,180,30);
        prevBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextBtn.setBounds(570,80,180,30);
        nextBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        submitBtn.setBounds(330,80,180,30);
        submitBtn.setForeground(Color.red);
        submitBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));



        //设置message中考试图片显示区域的大小颜色背景
        mess_pictureJP.setBounds(20,10,200,180);
        mess_pictureJP.setBorder(BorderFactory.createLineBorder(Color.gray));

        //第一部分
        //考试图片位置
        ExamPictureLabel.setBounds(0,0,200,180);


        //第二部分
        //设置message中用户信息区域的大小颜色背景
        mess_userJP.setBounds(20,210,200,260);
        mess_userJP.setBorder(BorderFactory.createLineBorder(Color.gray));

        //设置message区域中组件的大小颜色背景
        userPhotoLabel.setBounds(55,10,90,130);
        userPhotoLabel.setBorder(BorderFactory.createLineBorder(Color.gray));

        //姓名，班级，学号标签
        nameLabel.setBounds(20,160,150,20);
        nameLabel.setFont(new Font("黑体",Font.PLAIN,15));
        classLabel.setBounds(20,190,150,20);
        classLabel.setFont(new Font("黑体",Font.PLAIN,15));
        idLabel.setBounds(20,220,150,20);
        idLabel.setFont(new Font("黑体",Font.PLAIN,15));

        //第三部分
        //设置message中考试信息区域的大小颜色背景
        mess_testJP.setBounds(20,490,200,220);
        mess_testJP.setBorder(BorderFactory.createLineBorder(Color.gray));

        //
        nowNumLabel.setBounds(20,20,100,20);
        nowNumLabel.setFont(new Font("黑体",Font.PLAIN,16));
        nowNumField.setBounds(110,20,50,20);
        nowNumField.setHorizontalAlignment(JTextField.CENTER);
        nowNumField.setBorder(BorderFactory.createLineBorder(Color.gray));
        nowNumField.setEnabled(false);

        totalCountLabel.setBounds(20,60,100,20);
        totalCountLabel.setFont(new Font("黑体",Font.PLAIN,16));
        totalCountField.setBounds(110,60,50,20);
        totalCountField.setHorizontalAlignment(JTextField.CENTER);
        totalCountField.setBorder(BorderFactory.createLineBorder(Color.gray));
        totalCountField.setEnabled(false);

        answerCountLabel.setBounds(20,100,100,20);
        answerCountLabel.setFont(new Font("黑体",Font.PLAIN,16));
        answerCountField.setBounds(110,100,50,20);
        answerCountField.setHorizontalAlignment(JTextField.CENTER);
        answerCountField.setBorder(BorderFactory.createLineBorder(Color.gray));
        answerCountField.setEnabled(false);

        unAnswerLabel.setBounds(20,140,100,20);
        unAnswerLabel.setFont(new Font("黑体",Font.PLAIN,16));
        unAnswerField.setBounds(110,140,50,20);
        unAnswerField.setHorizontalAlignment(JTextField.CENTER);
        unAnswerField.setBorder(BorderFactory.createLineBorder(Color.gray));
        unAnswerField.setEnabled(false);

        timeLabel.setBounds(20,180,100,20);
        timeLabel.setFont(new Font("黑体",Font.PLAIN,16));
        timeLabel.setForeground(Color.blue);
        realTimeLabel.setBounds(110,180,100,20);
        realTimeLabel.setFont(new Font("黑体",Font.BOLD,16));
        realTimeLabel.setForeground(Color.blue);

        nowNumField.setText(nowNum + 1 + "");
        totalCountField.setText(totalCount + "");
        answerCountField.setText(answerCount + "");
        unAnswerField.setText(unanswerCount + "");

        realTimeLabel.setText(time+"");
    }

    @Override
    protected void addElement() {
        //三中下三块添加进message区域
        messageJP.add(mess_userJP);
        messageJP.add(mess_testJP);
        messageJP.add(mess_pictureJP);

        //用户信息相关进用户区域
        mess_userJP.add(userPhotoLabel);
        mess_userJP.add(nameLabel);
        mess_userJP.add(classLabel);
        mess_userJP.add(idLabel);
        //考试信息相关进考试区域
        mess_testJP.add(nowNumLabel);
        mess_testJP.add(nowNumField);
        mess_testJP.add(totalCountLabel);
        mess_testJP.add(totalCountField);
        mess_testJP.add(answerCountLabel);
        mess_testJP.add(answerCountField);
        mess_testJP.add(unAnswerLabel);
        mess_testJP.add(unAnswerField);
        mess_testJP.add(timeLabel);
        mess_testJP.add(realTimeLabel);

        //考试图片相关区域
        mess_pictureJP.add(ExamPictureLabel);

        //添加进button区域
        btnJP.add(aBtn);
        btnJP.add(bBtn);
        btnJP.add(cBtn);
        btnJP.add(dBtn);
        btnJP.add(prevBtn);
        btnJP.add(submitBtn);
        btnJP.add(nextBtn);

        mainJP.add(scrollPane);
        mainJP.add(messageJP);
        mainJP.add(btnJP);

        this.add(mainJP);
    }

    @Override
    protected void addListener() {
        //提交
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int value = JOptionPane.showConfirmDialog(ExamFrame.this,"是否确认提交试卷");
                if (value == 0){
                    //倒计时停止
                    timeControlThread.stopTimeThread();
                    //按钮禁用
                    ExamFrame.this.btnStatus(false);
                    nextBtn.setEnabled(false);
                    prevBtn.setEnabled(false);
                    ExamFrame.this.clearOptionButtonColor();
                    //成绩计算
                    float score = ExamFrame.this.checkPaper();
                    examArea.setText("考试结束\n您最终的成绩为\n"+score);
                    //成绩存入数据库
                    ExamLog examLog = new ExamLog(userNumberInfo,userNameInfo,userClassInfo,score){};
                    ExamLogService.saveLogInfoToDB(examLog);
                }
            }
        });

        //上一题
        prevBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ExamFrame.this.clearOptionButtonColor();
                ExamFrame.this.btnStatus(true);
                //下一题按钮可用
                nextBtn.setEnabled(true);
                //题目序号--
                nowNum--;
                if (nowNum == 0) {//第一题时
                    prevBtn.setEnabled(false);
                }
                ExamFrame.this.restoreAnswer();
                ExamFrame.this.showQuestionAndPicture();
                nowNumField.setText(nowNum+1+"");
                answerCountField.setText(--answerCount+"");
                unAnswerField.setText(++unanswerCount+"");
            }
        });

        //下一题
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                prevBtn.setEnabled(true);
                ExamFrame.this.clearOptionButtonColor();
                nowNum++;
                if (nowNum==totalCount){
                    examArea.setText("答题完毕，请点击按钮提交试卷");
                    //下一题按钮失效
                    nextBtn.setEnabled(false);
                    //全部选项按钮失效
                    ExamFrame.this.btnStatus(false);
                }else{
                    ExamFrame.this.showQuestionAndPicture();
                    //当前题号+1
                    nowNumField.setText(nowNum+1 + "");
                }
                answerCountField.setText(++answerCount+"");
                unAnswerField.setText(--unanswerCount+"");
            }
        });

        //ABCD
        ActionListener optionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //清除按钮状态
                ExamFrame.this.clearOptionButtonColor();
                JButton button = (JButton)actionEvent.getSource();
                button.setBackground(Color.yellow);
                //选择值存储
                answers[nowNum]= button.getText();
            }
        };

        aBtn.addActionListener(optionListener);
        bBtn.addActionListener(optionListener);
        cBtn.addActionListener(optionListener);
        dBtn.addActionListener(optionListener);

    }

    @Override
    protected void setFrameSelf() {
        // 设置窗体初始最大化
        this.setSize(1200,800);
        // 设置窗口初始化居中
        this.setLocationRelativeTo(null);
        //不让窗体拖拽大小
        this.setResizable(false);
        // 设置默认的关闭按钮操作为退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗体初始可见
        this.setVisible(true);
        //启动线程开始倒数
        timeControlThread.start();
    }

    //处理考试图片展示
    private ImageIcon drawImage(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(200,180,Image.SCALE_DEFAULT));
        return imageIcon;
    }

    //处理用户头像
    private ImageIcon drawUserImage(String path){
        ImageIcon imageIcon = new ImageIcon(path);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(90,130,Image.SCALE_DEFAULT));
        return imageIcon;
    }

    //展示头像
    private void showUserPhoto(){
        String student = userPhotoImage;
        if (student != null){
            userPhotoLabel.setIcon(this.drawUserImage("images\\userImage\\"+student));
        }
    }

    //展示题目
    private void showQuestionAndPicture(){
        Question question = paper.get(nowNum);
        String pictute = question.getPicture();
        if (pictute != null){
            ExamPictureLabel.setIcon(this.drawImage("images\\Qimage\\"+pictute));
        }else {
            ExamPictureLabel.setIcon(null);
        }
        examArea.setText((nowNum+1)+"."+question.getTitle().replace("<br>","\n "));
    }

    //清楚按钮状态
    private void clearOptionButtonColor(){
        aBtn.setBackground(null);
        bBtn.setBackground(null);
        cBtn.setBackground(null);
        dBtn.setBackground(null);
    }

    //ABCD按钮是否失效
    private void btnStatus(boolean key){
        aBtn.setEnabled(key);
        bBtn.setEnabled(key);
        cBtn.setEnabled(key);
        dBtn.setEnabled(key);
    }

    //还原之前已选答案
    private void restoreAnswer(){
        String answer = answers[nowNum];
        if(answer==null){
            return ;
        }
        switch (answer){
            case "A":
                aBtn.setBackground(Color.yellow);
                break;
            case "B":
                bBtn.setBackground(Color.yellow);
                break;
            case "C":
                cBtn.setBackground(Color.yellow);
                break;
            case "D":
                dBtn.setBackground(Color.yellow);
                break;
            default:
                this.clearOptionButtonColor();
                break;
        }
    }

    //处理时间倒计时 内部类
    private class TimeControlThread extends Thread{
        private boolean flag = true;

        public void stopTimeThread(){
            this.flag = false;
        }

        public void run(){
            //转化time成 时:分:秒
            int hour = time/60;
            int minute = time%60;
            int second = 0;

            while (flag){
                StringBuilder timeString = new StringBuilder();
                if (hour >= 0 && hour < 10){
                    timeString.append("0");
                }
                timeString.append(hour);
                timeString.append(":");
                if (minute >= 0 && minute < 10){
                    timeString.append("0");
                }
                timeString.append(minute);
                timeString.append(":");

                if (second >= 0 && second < 10){
                    timeString.append("0");
                }
                timeString.append(second);

                realTimeLabel.setText(timeString.toString());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (second > 0){//秒数有
                    second--;
                }else{//秒数为0
                    if (minute > 0){//分钟先-1
                        minute--;
                        second=59;
                    }else{//分钟为0
                        if (hour>0){//小时-1
                            hour--;
                            minute=59;
                            second=59;
                        }else {//考试完毕
                            System.out.println("时间截止");
                            realTimeLabel.setForeground(Color.RED);
                            ExamFrame.this.btnStatus(false);
                            prevBtn.setEnabled(false);
                            nextBtn.setEnabled(false);
                            JOptionPane.showMessageDialog(ExamFrame.this,"考试结束，请提交试卷！");
                            break;
                        }
                    }
                }
            }
        }
    }

    //计算成绩
    private float checkPaper(){
        float score = 100;
        int size = paper.size();
        for (int i=0;i<paper.size();i++){
            Question question = paper.get(i);
            String standardAnster = question.getAnswer();
            if (!standardAnster.equals(answers[i])){
                score -= (100/size);
            }
        }
        return score;
    }

    {
        this.showUserPhoto();
    }
}

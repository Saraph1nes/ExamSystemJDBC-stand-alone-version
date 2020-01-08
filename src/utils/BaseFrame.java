package utils;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {
    //此类定义了窗口模板

    public BaseFrame(){}
    public BaseFrame(String title){
        super(title);
    }

    public void init(){
        this.setFontAndSoOn();
        this.addElement();
        this.addListener();
        this.setFrameSelf();
    }

    //1、字体颜色，背景，布局等
    protected abstract void setFontAndSoOn();
    //2、添加属性到窗体
    protected abstract void addElement();
    //3、添加事件监听
    protected abstract void addListener();
    //4、设置窗体自身属性
    protected abstract void setFrameSelf();
}

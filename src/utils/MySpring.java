package utils;


import java.util.HashMap;

//目的是为了管理对象的产生
//对象的控制权交给当前类来完成    IOC控制反转
//生命周期托管的方式，实现了对象的单例
public class MySpring {

    //属性 为了存储所有被管理对象
    private static HashMap<String,Object> beanBox = new HashMap<>();


    //设计一个方法 获取任何一个类的对象
    //返回值（泛型） 参数String 类名
    public static <T>T getBean(String className){//传递一个类全名
        T object = null;
        try {
            //1、直接从beanBox里面获取
            object = (T) beanBox.get(className);
            //2、如果obj是空的 证明之前没有创建过这个对象
            if (object == null){
                //3、通过类名获取class
                Class clazz = Class.forName(className);
                //4、通过反射产生一个对象
                object = (T) clazz.newInstance();
                //5、新的对象存入集合
                beanBox.put(className,object);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}

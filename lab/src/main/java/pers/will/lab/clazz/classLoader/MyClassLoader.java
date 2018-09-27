package pers.will.lab.clazz.classLoader;

import sun.misc.Launcher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    private String root;

    public MyClassLoader(String root) {
        // TODO 更改构造方法，是否会有潜在风险，需要判断
        super();
        this.root = root;
    }

    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader("/Users/wxp/Documents/work/dev/workspace_idea/laboratory/lab/target/test-classes");
        /**
         * 类本身可以被AppClassLoader类加载，因此我们不能把被测试类放在类路径下。
         * 否则，由于双亲委托机制的存在，会直接导致该类由 AppClassLoader 加载，而不会通过我们自定义类加载器来加载
         */
        Class<?> testClass = null;
        try {
            testClass = classLoader.loadClass("pers.will.lab.TestClassLoader");
            if (testClass != null) {
                Object obj = testClass.newInstance();
                System.out.println(obj.getClass().getClassLoader());
                // 执行方法：通过反射调用Test类的say方法
                Method method = testClass.getDeclaredMethod("say", null);
                method.invoke(obj, null);
            }


        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // TODO JAVA虚拟机入口
        Launcher laucher;
//    Thread.currentThread().getContextClassLoader()
    }

    /**
     * 查找Class
     * <br><i>如果自定义加载器，直接重载loadClass方法，可能会破坏双亲委派机制，因此我们重载findClass方法，这也是Java设计上预留的插口</i>
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] loadClassData(String className) {
        String fileName = root + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try {
            FileInputStream is = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            try {
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // TODO 流关闭的先后顺序需要确定
                is.close();
                baos.close();
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
package pers.will.javaagent;

import org.apache.ibatis.javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * javaagent技术可用于APM监控，也可以用于动态代理、软件破解等，像Dubbo就使用了该技术；
 * <br>javassist基于ASM实现，类似的还有cglib、spring等，都基于ASM实现了动态代理
 * <br>javaagent使用时，需要在目标应用的jvm启动参数中配置javaagent:${agent.jar.location}，
 * 在当前开发环境中，可在右上角Application位置点edit configuration，输入javaagent:/Users/wxp/Documents/work/dev/workspace_idea/laboratory/javaagent/target/javaagent-1.0-SNAPSHOT.jar，该路径为package打出来含premain-class的jar
 */
public class APMAgent {

    /**
     * 类装载时调用插桩
     * <br>打包时需在pom plugin中指定Premain-Class
     *
     * @param arg
     * @param instrumentation
     */
    public static void premain(String arg, Instrumentation instrumentation) {
        System.out.println("** This is premain.");

        instrumentation.addTransformer(new ClassFileTransformer() {
            //  每个Java类装载时，都会调用ClassFileTransformer的transform方法
            // TODO 上文未验证，查看ClassLoader源码时，发现最终在defineClass1方法中将byte[]转换成了Class对象，但该方法为native方法，怀疑该实现方式，也是agent类需要通过-javaagent配置扩展的原因
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                // 代码校验块
                if (className == null) {
                    return null;
                }
                String classReference = className.replaceAll("/", ".");
//                System.out.println(className);
                if (!"pers.will.javaagent.SourceClass".equals(classReference)) {
                    return null;
                }

                // 使用javassist可以Java语义化操作字节码，否则要非常了解字节码结构、才能完成插桩；可使用javap -c 查看java类字节码
                ClassPool pool = new ClassPool();
                pool.insertClassPath(new LoaderClassPath(loader));

                // 返回改造后的类字节码，替换原类字节码； 如返回null或空数组，则类加载器会使用直接读取的原字节码
                return parse(pool, classReference);
            }

            private byte[] parse(ClassPool pool, String classReference) {
                CtClass clazz = null;
                try {
                    clazz = pool.get(classReference);

                    String insertBegin = "long begin = System.currentTimeMillis();" + "System.out.println(\"** \" + begin);";
                    String insertAfter1 = "try { java.lang.Thread.sleep(10l); } catch (InterruptedException e) {}" +
                            "long end = System.currentTimeMillis();" + "System.out.println(\"** \" + end);";
                    String insertAfter2 = "System.out.println(\"** \" + (end - begin));";
                    // 插桩演示1（直接操作整段插入）
                    {
                        CtMethod method = clazz.getDeclaredMethod("sayHello");
                        method.insertBefore(insertBegin);
                        method.insertAfter(insertAfter1);
                        // method.insertAfter(insertAfter2);
                        // 上面这句会报错，因为insertBefore和insertAfter方法，都是以代码块形式，将代码插入到原来的类中,
                        // 也就意味着每次插入、参数作用域都是独立的
                    }

                    // 插桩演示2（复制方法进行多段关联插入）
                    {
                        CtMethod method = clazz.getDeclaredMethod("write");
                        // 构造新方法
                        String newMethodName = method.getName() + "$agent";
                        CtMethod newMethod = CtNewMethod.copy(method, newMethodName, clazz, null);
                        clazz.addMethod(newMethod);
                        // 改造原方法 // XXX $$是javassist的固定语法，表示传递所有参数，类似的还有其他$0/$1等等，具体可参阅官方文档
                        method.setBody("{" + insertBegin + newMethodName + "($$);" + insertAfter1 + insertAfter2 + "}");
                    }

                    // 返回改造后类的字节码
                    return clazz.toBytecode();
                } catch (NotFoundException | CannotCompileException | IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }


}

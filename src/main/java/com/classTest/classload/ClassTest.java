package com.classTest.classload;

import java.lang.annotation.Annotation;

/**
 * Class 类的实例表示正在运行的 Java 应用程序中的类和接口
 */
public class ClassTest {
    public static void main(String[] args) {
        try {
            System.out.println("返回与带有给定字符串名的类或接口相关联的 Class 对象 : " + Class.forName("com.classTest.classload.CTest"));
            System.out.println("以 String 的形式返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称 : " + Class.forName("com.classTest.classload.CTest").getName());
            System.out.println("获取此类的包 : " + Class.forName("com.classTest.classload.CTest").getPackage());
            System.out.println("使用给定的类加载器，返回与带有给定字符串名的类或接口相关联的 Class 对象 : " + Class.forName("com.classTest.classload.CTest", true, ClassLoader.getSystemClassLoader()));
            System.out.println("返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的超类的 Class : " + Class.forName("com.classTest.classload.CTest").getSuperclass());
            System.out.println("返回源代码中给出的底层类的简称 : " + Class.forName("com.classTest.classload.CTest").getSimpleName());
            System.out.println("获取此类的标记 : " + Class.forName("com.classTest.classload.CTest").getSigners());

            CTest ct = new CTest();
            System.out.println("如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null : " + ct.annotationType());
            System.out.println("返回 Java Language Specification 中所定义的底层类的规范化名称 : " + Class.forName("com.classTest.classload.CTest").getCanonicalName());
            System.out.println("返回一个包含某些 Class 对象的数组，这些对象表示属于此 Class 对象所表示的类的成员的所有公共类和接口 : " + Class.forName("com.classTest.classload.CTest").getClasses());
            System.out.println("返回该类的类加载器 : " + Class.forName("com.classTest.classload.CTest").getClassLoader());
            System.out.println("返回表示数组组件类型的 Class : " + Class.forName("com.classTest.classload.CTest").getComponentType());

            System.out.println("返回一个 Constructor 对象，它反映此 Class 对象所表示的类的指定公共构造方法 : " + Class.forName("com.classTest.classload.CTest"));
            System.out.println("返回一个包含某些 Constructor 对象的数组，这些对象反映此 Class 对象所表示的类的所有公共构造方法 : " + Class.forName("com.classTest.classload.CTest").getConstructors().length);

            System.out.println(" 返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段 : " + Class.forName("com.classTest.classload.CTest").getDeclaredField("age").getName());
            System.out.println("返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段 : " + Class.forName("com.classTest.classload.CTest").getDeclaredFields().length);
            System.out.println("返回一个 Field 对象，它反映此 Class 对象所表示的类或接口的指定公共成员字段 : " + Class.forName("com.classTest.classload.CTest").getField("str"));
            System.out.println("返回一个包含某些 Field 对象的数组，这些对象反映此 Class 对象所表示的类或接口的所有可访问公共字段 : " + Class.forName("com.classTest.classload.CTest").getFields().length);

            System.out.println("返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法 : " + Class.forName("com.classTest.classload.CTest").getDeclaredMethod("annotationType").getName());
            System.out.println("返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法 : " + Class.forName("com.classTest.classload.CTest").getDeclaredMethods().length);
            System.out.println("如果此 Class 对象表示某一方法中的一个本地或匿名类，则返回 Method 对象，它表示底层类的立即封闭方法 : " + Class.forName("com.classTest.classload.CTest").getEnclosingMethod());
            System.out.println("返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法 : " + Class.forName("com.classTest.classload.CTest").getMethod("annotationType"));
            System.out.println("返回一个包含某些 Method 对象的数组，这些对象反映此 Class 对象所表示的类或接口（包括那些由该类或接口声明的以及从超类和超接口继承的那些的类或接口）的公共 member 方法 : " + Class.forName("com.classTest.classload.CTest").getMethods().length);

            System.out.println("返回此类或接口以整数编码的 Java 语言修饰符 : " + Class.forName("com.classTest.classload.CTest").getModifiers());


            System.out.println("如果此 Class 对象表示一个注释类型则返回 true : " + Class.forName("com.classTest.classload.ETest").isAnnotation());
            System.out.println("当且仅当底层类是匿名类时返回 true : " + Class.forName("com.classTest.classload.ETest").isAnonymousClass());
            System.out.println("判定此 Class 对象是否表示一个数组类 : " + Class.forName("com.classTest.classload.ETest").isArray());
            System.out.println("判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口 : " + Class.forName("com.classTest.classload.ETest").isAssignableFrom(Class.forName("com.classTest.classload.ETest")));
            System.out.println("当且仅当该类声明为源代码中的枚举时返回 true : " + Class.forName("com.classTest.classload.ETest").isEnum());
            System.out.println("判定指定的 Object 是否与此 Class 所表示的对象赋值兼容 : " + Class.forName("com.classTest.classload.CTest").isInstance(Class.forName("com.classTest.classload.CTest").newInstance()));
            System.out.println("判定指定的 Class 对象是否表示一个接口类型 : " + Class.forName("com.classTest.classload.CTest").isInterface());
            System.out.println("当且仅当底层类是本地类时返回 true : " + Class.forName("com.classTest.classload.CTest").isLocalClass());
            System.out.println("当且仅当底层类是成员类时返回 true : " + Class.forName("com.classTest.classload.CTest").isMemberClass());
            System.out.println("判定指定的 Class 对象是否表示一个基本类型 : " + Class.forName("com.classTest.classload.CTest").isPrimitive());
            System.out.println("如果此类是复合类，则返回 true，否则 false : " + Class.forName("com.classTest.classload.CTest").isSynthetic());
            System.out.println("创建此 Class 对象所表示的类的一个新实例 : " + Class.forName("com.classTest.classload.CTest").newInstance().toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class CTest implements Annotation{
    private int age;
    private String name;
    public String str;

    public Class<? extends Annotation> annotationType() {
        return CTest.class;
    }

    @Override
    public String toString() {
        return "CTest{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", str='" + str + '\'' +
                '}';
    }
}

enum ETest{
    CN,
    EN
}

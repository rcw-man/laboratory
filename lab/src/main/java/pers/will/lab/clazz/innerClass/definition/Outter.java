package pers.will.lab.clazz.innerClass.definition;

public class Outter {

    private double attr = 1;

    public void output() {
        System.out.println("A circle");
    }

    public Inner getInnerInstance() {
        return new Inner();
    }

    public void descInner() {
        /**
         * 成员内部类可以无条件地访问外部类的成员，而外部类想访问成员内部类的成员却不是这么随心所欲了
         * 在外部类中如果要访问成员内部类的成员，必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问
         */
        getInnerInstance().desc();
    }


    /**
     * 成员内部类，看起来像是类Outter的一个成员；与之对应的，Outter.class称为外部类；
     */
    class Inner {
        public void desc() {
            System.out.println("drawshape");
            /** 成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员） */
            System.out.println("attr" + attr);
        }

        /**
         * 当成员内部类拥有和外部类同名的成员变量或者方法时，会发生隐藏现象，即默认情况下访问的是成员内部类的成员
         * 如果要访问外部类的同名成员，需要以下面的形式进行访问
         */
        public void output() {
            Outter.this.output();
        }
    }

    /**
     * 内部类可以拥有private访问权限、protected访问权限、public访问权限及包访问权限；
     * 和外部类略有不同；外部类只能被public和包访问两种权限修饰。个人理解，成员内部类像外部类的成员，所以可以像类的成员一样拥有多种权限修饰。
     * 1.如上面的例子是默认访问权限，则只能在同一个包下访问;
     * 2.其他三种情况参见下面几个声明；
     */
    public class InnerPublic {
        /** 如果用public修饰，则任何地方都能访问；如果用protected修饰，则只能在同一个包下或者继承外部类的情况下访问；*/
    }

    private class InnerPrivate {
        /** 成员内部类Inner用private修饰，则只能在外部类的内部访问 */
    }

    protected class InnerProtected {
        /** 如果用protected修饰，则只能在同一个包下或者继承外部类的情况下访问；*/
    }


    public static void main(String[] args) {
        /**
         * 成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象。创建成员内部类对象的一般方式如下
         */
        //第一种方式：
        Outter outter = new Outter();
        Outter.Inner inner = outter.new Inner();  //必须通过Outter对象来创建

        //第二种方式：
        Outter.Inner inner1 = outter.getInnerInstance();
    }
}

package pers.will.lab.memory.jmm.reference;

/**
 * 暂时没用到
 */
public class ObjectDescriptor {

    private String stack; // 栈值
    private String heap; // 堆值

    public void compare(ObjectDescriptor target) {
        if (target == null) {
            System.out.println("Target无效，NULL");
        }
        stack.equals(target.stack);
    }

    private void equals() {

    }
}

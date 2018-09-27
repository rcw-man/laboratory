package pers.will.lab.data.structure.collection.array;

public class Capacity {

    public static void main(String[] args) {
        int[] arr1 = new int[2];

        /** 第一种方法 */
        int[] arr2 = new int[arr1.length * 2]; //新数组长度
        for (int i = 0; i < arr1.length; i++) { //复制
            arr2[i] = arr1[i];
        }

        /** 第二种方法：java.util.Arrays.copyOf(原数组名,新数组长度) */
        int[] arr3 = java.util.Arrays.copyOf(arr1, 5);

        /** 第三种方法：System.arraycopy(原数组名,起始下标,新数组名,起始下标,复制长度) */
        int[] arr4 = new int[arr1.length * 2];
        System.arraycopy(arr1, 0, arr4, 0, arr1.length);

        /** 其他参考资料 */

    }
}

package cn.gst.mvp.mvpdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.gst.mvp.mvpdemo.R;

public class StartViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view_group);
    }


    /**
     * * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * * 使得所有奇数位于数组的前半部分，所有偶数位予数组的后半部分。
     * *
     * * @param arr 输入的数组
     * */
 public static void reorderOddEven(int[] arr){
     //对于输入的数组为空，返回
     if (arr.length < 0 || arr == null){
         return;
     }
     //从左向右记录偶数的位置
     int start = 0;
     //从右向左记录奇数的位置
     int end = 0;
     //开始调整奇数和偶数的位置
     while (start < end){
         while (start < end && arr[start] / 2 != 0){
             start ++;
         }
         while (start < end && arr[end] / 2 == 0){
             end--;
         }

         // 找到后就将奇数和偶数交换位置
         // 对于start=end的情况，交换不会产生什么影响
        // 所以将if判断省去了
        int temp = arr[start];
         arr[start] = arr[end];
         arr[end] = temp;
     }
 }

    /**
     * 判断一个是时候为回文数字
     * 在O（1）空间内完成
     * 题目要求只能用O(1)的空间，所以不能考虑把它转化为字符串然后reverse比较的方法。
     基本思路是每次去第一位和最后一位，如果不相同则返回false，否则继续直到位数为0。
     需要注意的点:
     负数不是回文数字.
     0是回文数字.
     http://blog.csdn.net/wcqwcq123/article/details/534087892
     * @param x
     * @return
     */
    public boolean isPalindrome(int x){
        if(x < 0)
            return false;
        int div = 1;
        while (div < x / 10)
            div *= 10;
        while (x > 0){
            if (x / div != x %10)
                return  false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }



    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则返回true。否则返回false。假设输入的数组的任意两个数字都互不相同。
     *
     * @param sequence 某二叉搜索树的后序遍历的结果
     * @return true：该数组是某二叉搜索树的后序遍历的结果。false：不是
     */
    public static boolean verifySequenceOfBST(int[] sequence) {

        // 输入的数组不能为空，并且有数据
        if (sequence == null || sequence.length <= 0) {
            return false;
        }

        // 有数据，就调用辅助方法
        return verifySequenceOfBST(sequence, 0, sequence.length - 1);
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * @param sequence 某二叉搜索树的后序遍历的结果
     * @param start    处理的开始位置
     * @param end      处理的结束位置
     * @return true：该数组是某二叉搜索树的后序遍历的结果。false：不是
     */
    public static boolean verifySequenceOfBST(int[] sequence, int start, int end) {

        // 如果对应要处理的数据只有一个或者已经没有数据要处理（start>end）就返回true
        if (start >= end) {
            return true;
        }

        // 从左向右找第一个不小于根结点（sequence[end]）的元素的位置
        int index = start;
        while (index < end - 1 && sequence[index] < sequence[end]) {
            index++;
        }

        // 执行到此处[start, index-1]的元素都是小于根结点的（sequence[end]）
        // [start, index-1]可以看作是根结点的左子树

        // right用于记录第一个大于根结点的元素的位置

        int right = index;

        // 接下来要保证[index, end-1]的所有元素都是大于根根点的值
        // 因为[index, end-1]是根结点的右子树
        // 从第一个不小于根结点的元素开始，找第一个不大于根结点的元素
        while (index < end - 1 && sequence[index] > sequence[end]) {
            index++;
        }
        // 如果[index, end-1]中有小于等于根结点的元素，
        // 不符合二叉搜索树的定义，返回false
        if (index != end - 1) {
            return false;
        }

        // 执行到此处说明直到目前为止，还是合法的
        // [start, index-1]为根结点左子树的位置
        // [index, end-1]为根结点右子树的位置
        index = right;
        return verifySequenceOfBST(sequence, start, index - 1) && verifySequenceOfBST(sequence, index, end - 1);
    }


}

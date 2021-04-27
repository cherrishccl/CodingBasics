package com.boot.basics.coding.algorithm;

/**
 * @Author cherrishccl
 * @Date 2021/4/21 17:40
 * @Version 1.0
 * @Description FastSort
 */
public class QuickSort implements Sort{
    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right){
        // 如果left等于right, 即只有一个元素, 直接返回
        if(left >= right){
            return;
        }
        // 设置最左边的元素为基准值
        int radix = arr[left];
        // 数组中比radix小的值放左边, 比radix大的值放右边, radix的下标为i
        int i = left;
        int j = right;
        while (i != j){
            //j向左移，直到遇到比radix小的值
            while (arr[j] >= radix && i < j){
                j--;
            }
            //i向右移，直到遇到比radix大的值
            while(arr[i] <= radix && i < j){
                i++;
            }
            // 交换元素
            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                /*int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;*/
                swap(arr, i, j);
            }
        }
        arr[left] = arr[i];
        arr[i] = radix;
        //count++;
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
}

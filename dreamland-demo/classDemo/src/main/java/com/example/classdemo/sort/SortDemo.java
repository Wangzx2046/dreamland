package com.example.classdemo.sort;

import java.util.ArrayList;

public class SortDemo {
    public static void main(String[] args) {

    }

    //插入
    public static void insertSort(int[] a) {
        int length = a.length;
        for (int i = 1; i < length; i++) {
            int insertNum = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > insertNum) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = insertNum;
        }
    }
    //希尔
    public static void sheelSort(int a[]) {
        int length = a.length;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; i++) {
                int insertNum = a[i];
                int j = i - gap;
                while (j >= 0 && a[j] > insertNum) {
                    a[j + gap] = a[j];
                    j -= gap;
                }
                a[j + gap] = insertNum;
            }
        }
    }

    //冒泡
    public static void bubble(int[] a) {
        int length = a.length;
        int temp;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
    //简单选择排序
    public static void selectSort(int[] a) {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            //记录当前最小数和位置
            int key = a[i];
            int position = i;
            for (int j = i + 1; j < length; j++) {
                if (key > a[j]) {
                    key = a[j];
                    position = j;
                }
            }
            //交换
            a[position] = a[i];
            a[i] = key;
        }
    }

    //快速排序
    public static void quickSort(int[] a, int start, int end) {
        if (a == null || start >= end) return;

        int key = a[start];
        int i = start, j = end;

        while (i < j) {
            while (i < j && a[j] >= key) {
                j--;
            }
            if (i < j) {
                a[i++] = a[j];
            }
            while (i < j && a[i] <= key) {
                i++;
            }
            if (i < j) {
                a[j--] = a[i];
            }
        }
        a[i] = key;
        quickSort(a, start, i - 1);
        quickSort(a, i + 1, end);
    }

    //基数排序
    public static void radixSort(int[] a) {
        int queueNum = 10;
        int length = a.length;
        //找到最大数，判断位数
        int max = a[0];
        for (int i = 1; i < length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        int times = 0;
        while (max > 0) {
            max /= 10;
            times++;
        }
        //初始化10个队列
        ArrayList<ArrayList> queue = new ArrayList<>();
        for (int i = 0; i < queueNum; i++) {
            ArrayList<Integer> queue1 = new ArrayList<>();
            queue.add(queue1);
        }

        for (int i = 0; i < times; i++) {
            //分配数组元素
            for (int j = 0; j < length; j++) {
                //得到位数
                int x = a[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(a[j]);
                queue.set(x, queue2);
            }
            //记录元素数
            int count = 0;
            //收集队列元素
            for (int k = 0; k < queueNum; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    a[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }

    }

    //堆排序
//    public static void heapSort(int[] a) {
//        int length = a.length;
//        for (int i = 0; i < length - 1; i++) {
//            adjustHeap(a, length - i - 1);
//            swap(a, 0, length - i - 1);
//        }
//    }
//
//    public static void adjustHeap(int[] a, int lastIndex) {
//        for (int i = (lastIndex - 1) / 2; i >= 0 ; i--) {
//            int k = i;
//            while (2*k + 1 <= lastIndex) {
//                int left = 2*k + 1;
//                if (left < lastIndex && a[left] < a[left + 1]) {
//                    left++;
//                }
//                if (a[k] >= a[left]) break;
//                swap(a, k, left);
//                k = left;
//            }
//
//        }
//        System.out.println("调整后" + Arrays.toString(a));
//    }

    //归并排序
    public static void mergeSortTwo(int[] a, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSortTwo(a, start, mid);
            mergeSortTwo(a, mid + 1, end);
            mergeTwo(a, start, mid, end);
        }
    }
    public static void mergeTwo(int[] a, int start, int mid, int end) {
        int[] temp = new int[a.length];
        int p1 = start, p2 = mid + 1, k = start;
        while (p1 <= mid && p2 <= end) {
            if (a[p1] <= a[p2]) {
                temp[k++] = a[p1++];
            } else {
                temp[k++] = a[p2++];
            }
        }
        while (p1<=mid) {
            temp[k++] = a[p1++];
        }
        while (p2<=end) {
            temp[k++] = a[p2++];
        }
        for (int i = start; i <= end; i++) {
            a[i] = temp[i];
        }
    }

}

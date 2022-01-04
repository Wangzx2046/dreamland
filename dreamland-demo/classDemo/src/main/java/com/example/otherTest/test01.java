package com.example.otherTest;

/**
 * &运算符有两种用法：(1)按位与；(2)逻辑与。
 *
 * &&运算符是短路与运算。逻辑与跟短路与的差别是非常巨大的，虽然二者都要求运算符左右两端的布尔值都是true 整个表达式的值才是 true。&&之所以称为短路运算，是因为如果&&左边的表达式的值是 false，右边的表达式会被直接短路掉，不会进行运算。
 *
 * 注意：逻辑或运算符（|）和短路或运算符（||）的差别也是如此。
 */
public class test01 {
    public static void main(String[] args) {

        final boolean b = test1() & test2();
    }


    static boolean test1() {
        System.out.println("test1");
        return false;
    }

    static boolean test2() {
        System.out.println("test2");
        return true;
    }
}

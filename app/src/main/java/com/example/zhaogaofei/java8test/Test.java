package com.example.zhaogaofei.java8test;

import android.util.Log;

public class Test {
    private TestInner testInner;
    private TestInner2 testInner2;
    private TestInner3 testInner3;

    public Test() {}

    public Test(TestInner inner, int b) {
        this.testInner = inner;
    }

    public Test(TestInner2 inner) {
        this.testInner2 = inner;
    }

    public Test(TestInner3 inner) {
        this.testInner3 = inner;
    }

    public Test(TestInner2 inner2, TestInner3 inner3) {
        this.testInner2 = inner2;
        this.testInner3 = inner3;
    }

    public static void twoFiledTest(int aaa, int bbb) {
        Log.e("zgf", "========" + aaa + "=====" + bbb);
    }

    public static void aaa() {
        System.out.println("=====hahahaha======");
    }

    public static void bbb(int b) {
        System.out.println("=====hahahaha======");
    }

    public static int sum0(int a, int b) {
        return a + b;
    }

    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static void ccc(int[] ccc) {

    }

    public void ddd(int[] ccc) {

    }

    public int eee() {
        return 0;
    }

    public void print() {
        System.out.println("=====hahahaha======");
    }

    public void print(int a) {
        System.out.println("=====hahahaha======");
    }

    public void test() {
        if (testInner != null) {
            testInner.testFiled(1, 3);
        }

        if (testInner2 != null) {
            testInner2.testFiled(1);
        }

        if (testInner3 != null) {
            int sum = testInner3.sum(2, 3, 4);
            Log.e("zgf", "=====sum======" + sum);
        }
    }

    public interface TestInner {
        void testFiled(int a, int b);
    }

    public interface TestInner2 {
        void testFiled(int a);
    }

    public interface TestInner3 {
        int sum(int a, int b, int c);
    }
}

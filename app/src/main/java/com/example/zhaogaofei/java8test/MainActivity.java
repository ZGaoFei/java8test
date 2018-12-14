package com.example.zhaogaofei.java8test;

import java.io.File;
import java.io.FileFilter;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 闭包或者叫匿名方法
 *
 * lambda一个对应一个接口
 */
public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        textView.setOnClickListener(v -> test());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void test() {
        test1();

        test2();
    }

    private void test1() {
        textView.post(new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "=====1=======");
            }
        });

        textView.post(()-> Log.e("zgf", "======2======"));

        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("zgf", "======3======");
            }
        }, 1000);

        textView.postDelayed(()-> Log.e("zgf", "======4======")
        , 1000);

        Thread thread = new Thread(()-> Log.e("zgf", "======5======"));

        new Test(new Test.TestInner() {
            @Override
            public void testFiled(int a, int b) {
                Log.e("zgf", "======6======");
            }
        }, 10).test();

        new Test(new Test.TestInner2() {
            @Override
            public void testFiled(int a) {
                Log.e("zgf", "======7======");
            }
        }).test();

        new Test(a1->Log.e("zgf", "======8======")).test();

        new Test((a, b) -> Log.e("zgf", "======9======"), 10).test();

        new Test((a, b) -> {
            Log.e("zgf", "======9======");
            Log.e("zgf", "======9======");
            Log.e("zgf", "======9======");
            Log.e("zgf", "======9======");
        }, 10).test();

        new Test(new Test.TestInner3() {
            @Override
            public int sum(int a, int b, int c) {
                return 0;
            }
        }).test();

        new Test(((a, b, c) -> Test.sum(a, b, c))).test(); // 三个参数，带返回值

        new Test(((int a, int b, int c) -> a + b + c)).test(); // 三个参数，带返回值

        new Test(((a, b, c) -> {
            a = 10;
            return a + b + c;
        })).test(); // 三个参数，带返回值

        new Test(new Test.TestInner2() {
            @Override
            public void testFiled(int a) {
                Log.e("zgf", "======10======");
            }
        }, new Test.TestInner3() {
            @Override
            public int sum(int a, int b, int c) {
                return a + b + c;
            }
        }).test();

        new Test(a -> Log.e("zgf", "======11======"), (a, b, c) -> a + b + c).test();

        Test.TestInner inner = (a, b) -> Log.e("zgf", "======11======");

        FileFilter java = f -> f.getName().endsWith("*.java");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void test2() {
        new AlertDialog.Builder(this).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        }).setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        }).create().show();


        new AlertDialog.Builder(this)
                .setNegativeButton("cancel", (dialog, which)-> Log.e("zgf", "============"))
                .setPositiveButton("ok", (dialog, which)-> Log.e("zgf", "============"))
                .setOnDismissListener(dialog -> Log.e("zgf", "============"))
                .setOnKeyListener((dialog, keyCode, event) -> false) // 带有参数的类型
                .create()
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void test3() {
        Test test = new Test();

        // 无参实例方法
        Runnable print = test::print;
        print.run();

        // 有参实例方法
        Consumer<Integer> a = test::print;
        a.accept(10);

        // 无参静态方法
        Runnable aaa = Test::aaa;
        aaa.run();

        // 有参静态方法
        Consumer<Integer> bbb = Test::bbb;
        bbb.accept(10);

        Comparator<Integer> sum0 = Test::sum0;

        Consumer<int[]> ccc = Test::ccc;
        ccc.accept(new int[]{1, 0, 2});

        Consumer<int[]> ddd = test::ddd;
        ccc.accept(new int[]{1, 0, 2});

        Runnable eee = test::eee;
        eee.run();

        // 构造方法
        Runnable aNew = Test::new;
        aNew.run();
    }
}

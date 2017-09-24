package com.example.hx;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by dell on 2017/5/26.
 */

public class MyThread {
    private static Handler handler=new Handler(Looper.getMainLooper());

    public static  void getUIThread(Runnable task){
        handler.post(task);
    }
    public static  void getShThread(Runnable task){
        new Thread(task).start();
    }

}

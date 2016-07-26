package com.zxy.parities.service;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class TaskWithResult implements Callable {
        private int id;

        public TaskWithResult(int id) {
         this.id = id;
     }

        public String call() {
            return "result of TaskWithResult" + id;
        }
     }
/**
 * Created by zxy on 2016/7/22.
 */
public class CallableDemo {
    public static void main(String[]args){
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> result =new ArrayList<Future<String>>();


    }
}


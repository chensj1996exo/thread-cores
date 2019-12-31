package com.bistu.thread.cores.collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class UseCopyOnWrite {

    public static void main(String[] args){

        CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<>();

        cwal.add("A");
    }
}

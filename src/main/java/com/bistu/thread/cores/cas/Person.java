package com.bistu.thread.cores.cas;

public class Person {

    private int age;
    private String name;

    public Person(){

    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public String toString(){
        return "[name: " + this.name + ", age: " + this.age + "]";
    }
}

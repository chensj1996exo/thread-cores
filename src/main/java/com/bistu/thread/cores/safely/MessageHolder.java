package com.bistu.thread.cores.safely;

import java.util.ArrayList;
import java.util.List;

public class MessageHolder {

    private List<String> messages = new ArrayList<String>();

    public static final ThreadLocal<MessageHolder> holder = new ThreadLocal(){
        protected Object initialValue(){
            return new MessageHolder();
        }
    };

    public static void add(String message){
        holder.get().messages.add(message);
    }

    public static List<String> clear(){
        List<String> result = holder.get().messages;
        holder.remove();
        System.out.println("size:" + holder.get().messages.size());
        return result;
    }

    public static void main(String[] args) {
        MessageHolder.add("1111");
        MessageHolder.add("2222");

        List<String> result = MessageHolder.clear();
        System.out.println(result.toString());
    }
}

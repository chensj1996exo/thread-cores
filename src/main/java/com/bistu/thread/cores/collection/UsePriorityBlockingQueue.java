package com.bistu.thread.cores.collection;


import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {

    public static void main(String[] args) throws Exception{
        PriorityBlockingQueue<Node> pbq = new PriorityBlockingQueue<Node>();

        Node n1 = new Node(1, "node1");
        Node n2 = new Node(2, "node2");
        Node n3 = new Node(3, "node3");
        Node n4 = new Node(4, "node4");

        pbq.add(n4);
        pbq.add(n2);
        pbq.add(n1);
        pbq.add(n3);

        //执行一次take操作就会进行一次比较，将优先级最高的元素放在句首，其余的元素不进行排序操作。
        //避免了在进行take操作时，如果需要添加元素时，需要重新排序，浪费时间。

        System.out.println("0 元素内容：" + pbq);
        System.out.println("1 删除元素：" + pbq.take().getId());
        System.out.println("1 元素内容：" + pbq);
        System.out.println("2 删除元素：" + pbq.take().getId());
        System.out.println("2 元素内容：" + pbq);
        System.out.println("3 删除元素：" + pbq.take().getId());
        System.out.println("3 元素内容：" + pbq);
        System.out.println("4 删除元素：" + pbq.take().getId());

    }


}

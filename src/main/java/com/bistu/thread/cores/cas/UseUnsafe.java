package com.bistu.thread.cores.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

public class UseUnsafe {

    private static int byteArrayBaseOffset;

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalAccessException{

        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);

        Unsafe UNASFE = (Unsafe) theUnsafe.get(null);
        System.out.println(UNASFE);

        byte [] data = new byte[10];
        System.out.println(Arrays.toString(data));

        byteArrayBaseOffset = UNASFE.arrayBaseOffset(byte[].class);
        System.out.println("byte[]数组的第一个元素的偏移量： " + byteArrayBaseOffset);

        // 设置指定的字节数组，修改其内存字段的位置
        // 1.在data 这个数组对象设置第一个位置为1
        UNASFE.putByte(data, byteArrayBaseOffset, (byte)1);
        // 2.在data 这个数组对象设置第六个位置为8
        UNASFE.putByte(data, byteArrayBaseOffset+6, (byte)8);

        System.out.println(Arrays.toString(data));


    }
}

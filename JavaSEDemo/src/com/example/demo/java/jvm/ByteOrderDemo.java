package com.example.demo.java.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 验证小端存储：高字节存储在高地址中
 */
public class ByteOrderDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        /**
         * 获取Unsafe对象
         */
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        /**
         * 分配8字节内存，将一个8字节的long类型数据放在该内存上
         */
        long memory = unsafe.allocateMemory(8);
        unsafe.putLong(memory,0x0102030405060708L);

        /**
         * 取出低地址的第一个字节数据
         */
        byte first = unsafe.getByte(memory);
        System.out.println(first);
    }
}

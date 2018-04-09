package com.chenhl.juc.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

    private static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Get Unsafe instance occur error "  + e);
        }
    }

    public static void main(String[] args) throws Exception {
        final Class<Target> clazz = Target.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println("fieldName:fieldOffset");
        for (Field f : declaredFields) {
//            获取属性偏移量，可以通过这个偏移量给属性设置
            System.out.println(f.getName() + ": " + unsafe.objectFieldOffset(f));
        }
        Target target = new Target();
        Field intParam = clazz.getDeclaredField("intParam");
        int a = (Integer) intParam.get(target);
        System.out.println(intParam.getName() + "原始值: " + a);//3
        System.out.println(unsafe.compareAndSwapInt(target, 12, 3, 10));//true
        int b = (Integer) intParam.get(target);
        System.out.println(intParam.getName() + "修改后的值: " + b);//10

        System.out.println(unsafe.compareAndSwapInt(target, 12, 3, 10));//false

        System.out.println(unsafe.compareAndSwapObject(target, 24, null, "5"));//true

    }

}
class Target {
    int intParam = 3;//12
    long longParam;//16
    String strParam1;//24
    String strParam2;//28
}
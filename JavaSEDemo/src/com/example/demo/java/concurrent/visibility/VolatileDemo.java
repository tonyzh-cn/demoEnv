package com.example.demo.java.concurrent.visibility;

/**
 * 验证写volatile变量时的lock指令
 * -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*VolatileDemo.refresh
 */
public class VolatileDemo {
    //循环的终止条件，加volatile会影响运行结果
    private static volatile boolean initFlag = false;

    private  static int counter = 0;

    public static void refresh(){
        System.out.println("refresh data.......");
        initFlag = true;
        System.out.println("refresh data success.......");
    }

    public static void main(String[] args){
        //线程A负责循环，直至收到终止的信令才会停下来
        Thread threadA = new Thread(()->{
            while (!initFlag){
                //System.out.println("runing");
                counter++;
            }
            System.out.println("线程：" + Thread.currentThread().getName()
                    + "当前线程嗅探到initFlag的状态的改变");
        },"threadA");
        threadA.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //b线程负责通知A终止循环
        Thread threadB = new Thread(()->{
            refresh();
        },"threadB");
        threadB.start();
    }
}

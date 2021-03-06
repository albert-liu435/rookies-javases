package com.rookie.bigdata.stringbuild;

/**
 * @ClassName StringBuilderDemo
 * @Description StringBuilderDemo
 * @Author
 * @Date 2020/4/16 9:35
 * @Version 1.0
 */
public class StringBuilderDemo {
    public static void main(String[] args) throws Exception{
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10000;j++){
                        stringBuilder.append("a");
                    }
                }
            }).start();
        }

        Thread.sleep(100);
        System.out.println(stringBuilder.length());

        StringBuffer stringBuffer=new StringBuffer();
        String s=null;
        stringBuffer.append(s).append("h");
        System.out.println(stringBuffer);


    }
}

package test;

import java.io.File;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
//        File file = new File("E:\\Android\\huanguares\\app\\src\\main\\res");
//        fun(file);
    }

    private static void fun(File file) {
        if (file.isFile() && file.getName().endsWith(".xml")) {
            file.delete();
            System.out.println(file.getName());
        }
        else if (file.isDirectory()){
            for (File fp : file.listFiles()) {
                fun(fp);
            }
        }
    }
}
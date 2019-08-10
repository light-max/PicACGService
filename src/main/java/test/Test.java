package test;

import java.io.*;

public class Test {
    static int size = 0;

    public static void main(String[] args) {
        File file = new File("E:\\Project\\20190714yanshiapp_avluba");
        fun(file);
        System.out.println(size);
    }

    static void fun(File file) {
        if (file.getName().equals("test") || file.getName().equals("androidTest")) {
            return;
        }
        if (file.isFile()) {
            fun2(file);
        } else {
            for (File fp : file.listFiles()) {
                fun(fp);
            }
        }
    }

    static void fun2(File file) {
        String name = file.getName();
        if (name.endsWith(".java") && !name.equals("R.java") && !name.equals("BuildConfig.java")) {
            try {
                InputStream in = new FileInputStream(file);
                int value;
                int sz = 0;
                while ((value = in.read()) != -1) {
                    if (value == '\n') {
                        sz++;
                    }
                }
                System.out.println(file.getName() + ":" + sz);
                size += sz;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

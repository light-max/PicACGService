import java.io.*;

public class LineNumber {

    static File file = new File("E:\\IDEA\\PicACGService\\src\\main\\java");

    static int size = 0, lines = 0;

    public static void main(String[] args) {
        fun(file);
        fun(new File("E:\\IDEA\\PicACGService\\src\\main\\webapp\\css"));
        fun(new File("E:\\IDEA\\PicACGService\\src\\main\\webapp\\js"));
        fun(new File("E:\\IDEA\\PicACGService\\src\\main\\webapp\\WEB-INF\\jsp"));
        System.out.println("size:" + size + " lines:" + lines);
    }

    static void fun(File file) {
        if (file.isFile()) {
            read(file);
        } else {
            if (file.getName().equals("test")) {
                return;
            }
            if (file.getName().equals("jquery")) {
                return;
            }
            for (File fp : file.listFiles()) {
                fun(fp);
            }
        }
    }

    static void read(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String string;
            int size = 0, line = 0;
            while ((string = reader.readLine()) != null) {
                size += string.length();
                line++;
            }
            System.out.println(file.getName() + ":" + size + ":" + line);
            LineNumber.size += size;
            LineNumber.lines += line;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

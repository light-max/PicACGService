package test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTest {

    private static File file = new File("F:\\PicACG\\submission\\image\\2\\6\\10.jpg");

    public static void main(String[] args) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.0.104:8080/content/image").openConnection();
        int length = connection.getContentLength();
        System.out.println(length);
        InputStream in = connection.getInputStream();
        OutputStream out = new FileOutputStream(file);
        int value;
        while ((value = in.read()) != -1) {
            out.write(value);
        }
        out.flush();
        out.close();
        in.close();
    }

}

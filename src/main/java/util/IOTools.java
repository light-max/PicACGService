package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOTools {

    private final static int BUFFER_SIZE = 1024;

    /**
     * 拷贝流中的数据，自动关闭流
     *
     * @param out
     * @param in
     * @return
     */
    public static void copy(OutputStream out, InputStream in) throws IOException {
        byte[] aByte = new byte[BUFFER_SIZE];
        int len;
        while ((len = in.read(aByte)) != -1) {
            out.write(aByte, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }
}

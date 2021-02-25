package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 用于字符串的一些操作
 */
public class StringTools {

//    public static final String url = "http://192.168.0.104:8080";

    private static String url = null;

    public static String getUrl() {
        if (url == null) {
            try {
                url = String.format("http://%s:8080", InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            System.out.println("端口号：" + url);
        }
        return url;
    }


    /**
     * 验证登录名是否合法
     *
     * @param string 小于等于16位大于等于4位，由数字字母字符{_@.}组成
     * @return
     */
    public static boolean name(String string) {
        if (string.length() > 16 || string.length() < 4) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '_' || c == '@' || c == '.') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * 验证密码是否合法
     *
     * @param string 小于等于16位大于等于6位，由数字字母 字符{._*@}组成
     * @return
     */
    public static boolean password(String string) {
        if (string.length() > 16 || string.length() < 6) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '_' || c == '@' || c == '.' || c == '*') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                continue;
            }
            return false;
        }
        return true;
    }
}

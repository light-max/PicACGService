package util;

/**
 * 用于字符串的一些操作
 */
public class StringTools {

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

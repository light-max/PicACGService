import java.util.HashSet;

/**
 * 字符串100100110011001111101010101001011111111000中可以分割成多少种不同的组合？
 * 分割的字符串必须是连续的，比如1001可以分割成100 00 001，但不可以分割成101。
 * 输出能分割成多少种不同的组合，只需要输出一个整数即可
 */
public class SetMain {
    public static void main(String[] args) {
        String ss = "100100110011001111101010101001011111111000";
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < ss.length(); i++) {
            for (int j = i + 1; j < ss.length() + 1; j++) {
                String s = ss.substring(i, j);
                System.out.println(s);
                set.add(s);
            }
        }
        System.out.println("=====================");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println(set.size());
    }
}

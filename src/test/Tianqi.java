import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Tianqi {
    public static void main(String[] args) throws IOException {
        URLConnection conn = new URL("https://a.hecdn.net/download/dev/china-city-list.csv").openConnection();
        Scanner sc = new Scanner(conn.getInputStream());
        String line = null;
        while ((line = sc.nextLine()) != null) {
            System.out.println(line);
        }
    }


}

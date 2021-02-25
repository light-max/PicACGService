import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class Test3 {
    HashMap<String, File> map = new HashMap<>();

    public static void main(String[] args) {
        new Test3().main();
    }

    File root = new File("E:\\Android\\huanguares\\app\\src\\main\\res");

    public void main() {
        fun(root);
        delete(root);
    }

    private void delete(File file) {
        if (file.isFile()) {
            File obj = map.get(file.getName());
            if (obj.equals(file)) {
            } else {
                file.delete();
            }
        } else {
            for (File fp : Objects.requireNonNull(file.listFiles())) {
                delete(fp);
            }
        }
    }

    private void fun(File file) {
        if (file.isFile()) {
            String name = file.getName();
            File obj = map.get(name);
            if (obj == null) {
                map.put(name, file);
            } else {
                if (file.length() > obj.length()) {
                    map.put(name, file);
                }
            }
        } else {
            for (File fp : Objects.requireNonNull(file.listFiles())) {
                fun(fp);
            }
        }
    }

}

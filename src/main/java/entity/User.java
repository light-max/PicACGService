package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    /**
     * 唯一标识符
     */
    private long id;

    /**
     * 登录名
     */
    private String name;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 登录成功后会返回一个key，只有登录名加有效的key才能进行接下来的操作
     */
    private long key;

    public User(@NonNull long id, @NonNull String name, @NonNull String password, @NonNull long key) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.key = key;
    }

    public User(@NonNull String name, @NonNull String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }
}

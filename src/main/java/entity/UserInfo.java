package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfo extends User {
    /**
     * 用户昵称
     */
    @NonNull
    private String nickname;
    /**
     * 用户性别 男1 女2 未知 其他|null
     */
    @NonNull
    private Integer sex;
    /**
     * 个性签名
     */
    @NonNull
    private String word;

    public UserInfo(String nickname, Integer sex, String word) {
        this.nickname = nickname;
        this.sex = sex;
        this.word = word;
    }

    public UserInfo() {
        this("", 0, "");
    }
}

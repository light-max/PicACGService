package model;

import lombok.Data;
import org.json.JSONObject;

/**
 * 作者信息
 */
@Data
public class AuthorInfo {
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别，这里直接用明文显示不用代码显示
     */
    private String sex;

    /**
     * 作品数量
     */
    private int number;

    /**
     * 个人简介
     */
    private String word;

    /**
     * 生成一个头像图片url，没有头像就没有url
     */
    private String img_small;//缩略图
    private String img_source;//完整的图

    public AuthorInfo() {
    }

    public AuthorInfo(String nickname, String sex, int number, String word, String img_small, String img_source) {
        this.nickname = nickname;
        this.sex = sex;
        this.number = number;
        this.word = word;
        this.img_small = img_small;
        this.img_source = img_source;
    }

    public String toJson() {
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("nickname", nickname);
        object.put("sex", sex);
        object.put("number", number);
        object.put("word", word);
        object.put("img_small", img_small);
        object.put("img_source", img_source);
        return object.toString();
    }
}

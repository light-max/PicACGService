package entity;

import lombok.Data;

@Data
public class Submission {
    /**
     * 唯一id
     */
    private long id;

    /**
     * 所属用户id
     */
    private long byuserid;

    /**
     * 标题
     */
    private String title;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 文件数量
     */
    private int number;

    /**
     * 点赞数
     */
    private int star;

    /**
     * 浏览量
     */
    private int watch;

    /**
     * 发布时间
     */
    private long releasetime;


    public Submission(long id, long byuserid, String title, String keyword, String introduction, int number, int star, int watch) {
        this.id = id;
        this.byuserid = byuserid;
        this.title = title;
        this.keyword = keyword;
        this.introduction = introduction;
        this.number = number;
        this.star = star;
        this.watch = watch;
        releasetime = System.currentTimeMillis();
    }

    public Submission() {
        this(0, 0, null, null, null, 0, 0, 0);
    }

    public Submission(long byuserid, String title, String keyword, String introduction, int number) {
        this(0, byuserid, title, keyword, introduction, number, 0, 0);
    }
}

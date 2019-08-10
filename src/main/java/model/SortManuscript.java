package model;

import lombok.Data;

/**
 * 用于存储作品排序的数据的类
 * 里面只包含了字段： id 发布时间 点赞数量 浏览数量
 */
@Data
public class SortManuscript {
    public long id;
    public long releasetime;
    public int star;
    public int watch;

    public SortManuscript() {
    }
}

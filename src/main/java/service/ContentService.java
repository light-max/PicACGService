package service;

import model.Manuscript;

import java.util.List;

public interface ContentService {

    String toJson(List<Manuscript> list);

    /**
     * 随机获取稿件
     *
     * @param number 获取数量，数量超过最大值取最大值
     * @return
     */
    List<Manuscript> rand(int number);

    /**
     * 获取内容，根据id过滤
     *
     * @param number
     * @param filter_id
     * @return
     */
    List<Manuscript> get(int number, List<Long> filter_id);

    /**
     * 获取排序后的内容
     *
     * @param type
     * @param number
     * @param lastid 最后一个id，如果想忽略就填0
     * @return
     */
    List<Manuscript> sort(int type, int number, long lastid);

    /**
     * 获取指定作者的作品
     *
     * @param userid
     * @param number
     * @param lastid
     * @return
     */
    List<Manuscript> get(long userid, int number, long lastid);

    /**
     * 获取指定id的稿件，单个稿件
     *
     * @param id
     * @return
     */
    Manuscript get(long id);
}

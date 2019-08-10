package service;

public interface InteractiveService {

    /**
     * 点赞
     *
     * @param manuscriptid
     * @param name
     * @param key
     * @return
     */
    int star(long manuscriptid, String name, long key);

    /**
     * 增加浏览量
     *
     * @param manuscriptid
     * @param name
     * @param key
     * @return
     */
    int watch(long manuscriptid, String name, long key);

}

package dao;

import org.apache.ibatis.annotations.Param;

public interface WatchMapper {

    /**
     * 查询浏览量
     *
     * @param manuscriptid 作品id号
     * @return
     */
    int selectWatchNumber(@Param("manuscriptid") long manuscriptid);

    /**
     * 这个用户是否已对这个稿件增加过查看量
     *
     * @param userId
     * @param manuscriptid
     * @return
     */
    boolean checkWatch(@Param("userid") long userId, @Param("manuscriptid") long manuscriptid);

    void insert(@Param("userid") long userId, @Param("manuscriptid") long manuscriptid);

    /**
     * 清除浏览量
     *
     * @param manuscriptid
     */
    void clearWatch(@Param("manuscriptid") long manuscriptid);
}

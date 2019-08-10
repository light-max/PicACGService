package dao;

import org.apache.ibatis.annotations.Param;

public interface StarMapper {

    /**
     * 查询点赞数量
     *
     * @param manuscriptid 作品id号
     * @return
     */
    int selectStarNumber(@Param("manuscriptid") long manuscriptid);

    /**
     * 判断这个用户是否对这个稿件点过赞
     *
     * @param userid
     * @param manuscriptid
     * @return
     */
    boolean checkStar(@Param("userid") long userid, @Param("manuscriptid") long manuscriptid);

    int insert(@Param("userid") long userid, @Param("manuscriptid") long manuscriptid);

    /**
     * 清除这个id号的点赞
     *
     * @param manuscriptid
     */
    void clearStar(@Param("manuscriptid") long manuscriptid);
}

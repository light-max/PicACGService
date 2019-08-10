package dao;

import entity.Submission;
import model.Manuscript;
import model.SearchValue;
import model.SortManuscript;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SubmissionMapper {

    void insert(Submission submission);

    @Delete("delete from submission where id=#{id}")
    void deleteById(@Param("id") long id);

    List<Long> selectId();

    Manuscript selectById(@Param("id") long id);

    /**
     * 查询作者的作品数量
     *
     * @param id 作者id
     * @return
     */
    Integer selectNumberByAuthorId(@Param("byuserid") long id);

    /**
     * 给这个作品增加一个点赞数量
     *
     * @param id
     */
    void addStar(@Param("id") long id);

    /**
     * 给这个作品增加一个浏览量
     *
     * @param id
     */
    void addWatch(@Param("id") long id);

    /**
     * 查询所有用于排序的要素
     *
     * @return
     */
    List<SortManuscript> selectSortElementAll();

    /**
     * 获取这个作者所有的作品id
     *
     * @param userid 如果不指定就设置0
     * @return
     */
    List<Long> selectAllByUserId(@Param("byuserid") long userid, @Param("lastid") long lastid);

    /**
     * 根据id查询所属用户id
     *
     * @param id
     * @return
     */
    @Select("select byuserid from submission where id=#{id}")
    Long selectUserIdById(@Param("id") long id);

    /**
     * 更新
     *
     * @param title
     * @param keyword
     * @param introduction
     * @param id
     */
    @Update("update submission set title=#{title},keyword=#{keyword},introduction=#{introduction} where id=#{id}")
    void update(@Param("title") String title, @Param("keyword") String keyword, @Param("introduction") String introduction, @Param("id") long id);

    /**
     * 查找所有标题
     *
     * @return
     */
    List<SearchValue> selectTitleAll();

    /**
     * 查找所有关键字
     *
     * @return
     */
    List<SearchValue> selectKeywordAll();
}

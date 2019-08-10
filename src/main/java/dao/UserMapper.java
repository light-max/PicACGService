package dao;

import entity.User;
import entity.UserInfo;
import model.SearchValue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {

    @Select("select * from user")
    List<User> select();

    @Select("select * from user where name=#{name}")
    User selectByName(@Param(value = "name") String name);

    @Insert("insert into user(name,password) values(#{user.name},#{user.password})")
    void insert(@Param(value = "user") User user);

    @Update("update user set name=#{name} where id=#{id}")
    void updateName(@Param(value = "name") String name, @Param(value = "id") long id);

    @Update("update user set password=#{password} where id=#{id}")
    void updatePassword(@Param(value = "password") String name, @Param(value = "id") long id);

    @Update("update user set nickname=#{info.nickname},sex=#{info.sex},word=#{info.word} where id=#{id}")
    void updateUserInfo(@Param(value = "info") UserInfo info, @Param(value = "id") long id);

    @Select("select nickname from user where id=#{id}")
    String selectNicknameById(@Param(value = "id") long id);

    UserInfo selectUserInfoById(@Param(value = "id") long id);

    /**
     * 查找所有昵称，返回值加上id
     *
     * @return
     */
    List<SearchValue> selectNicknameAll();
}

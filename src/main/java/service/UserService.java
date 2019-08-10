package service;

import entity.UserInfo;
import model.AuthorInfo;

public interface UserService {

    /**
     * 验证账号和密码
     *
     * @param name
     * @param password
     * @return {
     * 0: 账号密码正确
     * 1: 账号未注册
     * 2: 密码错误
     * }
     */
    int verify(String name, String password);

    /**
     * 根据用户名查询id号
     *
     * @param name
     * @return
     */
    long findIdByName(String name);

    /**
     * 注册账号
     *
     * @param name
     * @param password
     * @return 0注册成功, 2账号已存在, 3账号或密码格式错误
     */
    int sign_up(String name, String password);

    /**
     * 修改登录名
     *
     * @param name
     * @param key
     * @return{ 0：修改成功
     * 1: 不合法的用户名
     * 2: 该登录名已被占用
     * }
     */
    int updateName(String name, long key);

    /**
     * 修改密码
     *
     * @param source
     * @param password
     * @param key
     * @return{ 0：修改成功
     * 1: 输入的密码不合法
     * 2: 原密码错误
     * }
     */
    int updatePassword(String source, String password, long key);

    /**
     * 修改个人信息，昵称签名性别什么的
     *
     * @param info
     * @param key
     */
    void updateInfoData(UserInfo info, long key);

    /**
     * 根据key来查找用户数据
     *
     * @param key
     * @return
     */
    UserInfo selectUserInfo(long key);

    /**
     * 查询作者信息，把用户数据已作者信息的形式返回
     *
     * @param id
     * @return
     */
    AuthorInfo selectAuthorInfo(long id);
}

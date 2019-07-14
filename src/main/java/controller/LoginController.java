package controller;

import entity.User;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;
import util.KeyBufferManager;
import util.VerifyCodeTools;

/**
 * 登录相关的接口
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    UserService userService;

    private final String sign_in_message[] = new String[]{
            "登录成功", "账号未注册", "密码错误"
    };
    private final String sign_up_message[] = new String[]{
            "注册成功", "验证码错误", "账号已存在", "账号或密码格式错误"
    };

    /**
     * 登录接口 返回一个json数据，包含了登录状态
     *
     * @param name     登录名
     * @param password 登录密码
     * @return json{
     * code:登录代码 0登录成功, 1账号未注册, 2密码错误
     * message:登录信息
     * key:登录成功会拿到key 重复登录会把原来的key删除掉 原来登录的账号就是未登录状态了
     * }
     */
    @GetMapping(value = "/sign_in")
    public String sign_in(
            @Param(value = "name") String name,
            @Param(value = "password") String password
    ) {
        JSONObject object = new JSONObject();
        int code = userService.verify(name, password);
        //密码正确
        if (code == 0) {
            KeyBufferManager manager = KeyBufferManager.getInstance();
            //获取新的key，如果原来已经有key，则被移除掉
            long key = manager.createKey(name);
            User user = new User(userService.findIdByName(name), name, password, key);
            manager.putKeyAndUser(key, user);
            object.put("key", key);
        }
        object.put("code", code);
        object.put("message", sign_in_message[code]);
        return object.toString();
    }

    /**
     * 注册账号
     *
     * @param name     登录名
     * @param password 密码
     * @param id       验证码的id号
     * @param answer   验证码的答案
     * @return json{
     * code:注册代码 0注册成功, 1验证码错误, 2账号已存在, 3账号或密码格式错误
     * message:注册信息
     * }
     */
    @GetMapping(value = "/sign_up")
    public String sign_up(
            @Param(value = "name") String name,
            @Param(value = "password") String password,
            @Param(value = "id") long id,
            @Param(value = "answer") String answer
    ) {
        VerifyCodeTools util = VerifyCodeTools.getInstance();
        JSONObject object = new JSONObject();
        //默认验证码错误
        int code = 1;
        //验证成功
        if (util.verify(id, answer)) {
            code = userService.sign_up(name, password);
            //注册成功清除验证码
            if (code == 0) {
                util.remove(id);
            }
        }
        object.put("code", code);
        object.put("message", sign_up_message[code]);
        return object.toString();
    }

    /**
     * 账号登出 下线
     *
     * @param name
     * @param key
     */
    @RequestMapping(value = "/sign_out")
    public void sign_out(
            @Param(value = "name") String name,
            @Param(value = "key") long key
    ) {
        KeyBufferManager manager = KeyBufferManager.getInstance();
        //需要验证登录名和key，防止恶意登出
        if (manager.verify(name, key)) {
            manager.removeKey(name);
            manager.removeUser(key);
        }
    }

    /**
     * 生成验证码
     *
     * @return json{
     * id: 验证码的id号
     * problem: 验证码的问题
     * }
     */
    @GetMapping(value = "/create_verify_id")
    public String create_verify_id() {
        VerifyCodeTools util = VerifyCodeTools.getInstance();
        JSONObject object = util.create();
        return object.toString();
    }
}

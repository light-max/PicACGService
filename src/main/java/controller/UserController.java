package controller;

import entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;
import util.KeyBufferManager;

/**
 * 账号信息相关的接口
 * 所有的操作都需要加上登录名和key才能操作 为什么不带上密码？没必要，key就是动态密码
 * 如果提交的信息有误 name和key错误就返回-1
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 修改登录名 修改后账号需要重新登录
     *
     * @param name
     * @param key
     * @param args
     * @return
     */
    @GetMapping(value = "/set_name")
    public String updateName(
            @Param(value = "name") String name,
            @Param(value = "key") long key,
            @Param(value = "args") String args
    ) {
        KeyBufferManager manager = KeyBufferManager.getInstance();
        JSONObject object = new JSONObject();
        if (manager.verify(name, key)) {
            int code = userService.updateName(args, key);
            if (code == 0) {
                //登出
                manager.removeKey(name);
                manager.removeUser(key);
            }
            object.put("code", code);
            object.put("message", new String[]{
                    "修改成功, 请重新登录",
                    "不合法的用户名",
                    "该登录名已被占用"
            }[code]);
            return object.toString();
        }
        return "-1";
    }

    /**
     * 修改登录密码 修改后需要重新登录
     *
     * @param name
     * @param key
     * @param password
     * @param args
     * @return
     */
    @GetMapping(value = "/set_password")
    public String updatePassword(
            @Param(value = "name") String name,
            @Param(value = "key") long key,
            @Param(value = "password") String password,
            @Param(value = "args") String args
    ) {
        KeyBufferManager manager = KeyBufferManager.getInstance();
        JSONObject object = new JSONObject();
        if (manager.verify(name, key)) {
            int code = userService.updatePassword(password, args, key);
            if (code == 0) {
                //登出
                manager.removeKey(name);
                manager.removeUser(key);
            }
            object.put("code", code);
            object.put("message", new String[]{
                    "修改成功, 请重新登录",
                    "输入的密码不合法",
                    "原密码输入错误"
            }[code]);
            return object.toString();
        }
        return "-1";
    }

    /**
     * 修改账号资料 修改成功返回0
     *
     * @param name
     * @param key
     * @param args
     * @return
     */
    @GetMapping(value = "/set_data")
    public int updatePersonalData(
            @Param(value = "name") String name,
            @Param(value = "key") long key,
            @Param(value = "args") String args
    ) {
        KeyBufferManager manager = KeyBufferManager.getInstance();
        JSONObject data = new JSONObject(args);
        if (manager.verify(name, key)) {
            String nickname = data.getString("nickname");
            int sex = data.getInt("sex");
            String word = data.getString("word");
            UserInfo info = new UserInfo(nickname, sex, word);
            System.out.println(info);
            userService.updateInfoData(info, key);
            return 0;
        }
        return -1;
    }

}

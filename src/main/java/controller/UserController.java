package controller;

import entity.UserInfo;
import model.AuthorInfo;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.UserService;
import util.FileTools;
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
    @GetMapping(value = "/set/name")
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
    @GetMapping(value = "/set/password")
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
    @GetMapping(value = "/set/data")
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
            userService.updateInfoData(info, key);
            return 0;
        }
        return -1;
    }

    /**
     * 获取用户数据
     *
     * @param name
     * @param key
     * @return 返回一个json，包含了用户部分数据和code 如果code为-1，则请求失败
     */
    @GetMapping(value = "/get/data")
    public String getPersonalData(
            @Param(value = "name") String name,
            @Param(value = "key") long key
    ) {
        JSONObject object = new JSONObject();
        KeyBufferManager manager = KeyBufferManager.getInstance();
        object.put("code", -1);
        if (manager.verify(name, key)) {
            UserInfo info = userService.selectUserInfo(key);
            object.put("nickname", info.getNickname());
            object.put("sex", info.getSex());
            object.put("word", info.getWord());
            object.put("code", 0);
            object.put("id", info.getId());
        }
        return object.toString();
    }

    /**
     * 设置用户头像 图片大小最大为5M 如果图片不是正方形就返回-3
     *
     * @param name
     * @param key
     * @param file
     * @return
     */
    @Scope("prototype")
    @PostMapping(value = "/set/head")
    public int set_head(
            @Param(value = "name") String name,
            @Param(value = "key") long key,
            @RequestParam MultipartFile file
    ) {
        if (file.getSize() < 1024 * 1024 * 5) {
            KeyBufferManager manager = KeyBufferManager.getInstance();
            if (manager.verify(name, key)) {
                if (FileTools.saveUserHead(userService.findIdByName(name), file)) {
                    return 0;
                }
                return -3;
            }
            return -1;
        }
        return -2;
    }

    /**
     * 获取作者信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/authorinfo/{id}")
    public String get_authorinfo(
            @PathVariable(value = "id") long id
    ) {
        AuthorInfo info = userService.selectAuthorInfo(id);
        if (info == null) {
            return "{\"code\":-1}";
        }
        return info.toJson();
    }
}

package controller;

import entity.User;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.SubmissionService;
import service.UserService;
import util.KeyBufferManager;

/**
 * 投稿的请求
 */
@RestController
@RequestMapping(value = "/submission")
public class SubmissionController {

    @Autowired
    UserService userService;

    @Autowired
    SubmissionService submissionService;

    private final static String UPLOAD_MESSAGE[] = new String[]{
            "发布成功",
            "文件过多",
            "标题内容过长",
            "关键内容字过长",
            "简介内容过长",
            "服务器错误",
            "登录信息已失效, 请重新登陆",
    };

    private final static String UPDATE_MESSAGE[] = new String[]{
            "修改成功",
            "标题内容过长",
            "关键内容字过长",
            "简介内容过长",
            "服务器错误",
            "登录信息已失效, 请重新登录"
    };

    /**
     * 投稿请求 上传图片
     *
     * @param name
     * @param key
     * @param title        稿件标题
     * @param keyword      关键字
     * @param introduction 简介信息
     * @param file         文件数组，最多24张图片
     * @return {
     * code:返回代码 -1为key错误
     * message:信息
     * }
     */
    @Scope("prototype")
    @PostMapping(value = "/upload")
    public String UploadImage(
            @Param(value = "name") String name,
            @Param(value = "key") long key,
            @Param(value = "title") String title,
            @Param(value = "keyword") String keyword,
            @Param(value = "introduction") String introduction,
            @RequestParam MultipartFile[] file
    ) {
        KeyBufferManager manager = KeyBufferManager.getInstance();
        JSONObject object = new JSONObject();
        if (manager.verify(name, key)) {
            long byuserid = userService.findIdByName(name);
            int code = submissionService.upload(byuserid, title, keyword, introduction, file);
            object.put("code", code);
            object.put("message", UPLOAD_MESSAGE[code]);
        } else {
            object.put("code", 6);
            object.put("message", UPLOAD_MESSAGE[6]);
        }
        return object.toString();
    }

    /**
     * 删除投稿
     *
     * @param name
     * @param key
     * @param submission_id
     * @return
     */
    @GetMapping(value = "/delete")
    public int delete(
            @Param(value = "name") String name,
            @Param(value = "key") long key,
            @Param(value = "submission_id") long submission_id
    ) {
        KeyBufferManager manager = KeyBufferManager.getInstance();
        if (manager.verify(name, key)) {
            User user = manager.getUser(key);
            if (submissionService.delete(user.getId(), submission_id)) {
                return 0;
            }
        }
        return -1;
    }

    /**
     * 修改内容，只能修改标题，关键字，简介
     *
     * @param name
     * @param key
     * @param submission_id
     * @param title
     * @param keyword
     * @param introduction
     * @return
     */
    @GetMapping(value = "/update")
    public String update(
            @Param(value = "name") String name,
            @Param(value = "key") long key,
            @Param(value = "submission_id") long submission_id,
            @Param(value = "title") String title,
            @Param(value = "keyword") String keyword,
            @Param(value = "introduction") String introduction
    ) {
        JSONObject object = new JSONObject();
        int code = 5;
        KeyBufferManager manager = KeyBufferManager.getInstance();
        if (manager.verify(name, key)) {
            User user = manager.getUser(key);
            code = submissionService.update(user.getId(), submission_id, title, keyword, introduction);
        }
        object.put("code", code);
        object.put("message", UPDATE_MESSAGE[code]);
        return object.toString();
    }
}

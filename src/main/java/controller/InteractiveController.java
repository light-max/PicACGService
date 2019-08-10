package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.InteractiveService;

/**
 * 互动请求
 */
@RestController
@RequestMapping(value = "/interactive")
public class InteractiveController {

    @Autowired
    InteractiveService interactiveService;

    /**
     * 给id为manuscriptid的作品点赞
     *
     * @param manuscriptid
     * @param name
     * @param key
     * @return code{
     * -1 : 登录信息不存在
     * 0 : 点赞成功
     * 1 : 不可重复点赞
     * }
     */
    @GetMapping(value = "/star/{manuscriptid}")
    public int star(
            @PathVariable("manuscriptid") long manuscriptid,
            @Param("name") String name,
            @Param("key") long key
    ) {
        return interactiveService.star(manuscriptid, name, key);
    }

    /**
     * 给id为manuscriptid的作品增加浏览量
     *
     * @param manuscriptid
     * @param name
     * @param key
     * @return code{
     * -1 : 登录信息不存在
     * 0 : 成功增加
     * 1 : 已经增加过了
     * }
     */
    @GetMapping(value = "/watch/{manuscriptid}")
    public int watch(
            @PathVariable("manuscriptid") long manuscriptid,
            @Param("name") String name,
            @Param("key") long key
    ) {
        return interactiveService.watch(manuscriptid, name, key);
    }

}

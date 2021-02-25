package controller;

import model.Manuscript;
import org.apache.ibatis.annotations.Param;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ContentService;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取内容的请求
 */
@RestController
@RequestMapping(value = "/content")
public class ContentController {

    @Autowired
    ContentService contentService;
//    ACG picture share

    /**
     * 随机获取其他用户发的稿件
     *
     * @param number 要获取的稿件的数量
     * @return
     */
    @GetMapping(value = "/random")
    public String random(
            @Param(value = "number") int number
    ) {
        List<Manuscript> list = contentService.rand(number);
        return contentService.toJson(list);
    }

    /**
     * 获取内容，根据filter_id过滤
     *
     * @param number
     * @param filter_id
     * @return
     */
    @GetMapping(value = "/get")
    public String get(
            @Param(value = "number") int number,
            @Param(value = "filter_id") String filter_id
    ) {
        JSONArray filter_array;
        List<Long> id = new ArrayList<>();
        if (!filter_id.equals("null")) try {
            filter_array = new JSONArray(filter_id);
            for (int i = 0; i < filter_array.length(); i++) {
                id.add(filter_array.getLong(i));
            }
        } catch (JSONException ignored) {
        }
        List<Manuscript> list = contentService.get(number, id);
        return contentService.toJson(list);
    }

    /**
     * 获取特定类型排序的稿件
     *
     * @param type   {time:0 star:1 watch:2}
     * @param number
     * @param lastid
     * @return
     */
    @GetMapping(value = "/sort/{type}")
    public String sort(
            @PathVariable(value = "type") String type,
            @Param(value = "number") int number,
            @Param(value = "lastid") long lastid
    ) {
        int t = -1;
        switch (type) {
            case "time":
                t = 0;
                break;
            case "star":
                t = 1;
                break;
            case "watch":
                t = 2;
                break;
        }
        if (t == -1) {
            return "-1";
        }
        List<Manuscript> list = contentService.sort(t, number, lastid);
        return contentService.toJson(list);
    }

    /**
     * 获取指定作者的投稿
     *
     * @param id     作者id
     * @param number 需要的数量
     * @param lastid 已获得的最后一个id，作为过滤条件，不过滤可以填0
     * @return
     */
    @GetMapping(value = "/get_author/{id}")
    public String get(
            @PathVariable(value = "id") long id,
            @Param(value = "number") int number,
            @Param(value = "lastid") long lastid
    ) {
        List<Manuscript> list = contentService.get(id, number, lastid);
        return contentService.toJson(list);
    }

    /**
     * 获取指定的稿件
     *
     * @param id 稿件id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public String getas(
            @PathVariable(value = "id") long id
    ) {
        Manuscript manuscript = contentService.get(id);
        if (manuscript != null) {
            return manuscript.toJson().toString();
        }
        return "";
    }
}

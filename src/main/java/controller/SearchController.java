package controller;

import model.SearchValue;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SearchService;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    /**
     * 搜索，按标题分类，按关键字分类，按作者昵称分类
     *
     * @param value
     * @return
     */
    @GetMapping(value = "/search")
    public String search(
            @Param("value") String value
    ) {
        JSONObject object = new JSONObject();
        if (value.trim().length() != 0 && value.length() <= 24) {
            List<SearchValue> title = searchService.title(value.toLowerCase());
            List<SearchValue> keyword = searchService.keyword(value.toLowerCase());
            List<SearchValue> username = searchService.username(value.toLowerCase());
            object.put("title", title);
            object.put("keyword", keyword);
            object.put("user", username);
        }
        return object.toString();
    }

}

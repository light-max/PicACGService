package model;

import lombok.Data;
import org.json.JSONObject;

/**
 * 和搜索有关的model，只包含了id和值
 */
@Data
public class SearchValue {
    public SearchValue() {
    }

    private long id;
    private String value;
    private String icon;//搜索到的资源的图标

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("value", value);
        object.put("icon", icon);
        return object.toString();
    }
}

package model;

import entity.Submission;
import lombok.Data;
import org.json.JSONObject;
import util.StringTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 稿件数据模型 继承Submission
 */
@Data
public class Manuscript extends Submission {
    /**
     * 图片资源链接 原图
     */
    private List<String> images = new ArrayList<>();

    /**
     * 图片资源链接 缩略图
     */
    private List<String> thumbnails = new ArrayList<>();

    /**
     * 图片资源链接 展示图
     */
    private List<String> show = new ArrayList<>();

    /**
     * 作者的昵称
     */
    private String authorName;

    private void createImages() {
        images.clear();
        for (int i = 0; i < getNumber(); i++) {
            String url = String.format("%s/image/source/%d/%d/%d", StringTools.getUrl(), getByuserid(), getId(), i);
            images.add(url);
        }
    }

    private void createThumbnails() {
        thumbnails.clear();
        for (int i = 0; i < getNumber(); i++) {
            String url = String.format("%s/image/small/%d/%d/%d", StringTools.getUrl(), getByuserid(), getId(), i);
            thumbnails.add(url);
        }
    }

    private void createShow() {
        show.clear();
        for (int i = 0; i < getNumber(); i++) {
            String url = String.format("%s/image/show/%d/%d/%d", StringTools.getUrl(), getByuserid(), getId(), i);
            show.add(url);
        }
    }

    /**
     * 转换为json格式的数据
     *
     * @return
     */
    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("id", getId());
        object.put("author", getByuserid());
        object.put("title", getTitle());
        object.put("keyword", getKeyword());
        object.put("introduction", getIntroduction());
        object.put("number", getNumber());
        object.put("star", getStar());
        object.put("watch", getWatch());
        object.put("releasetime", getReleasetime());
        object.put("authorname", authorName);
        createImages();
        createThumbnails();
        createShow();
        object.put("images", images);
        object.put("thumbnails", thumbnails);
        object.put("show", show);
        return object;
    }
}

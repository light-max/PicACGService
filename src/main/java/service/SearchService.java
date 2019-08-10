package service;

import model.SearchValue;

import java.util.List;

/**
 * 搜索返回的都是资源id
 */
public interface SearchService {

    /**
     * 按标题搜索
     *
     * @param value
     * @return
     */
    List<SearchValue> title(String value);

    /**
     * 按关键字搜索
     *
     * @param value
     * @return
     */
    List<SearchValue> keyword(String value);

    /**
     * 按用户名搜索
     *
     * @param value
     * @return
     */
    List<SearchValue> username(String value);
}

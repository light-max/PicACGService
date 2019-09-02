package service.imp;

import dao.SubmissionMapper;
import dao.UserMapper;
import model.SearchValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SearchService;
import util.StringTools;

import java.util.ArrayList;
import java.util.List;

@Service("SearchService")
public class SearchServiceImp implements SearchService {

    @Autowired
    SubmissionMapper submissionMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 内部方法
     *
     * @param list
     * @param value
     * @return
     */
    private List<SearchValue> getValue(List<SearchValue> list, String value) {
        List<SearchValue> values = new ArrayList<>();
        for (SearchValue searchValue : list) {
            if (searchValue.getValue() == null || searchValue.getValue().length() == 0) {
                continue;
            }
            String v = searchValue.getValue().toLowerCase();
            //随便匹配一下
            if (v.contains(value) || value.contains(v)) {
                values.add(searchValue);
            }
        }
        return filter(values, value);
    }

    private List<SearchValue> getSubmissionSmallURL(String value, List<SearchValue> list) {
        list = getValue(list, value);
        for (SearchValue v : list) {
            Long userid = submissionMapper.selectUserIdById(v.getId());
            String url = StringTools.getUrl() + String.format("/image/small/%d/%d/0", userid, v.getId());
            v.setIcon(url);
        }
        return list;
    }

    /**
     * 经过二次过滤
     *
     * @param list
     * @param value
     * @return
     */
    private List<SearchValue> filter(List<SearchValue> list, String value) {
        return list;
    }

    @Override
    public List<SearchValue> title(String value) {
        List<SearchValue> list = submissionMapper.selectTitleAll();
        return getSubmissionSmallURL(value, list);
    }

    @Override
    public List<SearchValue> keyword(String value) {
        List<SearchValue> list = submissionMapper.selectKeywordAll();
        return getSubmissionSmallURL(value, list);
    }

    @Override
    public List<SearchValue> username(String value) {
        List<SearchValue> list = userMapper.selectNicknameAll();
        list = getValue(list, value);
        for (SearchValue v : list) {
            v.setIcon(StringTools.getUrl() + "/image/small/head/" + v.getId());
        }
        return list;
    }
}

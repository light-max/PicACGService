package service.imp;

import dao.SubmissionMapper;
import dao.UserMapper;
import model.Manuscript;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ContentService;
import util.SubmissionIdManager;

import java.util.ArrayList;
import java.util.List;

@Service(value = "ContentService")
public class ContentServiceImp implements ContentService {

    @Autowired
    SubmissionMapper submissionMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 内部方法
     *
     * @param ids
     * @return
     */
    private List<Manuscript> getManuscripts(long[] ids) {
        List<Manuscript> list = new ArrayList<>();
        if (ids != null) {
            for (long id : ids) {
                try {
                    Manuscript obj = submissionMapper.selectById(id);
                    String nickname = userMapper.selectNicknameById(obj.getByuserid());
                    obj.setAuthorName(nickname);
                    list.add(obj);
                } catch (NullPointerException e) {
                    System.out.println(id);
                    e.printStackTrace();
                    break;
                }
            }
        }
        return list;
    }

    @Override
    public String toJson(List<Manuscript> list) {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for (Manuscript manuscript : list) {
            array.put(manuscript.toJson());
        }
        object.put("code", 0);
        object.put("time", System.currentTimeMillis());
        object.put("content", array);
        return object.toString();
    }

    @Override
    public List<Manuscript> rand(int number) {
        SubmissionIdManager manager = SubmissionIdManager.getInstance();
        long[] ids = manager.randId(number);
        return getManuscripts(ids);
    }

    @Override
    public List<Manuscript> get(int number, List<Long> filter_id) {
        SubmissionIdManager manager = SubmissionIdManager.getInstance();
        long[] ids = manager.getIdFilter(number, filter_id);
        return getManuscripts(ids);
    }

    @Override
    public List<Manuscript> sort(int type, int number, long lastid) {
        SubmissionIdManager manager = SubmissionIdManager.getInstance();
        long[] ids = null;
        switch (type) {
            case 0:
                ids = manager.getIdTime(number, lastid);
                break;
            case 1:
                ids = manager.getIdStar(number, lastid);
                break;
            case 2:
                ids = manager.getIdWatch(number, lastid);
                break;
        }
        return getManuscripts(ids);
    }

    @Override
    public List<Manuscript> get(long userid, int number, long lastid) {
        List<Long> list = submissionMapper.selectAllByUserId(userid, lastid);
        int size = number < list.size() ? number : list.size();
        long[] ids = new long[size];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = list.get(i);
        }
        return getManuscripts(ids);
    }

    @Override
    public Manuscript get(long id) {
        List<Manuscript> list = getManuscripts(new long[]{id});
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }
}


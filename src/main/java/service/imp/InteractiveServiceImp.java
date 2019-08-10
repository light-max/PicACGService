package service.imp;

import dao.StarMapper;
import dao.SubmissionMapper;
import dao.WatchMapper;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.InteractiveService;
import util.KeyBufferManager;

@Service(value = "InteractiveService")
public class InteractiveServiceImp implements InteractiveService {

    @Autowired
    StarMapper starMapper;

    @Autowired
    WatchMapper watchMapper;

    @Autowired
    SubmissionMapper submissionMapper;

    @Override
    public int star(long manuscriptid, String name, long key) {
        KeyBufferManager manager = KeyBufferManager.getInstance();
        if (manager.verify(name, key)) {
            User user = manager.getUser(key);
            long userId = user.getId();
            if (!starMapper.checkStar(userId, manuscriptid)) {
                starMapper.insert(userId, manuscriptid);
                submissionMapper.addStar(manuscriptid);
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public int watch(long manuscriptid, String name, long key) {
        KeyBufferManager manager = KeyBufferManager.getInstance();
        if (manager.verify(name, key)) {
            User user = manager.getUser(key);
            long userId = user.getId();
            if (!watchMapper.checkWatch(userId, manuscriptid)) {
                watchMapper.insert(userId, manuscriptid);
                submissionMapper.addWatch(manuscriptid);
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }

}

package service.imp;

import dao.StarMapper;
import dao.SubmissionMapper;
import dao.WatchMapper;
import entity.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.SubmissionService;
import util.FileTools;
import util.SubmissionIdManager;

import java.io.File;

@Service(value = "SubmissionService")
public class SubmissionServiceImp implements SubmissionService {

    @Autowired
    SubmissionMapper submissionMapper;

    @Autowired
    StarMapper starMapper;

    @Autowired
    WatchMapper watchMapper;

    @Override
    public int upload(long byuserid, String title, String keyword, String introduction, MultipartFile[] files) {
        if (title.length() > 32) {
            return 2;
        }
        if (keyword.length() > 32) {
            return 3;
        }
        if (introduction.length() > 64) {
            return 4;
        }
        if (files.length > 24) {
            return 1;
        }
        Submission submission = new Submission(byuserid, title, keyword, introduction, files.length);
        submissionMapper.insert(submission);
        //文件保存失败的操作，但正常情况下这几乎是不可能发生的 还真有可能，如果是gif图片会抛异常
        if (!FileTools.saveSubmission(byuserid, submission.getId(), files)) {
            submissionMapper.deleteById(submission.getId());
            return 5;
        }
        //把稿件id添加到缓存
        SubmissionIdManager.getInstance().addId(submission.getId());
        return 0;
    }

    @Override
    public boolean delete(long userid, long submissionId) {
        //确保这个作品是这个key的用户的
        Long aLong = submissionMapper.selectUserIdById(submissionId);
        if (aLong != null && aLong == userid) {
            //删除内存中的缓存
            SubmissionIdManager manager = SubmissionIdManager.getInstance();
            manager.removeId(submissionId);
            //删除这个作品的一切数据
            submissionMapper.deleteById(submissionId);
            starMapper.clearStar(submissionId);
            watchMapper.clearWatch(submissionId);
            File[] files = new File[]{
                    FileTools.getSubmissionShowFile(userid, submissionId, 0).getParentFile(),
                    FileTools.getSubmissionSmallFile(userid, submissionId, 0).getParentFile(),
                    FileTools.getSubmissionSourceFile(userid, submissionId, 0).getParentFile()
            };
            FileTools.deleteDirectory(files[0]);
            FileTools.deleteDirectory(files[1]);
            FileTools.deleteDirectory(files[2]);
            return true;
        }
        return false;
    }

    @Override
    public int update(long userid, long submissionId, String title, String keyword, String introduction) {
        if (title.length() > 16) {
            return 1;
        }
        if (keyword.length() > 16) {
            return 2;
        }
        if (introduction.length() > 64) {
            return 3;
        }
        //确保这个作品是这个key的用户的
        if (submissionMapper.selectUserIdById(submissionId) == userid) {
            submissionMapper.update(title, keyword, introduction, submissionId);
            return 0;
        }
        return -1;
    }
}

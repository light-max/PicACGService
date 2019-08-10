package service;

import org.springframework.web.multipart.MultipartFile;

public interface SubmissionService {

    /**
     * 投稿上传
     *
     * @param byuserid
     * @param title
     * @param keyword
     * @param introduction
     * @param files
     * @return {
     * 0:发布成功
     * 1:文件过多
     * 2:标题内容过长
     * 3:关键内容字过长
     * 4:简介内容过长
     * 5:服务器错误
     * }
     */
    int upload(long byuserid, String title, String keyword, String introduction, MultipartFile[] files);

    /**
     * 删除投稿
     *
     * @param userid
     * @param submissionId
     */
    boolean delete(long userid, long submissionId);

    /**
     * 修改投稿 返回值和upload差不多
     *
     * @param userid
     * @param submissionId
     * @param title
     * @param keyword
     * @param introduction
     */
    int update(long userid, long submissionId, String title, String keyword, String introduction);
}

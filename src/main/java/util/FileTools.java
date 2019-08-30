package util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * 文件操作
 */
public class FileTools {
    //顶级目录
    private static final File root = new File("F:\\PicACG");
    //图片目录
    private static final File image = new File(root, "image");
    //投稿图片目录
    private static final File submission_source = new File(image, "submission_source");
    private static final File submission_small = new File(image, "submission_small");
    private static final File submission_show = new File(image, "submission_show");
    //用户头像目录
    private static final File head_source = new File(image, "head_source");
    private static final File head_small = new File(image, "head_small");
    private static final File head_temp = new File(image, "head_temp");
    //默认头像
    public static final File defaultHead = new File(image, "default/head.jpg");

    /**
     * 保存投稿图片，再保存一份缩略图
     *
     * @param userId       投稿人id
     * @param submissionId 投稿人稿件id
     * @param files        文件列表
     * @return
     */
    public static boolean saveSubmission(long userId, long submissionId, MultipartFile[] files) {
        try {
            for (int i = 0; i < files.length; i++) {
                File source = getSubmissionSourceFile(userId, submissionId, i);
                File small = getSubmissionSmallFile(userId, submissionId, i);
                File show = getSubmissionShowFile(userId, submissionId, i);
                IOTools.copy(new FileOutputStream(source), files[i].getInputStream());
                MyImageTools.saveSubmissionSmall(source, small);
                MyImageTools.saveSubmissionShow(source, show);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存用户头像，再保存一份缩略图
     *
     * @param userId 用户id号
     * @param file
     * @return
     */
    public static boolean saveUserHead(long userId, MultipartFile file) {
        File temp = getUserHeadImage_Temp(userId);
        File source = getUserHeadImage_Source(userId);
        File small = getUserHeadImage_Small(userId);
        try {
            //先保存到临时文件
            IOTools.copy(new FileOutputStream(temp), file.getInputStream());
            //判断图片长宽是否一样
            //seek等于0才是长宽一样，但有时候会出现bug，所以差一点点像素点就酸了
            BufferedImage image = ImageIO.read(temp);
            int seek = image.getWidth() - image.getHeight();
            if (Math.abs(seek) > 5) {
                return false;
            }
            //保存原图
            IOTools.copy(new FileOutputStream(source), new FileInputStream(temp));
            //保存剪裁后的缩略图
            MyImageTools.saveHeadPNG(source, small);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            temp.delete();
        }
    }

    /**
     * 删除文件
     *
     * @param file
     */
    public static void deleteDirectory(File file) {
        if (file.isFile()) {
            file.delete();
        } else {
            for (File fp : Objects.requireNonNull(file.listFiles())) {
                deleteDirectory(fp);
            }
            file.delete();
        }
    }

    /**
     * 获取投稿文件
     *
     * @param userId       所属用户id
     * @param submissionId 稿件id
     * @param resId        资源id
     * @return
     */
    public static File getSubmissionSourceFile(long userId, long submissionId, int resId) {
        File path = new File(submission_source, String.format("%d/%d", userId, submissionId));
        if (!path.exists()) {
            path.mkdirs();
        }
        return new File(path, resId + ".jpg");
    }

    public static File getSubmissionSmallFile(long userId, long submissionId, int resId) {
        File path = new File(submission_small, String.format("%d/%d", userId, submissionId));
        if (!path.exists()) {
            path.mkdirs();
        }
        return new File(path, resId + ".jpg");
    }

    public static File getSubmissionShowFile(long userId, long submissionId, int resId) {
        File path = new File(submission_show, String.format("%d/%d", userId, submissionId));
        if (!path.exists()) {
            path.mkdirs();
        }
        return new File(path, resId + ".jpg");
    }


    /**
     * 获取用户头像文件
     *
     * @param userId 用户id
     * @return
     */
    public static File getUserHeadImage_Source(long userId) {
        if (!head_source.exists()) {
            head_source.mkdirs();
        }
        return new File(head_source, userId + ".jpg");
    }

    public static File getUserHeadImage_Small(long userId) {
        if (!head_small.exists()) {
            head_small.mkdirs();
        }
        return new File(head_small, userId + ".jpg");
    }

    public static File getUserHeadImage_Temp(long userId) {
        if (!head_temp.exists()) {
            head_temp.mkdirs();
        }
        return new File(head_temp, Long.toString(userId));
    }
}

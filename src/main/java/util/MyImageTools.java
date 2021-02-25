package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyImageTools {

    /**
     * 保存正方形的图片头像
     *
     * @param source
     * @param topath
     * @throws IOException
     */
    static void saveHeadPNG(File source, File topath) throws IOException {
        BufferedImage image = ImageIO.read(source);
        int size = Math.min(image.getWidth(), 256);
        BufferedImage buffer = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = buffer.getGraphics();
        graphics.drawImage(image, 0, 0, size, size, null);
        graphics.dispose();
        topath.delete();
        ImageIO.write(buffer, "jpg", topath);
    }

    /**
     * 保存投稿图片的缩略图
     *
     * @param source
     * @param topath
     * @throws IOException
     */
    public static void saveSubmissionSmall(File source, File topath) throws IOException {
        BufferedImage image = ImageIO.read(source);
        //取消缩放
        if (image.getWidth() < 512 || image.getHeight() < 512) {
            ImageIO.write(image, "jpg", topath);
            return;
        }
        double sw = image.getWidth() / 512.0;
        double sh = image.getHeight() / 512.0;
        //缩小比例
        double s = Math.min(sw, sh);
        int width = (int) (image.getWidth() / s);
        int height = (int) (image.getHeight() / s);
        BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = buffer.getGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        ImageIO.write(buffer, "jpg", topath);
    }

    /**
     * 保存投稿图片的展示图，展示图的尺寸和原图一样，但展示图是转换为jpg格式的
     *
     * @param source
     * @param topath
     * @throws IOException
     */
    public static void saveSubmissionShow(File source, File topath) throws IOException {
        BufferedImage image = ImageIO.read(source);
        BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics graphics = buffer.getGraphics();
        graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        graphics.dispose();
        ImageIO.write(buffer, "jpg", topath);
    }

    public static void main(String[] args) {
        File source = new File("F:\\PicACG\\submission\\image\\8\\16\\test1.jpg");
        File topath = new File("F:\\PicACG\\submission\\image\\8\\16\\test.jpg");
        try {
            saveSubmissionSmall(source, topath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

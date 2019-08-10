package controller;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import util.FileTools;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

    /**
     * 获取用户头像
     *
     * @param type {source:原图 small:256*256的jpg图片}
     * @param id
     * @return
     * @throws IOException
     */
    @Scope("prototype")
    @GetMapping(value = "/{type}/head/{id}")
    public ResponseEntity<byte[]> head(
            @PathVariable("type") String type,
            @PathVariable("id") long id
    ) throws IOException {
        File file = null;
        if (type.equals("source")) {
            file = FileTools.getUserHeadImage_Source(id);
        } else if (type.equals("small")) {
            file = FileTools.getUserHeadImage_Small(id);
        }
        if (file == null || !file.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", file.getName());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }

    /**
     * 获取投稿图片
     *
     * @param type       {source:原图  small:缩略图  show:展示图}
     * @param author
     * @param submission
     * @param image
     * @return
     */
    @Scope("prototype")
    @GetMapping(value = "/{type}/{author}/{submission}/{image}")
    public ResponseEntity<byte[]> image(
            @PathVariable(value = "type") String type,
            @PathVariable(value = "author") int author,
            @PathVariable(value = "submission") int submission,
            @PathVariable(value = "image") int image
    ) throws IOException {
        File file = null;
        if (type.equals("source")) {
            file = FileTools.getSubmissionSourceFile(author, submission, image);
        } else if (type.equals("small")) {
            file = FileTools.getSubmissionSmallFile(author, submission, image);
        } else if (type.equals("show")) {
            file = FileTools.getSubmissionShowFile(author, submission, image);
        }
        if (file == null || !file.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        //通知浏览器以下载的方式打开文件
        headers.setContentDispositionFormData("attachment", String.format("image%d%d%d.jpg", author, submission, image));
        //定义以流的形式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }
}

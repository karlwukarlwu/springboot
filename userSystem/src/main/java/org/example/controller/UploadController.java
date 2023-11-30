package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Controller
@Slf4j
public class UploadController {

    //处理转发到用户注册-可以完成文件上传页面
    @GetMapping("/upload.html")
    public String uploadPage() {
        return "upload";// 视图解析,转发到templates/upload.html
    }

    //处理用户的注册请求-包括处理文件上传

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("age") Integer age,
                         @RequestParam("job") String job,
                         @RequestParam("header") MultipartFile header,
                         @RequestParam("photos") MultipartFile[] photos) throws IOException {

        //输出获取到的信息
        log.info("上传的信息 name={} email={} age={} job={} header={} photos={} ",
                name, email, age, job, header.getSize(), photos.length);

        //如果信息都成功得到，我们就将文件保存到指定的目录,比如d:\\temp_upload
        //1. 我们先将文件保存到指定的目录 比如d:\\temp_upload
        //2. 后面我们在演示把文件保存到动态创建的目录.
        //   比如:

        //得到类路径(运行的时候)
        String path = ResourceUtils.getURL("classpath:").getPath();
        //log.info("path={}", path);

        //动态创建指定目录，这里就是日期+固定目录
        File file = new File(path + WebUtils.getUploadFileDirectory());
        if (!file.exists()) {//如果目录不存在，我们就创建, 在java io
            file.mkdirs();
        }
//header 是前端传的头像
        if (!header.isEmpty()) {//处理头像
            //获取上传文件的名字
            String originalFilename = header.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + originalFilename;
            //这里我们需要指定保存文件的绝对路径 ，
            //即 D:\hspedu_springboot\springboot-usersys\target\classes\static\images\
            //header.transferTo(new File("d:\\temp_upload\\" + originalFilename));

            //log.info("保存文件的绝对路径={}" + file.getAbsolutePath());
            //保存到动态创建的目录
//            这样就是保存了文件 且给了文件新的名字
            header.transferTo(new File(file.getAbsolutePath() + "/" + fileName));
        }

        //处理宠物的图片
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {//遍历
                if (!photo.isEmpty()) {
                    String originalFilename = photo.getOriginalFilename();
                    String fileName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis() + "_" + originalFilename;
                    //photo.transferTo(new File("d:\\temp_upload\\" + originalFilename));
                    //保存到动态创建的目录
                    photo.transferTo(new File(file.getAbsolutePath() + "/" + fileName));
                }
            }
        }
        return "注册用户成功/文件上传成功";
    }
}

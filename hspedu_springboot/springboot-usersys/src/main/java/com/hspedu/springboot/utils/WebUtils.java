package com.hspedu.springboot.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class WebUtils {


    //定义一个文件上传的路径
    public static String  UPLOAD_FILE_DIRECTORY = "static/images/upload/";

    //编写方法，生成一个目录-根据当前日期 年/月/日

    public static String getUploadFileDirectory() {
        return UPLOAD_FILE_DIRECTORY + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

}

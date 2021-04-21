package com.example.mydegign.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadUtils {

    //    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
//    public final static String IMG_PATH_PREFIX = "static/outsourcin/images";

    public static File getImgDirFile() {

//        // 构建上传文件的存放 "文件夹" 路径
//        String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX);

//        File fileDir = new File(MyFile.uploadLinuxImg);
        File fileDir = new File(MyFile.uploadWindowImg);
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

    public static String uploadImg(MultipartFile imgFile) {
        String filename = imgFile.getOriginalFilename();
        assert filename != null;
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        filename = UUID.randomUUID().toString().replace("-", "") + "." + prefix;

        // 存放上传图片的文件夹
        File fileDir = UploadUtils.getImgDirFile();
        // 输出文件夹绝对路径  -- 这里的绝对路径是相当于当前项目的路径而不是“容器”路径
        String url = fileDir.getAbsolutePath();

        try {
            // 构建真实的文件路径
            File newFile = new File(url + File.separator + filename);
            System.err.println(newFile.getAbsolutePath());
            // 上传图片到 -》 “绝对路径”
            imgFile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return filename;
    }
}
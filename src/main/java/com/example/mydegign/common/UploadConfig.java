package com.example.mydegign.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        /*
//         * windows
//         */
        registry.addResourceHandler(MyFile.imgAccessPath).addResourceLocations("file:" + MyFile.uploadWindowImg);
        registry.addResourceHandler(MyFile.fileAccessPath).addResourceLocations("file:" + MyFile.uploadWindowFile);

        /*
         * linux
         */
//        registry.addResourceHandler(MyFile.imgAccessPath).addResourceLocations("file:" + MyFile.uploadLinuxImg);
//        registry.addResourceHandler(MyFile.pageAccessPath).addResourceLocations("file:" + MyFile.uploadLinuxFile);

    }
}
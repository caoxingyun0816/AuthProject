package com.cxy.security.cxysecuritydemo.controller;

import com.cxy.security.cxysecuritydemo.model.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.lang.annotation.Target;
import java.util.Date;

/***
 * Created by Caoxingyun on 2019/03/18
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private String target = "D:/upload";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        //file.getInputStream() 可以利用该方法将文件写到其它环境中
        File localFile = new File(target,new Date().getTime() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id , HttpServletRequest request , HttpServletResponse response) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(target,id + ".txt"));
             OutputStream outputStream =response.getOutputStream() ){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename = test.txt");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }
}

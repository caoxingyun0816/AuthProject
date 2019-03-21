package com.cxy.security.cxysecuritydemo.model;

import lombok.Data;

/***
 * Created by Caoxingyun on 2019/03/18
 */
@Data
public class FileInfo {

    private String path;

    public FileInfo(String path){
        this.path = path;
    }
}

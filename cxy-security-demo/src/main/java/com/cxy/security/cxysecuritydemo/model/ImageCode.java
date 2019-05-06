package com.cxy.security.cxysecuritydemo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/***
 * Created by Caoxingyun on 2019/04/11
 */
@Data
public class ImageCode {

    @ApiModelProperty(value = "图片")
    private BufferedImage image;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, int plusTime) {
        this.code = code;
        this.image = image;
        this.expireTime = LocalDateTime.now().plusSeconds(plusTime);
    }

    /***
     * 判断当前时间是否在过期时间之后
     * @return
     */
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}

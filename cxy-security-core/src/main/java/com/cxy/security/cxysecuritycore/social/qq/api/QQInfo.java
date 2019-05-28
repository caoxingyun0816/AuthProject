package com.cxy.security.cxysecuritycore.social.qq.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/***
 * Created by Caoxingyun on 2019/05/27
 */
@Data
public class QQInfo {

    private Integer ret;

    private String msg;

    private String openId;

    @ApiModelProperty(value = "用户在QQ空间的昵称。")
    private String nickname;

    @ApiModelProperty(value = "大小为30×30像素的QQ空间头像URL。")
    private String figureurl;

    @ApiModelProperty(value = "大小为50×50像素的QQ空间头像URL。")
    private String figureurl_1;

    @ApiModelProperty(value = "大小为100×100像素的QQ空间头像URL。")
    private String figureurl_2;

    @ApiModelProperty(value = "大小为40×40像素的QQ头像URL。")
    private String figureurl_qq_1;

    @ApiModelProperty(value = "大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。")
    private String figureurl_qq_2;

    @ApiModelProperty(value = "性别")
    private String gender;


}

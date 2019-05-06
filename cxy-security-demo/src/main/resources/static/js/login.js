/**
 * Created by Administrator on 2018/7/19.
 */
/***
 * if(this.value==''){this.value='用户名'}
 */
function onblurName() {
    var username = document.getElementById("username");
    if(username == ""){
        username.innerHTML = "用户名";
    }
}

function onfocusName() {
    var username = document.getElementById("username");
    username.innerHTML = "";
}

function changeImage() {
    var imageSrc = document.getElementById("imageId");
    var src = imageSrc.attr("src");
    imageSrc.attr("src",changeUrl(src));
}

function changeUrl(url) {
    var timestamp = (new Date()).valueOf();
    var index = url.indexOf("?",url);
    if (index > 0) {
        url = url.substring(0, url.indexOf(url, "?"));
    }
    if ((url.indexOf("&") >= 0)) {
        url = url + "×tamp=" + timestamp;
    } else {
        url = url + "?timestamp=" + timestamp;
    }
    return url;
}

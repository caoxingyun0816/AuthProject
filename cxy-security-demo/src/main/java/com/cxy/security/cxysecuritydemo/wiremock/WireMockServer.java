package com.cxy.security.cxysecuritydemo.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/***
 * Created by Caoxingyun on 2019/03/08
 */
public class WireMockServer {
    public static void main(String[] args) throws IOException {
        //指定端口号
        WireMock.configureFor(8080);
        //清空以前的配置
        WireMock.removeAllMappings();
        //模拟请求
        mockService("/order/1","1");
    }

    public static void mockService(String url, String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("d:/test/"+fileName+".txt");
        String content = StringUtils.join(FileUtils.readLines(classPathResource.getFile(),"UTF-8").toString(),"\n");
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}

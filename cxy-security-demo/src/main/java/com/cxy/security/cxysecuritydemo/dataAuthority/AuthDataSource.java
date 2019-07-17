package com.cxy.security.cxysecuritydemo.dataAuthority;

import javax.persistence.Table;
import java.util.*;

/***
 * Created by Caoxingyun on 2019/07/17
 */

public class AuthDataSource {
    // 从其他系统获取数据 然后将用户和对应的数据权限存入到Redis中
    // 关键是触发，邮件触发还是 消息队列触发

//    public void initBrandInterceptData() {
//        List<BaseUserVO> userVOS = userFeign.getAllUsers().getData();
//
//        Map<String, String> tokenParam = new HashMap<>();
//        tokenParam.put("appId", brandProp.getLimitAppId());
//        tokenParam.put("password", brandProp.getLimitPassword());
//        String resultStr = HttpClientUtils.doGet(brandProp.getLimitTokenUrl(), tokenParam);
//        this.logger.info("获取品牌数据权限token：" + resultStr);
//        Map<String, Object> resultMap = JSON.parseObject(resultStr, Map.class);
//        if (200 != (Integer) resultMap.get("code")) {
//            this.logger.error("获取品牌数据权限token异常！result：" + resultStr);
//            return;
//        }
//        for (BaseUserVO userVO : userVOS) {
//            Map<String, String> headerParam = new HashMap<>();
//            headerParam.put("appId", brandProp.getLimitAppId());
//            headerParam.put("token", resultMap.get("data").toString());
//            Map<String, String> bodyParam = new HashMap<>();
//            bodyParam.put("employeeid", userVO.getEmployeeId());
//            this.logger.info("请求参数：" + userVO.getEmployeeId());
//            String brandResultStr = HttpClientUtils.doPost(brandProp.getLimitBrandsUrl(), bodyParam, headerParam);
//            this.logger.info("获取品牌数据权限data：" + brandResultStr);
//            Map<String, Object> brandResultMap = JSON.parseObject(brandResultStr, Map.class);
//            if (200 != (Integer) brandResultMap.get("code")) {
//                this.logger.error("获取品牌数据权限data！result：" + brandResultStr);
//                continue;
//            }
//
//            String spBrandResultStr = HttpClientUtils.doPost(brandProp.getSpLimitBrandsUrl(), bodyParam, headerParam);
//            this.logger.info("获取特殊品牌数据权限data：" + spBrandResultStr);
//            Map<String, Object> spBrandResultMap = JSON.parseObject(spBrandResultStr, Map.class);
//            if (200 != (Integer) spBrandResultMap.get("code")) {
//                this.logger.error("获取特殊品牌数据权限data！result：" + spBrandResultStr);
//                continue;
//            }
//
//            List<String> names = new ArrayList<>();
//            JSONArray array = (JSONArray) brandResultMap.get("data");
////            if (array.isEmpty() || array.size() == 0) {
////                continue;
////            }
//
//            JSONArray spArray = (JSONArray) spBrandResultMap.get("data");
////            if (spArray.isEmpty() || spArray.size() == 0) {
////                continue;
////            }
//
//            for (Object object : array) {
//                JSONObject jsonObject = (JSONObject) object;
//                if (jsonObject != null) {
//                    names.add(jsonObject.get("brand").toString());
//                }
//            }
//
//            for (Object object : spArray) {
//                JSONObject jsonObject = (JSONObject) object;
//                if (jsonObject != null) {
//                    names.add(jsonObject.get("expect_brand").toString());
//                }
//            }
//
//            redisService.deleteHash("DataAuthority:" + userVO.getEmployeeId(), BrandSideProject.class.getAnnotation((Table.class)).name());
//            redisService.deleteHash("DataAuthority:" + userVO.getEmployeeId(), Brand.class.getAnnotation((Table.class)).name());
//            if (names.size() == 0) {
//                continue;
//            }
//
//            Set<String> projectIds = getProjectBySideNames(names).getData();
//            List<TableCondition> sideProjectConditions = new ArrayList<>();
//            sideProjectConditions.add(new TableCondition("in", "id", projectIds));
//            redisService.setHash("DataAuthority:" + userVO.getEmployeeId()
//                    , BrandSideProject.class.getAnnotation((Table.class)).name()
//                    , sideProjectConditions);
//
//            List<String> brandIds = brandService.getBySideNames(names).getData();
//            List<TableCondition> brandConditions = new ArrayList<>();
//            brandConditions.add(new TableCondition("in", "id", brandIds));
//            redisService.setHash("DataAuthority:" + userVO.getEmployeeId()
//                    , Brand.class.getAnnotation((Table.class)).name()
//                    , brandConditions);
//        }
//    }

//    public ResponseVO<Set<String>> getProjectBySideNames(List<String> names) {
//        Set<String> resultIds = new HashSet<>();
//        List<BrandSideProject> projectBySideNames = brandSideProjectMapper.getProjectBySideNames(names);
//        projectBySideNames.forEach(sideProject ->
//                {
//                    resultIds.add(sideProject.getId());
//                    resultIds.add(sideProject.getParentId());
//                }
//        );
//        return ResponseUtil.ok(resultIds);
//    }
}

package com.itstyle.controller;

import com.itstyle.common.ResultData;
import com.itstyle.common.qiniu.QiniuUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/qiniu"})
public class QiniuController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuController.class);

    @Autowired
    private QiniuUtils qiniuUtils;

    @GetMapping({"/token"})
    public ResultData getToken() {
        try {
            return ResultData.success(this.qiniuUtils.getUploadToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.error("服务器超时");
    }

    @DeleteMapping({"/{pictureId}"})
    public ResultData deletePicture(@PathVariable("pictureId") String pictureId) {
        try {
            if (StringUtils.isBlank(pictureId)) {
                LOGGER.debug("pictureId is empty");
                return ResultData.empty();
            }

            this.qiniuUtils.delete(pictureId);
            return ResultData.success();
        } catch (Exception e) {
            LOGGER.error("七牛删除图片错误：{}", e);
        }
        return ResultData.error("服务器超时");
    }
}
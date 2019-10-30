package com.itstyle.common.qiniu;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.itstyle.common.properties.AppProperties;
import com.itstyle.utils.RandomUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QiniuUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuUtils.class);

    @Autowired
    private AppProperties appProperties;

    public String upload(byte[] uploadBytes) {
        LOGGER.debug("七牛上传图片...");

        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);

        String key = RandomUtils.get32UUID();
        try {
            String upToken = getUploadToken();

            Response response = uploadManager.put(uploadBytes, key, upToken);

            DefaultPutRet putRet = (DefaultPutRet) new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            return putRet.key;
        } catch (Exception e) {
            LOGGER.error("七牛上传图片错误");
        }
        throw new RuntimeException("七牛上传图片错误");
    }

    public String upload(String base64String) {
        if (StringUtils.isBlank(base64String)) {
            throw new NullPointerException("上传对象为空");
        }

        byte[] bytes = Base64.decodeBase64(base64String);

        return upload(bytes);
    }

    public String getUploadToken() {
        Auth auth = Auth.create(this.appProperties.getQiniu().getAk(), this.appProperties.getQiniu().getSk());
        return auth.uploadToken(this.appProperties.getQiniu().getBucket());
    }

    public boolean delete(String pictureId) {
        Configuration cfg = new Configuration(Zone.zone0());

        String accessKey = this.appProperties.getQiniu().getAk();
        String secretKey = this.appProperties.getQiniu().getSk();

        String bucket = this.appProperties.getQiniu().getBucket();
        String key = pictureId;

        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
            return true;
        } catch (QiniuException ex) {
            LOGGER.error("七牛删除图片失败：code:{} 信息：{}", Integer.valueOf(ex.code()), ex.response.toString());
        }
        return false;
    }
}
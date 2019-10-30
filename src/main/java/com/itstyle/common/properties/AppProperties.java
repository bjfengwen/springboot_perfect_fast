package com.itstyle.common.properties;

import com.itstyle.common.properties.entity.AliCloudPro;
import com.itstyle.common.properties.entity.JPushPro;
import com.itstyle.common.properties.entity.QiniuPro;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private AliCloudPro ali = new AliCloudPro();
    private JPushPro jpush = new JPushPro();
    private QiniuPro qiniu = new QiniuPro();

    public AliCloudPro getAli() {
        return this.ali;
    }

    public void setAli(AliCloudPro ali) {
        this.ali = ali;
    }

    public JPushPro getJpush() {
        return this.jpush;
    }

    public void setJpush(JPushPro jpush) {
        this.jpush = jpush;
    }

    public QiniuPro getQiniu() {
        return this.qiniu;
    }

    public void setQiniu(QiniuPro qiniu) {
        this.qiniu = qiniu;
    }
}
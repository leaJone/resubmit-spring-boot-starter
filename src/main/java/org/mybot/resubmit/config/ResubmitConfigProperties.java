package org.mybot.resubmit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lijing
 * @ClassName ResubmitConfigProperties
 * @Description 配置类
 * @date 2019/08/11 12:05
 **/
@Data
@ConfigurationProperties(prefix = ResubmitConfigProperties.PREFIX)
public class ResubmitConfigProperties {

    public static final String PREFIX = "mybot.resubmit";

    /**
     * 定义缓存大小
     */
    private int sizeCtl = 200;

}

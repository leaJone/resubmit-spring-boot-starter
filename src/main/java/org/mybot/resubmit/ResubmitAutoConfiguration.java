package org.mybot.resubmit;

import org.mybot.resubmit.aspect.ResubmitDataAspect;
import org.mybot.resubmit.config.ResubmitConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lijing
 * @ClassName ResubmitConfigProperties
 * @Description 自动装配类
 * @date 2019/08/11 12:05
 **/
@Configuration
@EnableConfigurationProperties(ResubmitConfigProperties.class)
@Import({ResubmitDataAspect.class})
public class ResubmitAutoConfiguration {


}

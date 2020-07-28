package config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "custom", configuration = TestConfig.class)
public class TestConfig {
    /**
       * @description:负载均衡算法
       * @param
       * @return
    　　* @author liangchunbo
    　　* @date 2020-07-27 18:52
    　　*/
    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new RandomRule();
    }
}

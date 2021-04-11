package cn.cst.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * session托管到redis
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 600,redisNamespace = "myNamespace")
public class SessionConfig {
}
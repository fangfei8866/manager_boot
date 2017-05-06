package com.zhang.config;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 缓存配置;
 *
 * 注意：RedisCacheConfig这里也可以不用继承：CachingConfigurerSupport，也就是直接一个普通的Class就好了；
 *
 * 这里主要我们之后要重新实现 key的生成策略，只要这里修改KeyGenerator，其它位置不用修改就生效了。
 *
 * 普通使用普通类的方式的话，那么在使用@Cacheable的时候还需要指定KeyGenerator的名称;这样编码的时候比较麻烦。
 *
 * @version v.0.1
 */
@Configuration
@EnableCaching//启用缓存，这个注解很重要；
@EnableRedisHttpSession
public class RedisCacheConfig extends CachingConfigurerSupport {
   
	@Bean
	public JedisPoolConfig jedisPoolConfig(
			@Value("${spring.redis.pool.max-idle}") int maxIdle,
			@Value("${spring.redis.pool.min-idle}") int minIdle,
			@Value("${spring.redis.pool.max-active}") int maxTotal,
			@Value("${spring.redis.pool.max-wait}") int maxWaitMillis
			){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(maxIdle);
		jedisPoolConfig.setMinIdle(minIdle);
		jedisPoolConfig.setMaxTotal(maxTotal);
		jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
		return jedisPoolConfig;
		
	}
	
	@Bean  
    public JedisConnectionFactory redisConnectionFactory(
    		 JedisPoolConfig jedisPoolConfig,
    		 @Value("${spring.redis.host}") String hostName,
    		 @Value("${spring.redis.port}") int port,
    		 @Value("${spring.redis.password}") String password,
    		 @Value("${spring.redis.database}") int index
    		) {  
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        factory.setHostName(hostName);
        factory.setPort(port);
        factory.setPassword(password);
        factory.setDatabase(index);
        factory.setPoolConfig(jedisPoolConfig);
        return factory;  
    }  
	
    /**
     * 缓存管理器.
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
    	
       CacheManager cacheManager = new RedisCacheManager(redisTemplate);
       return cacheManager;
    }
 
   
    /**
     * RedisTemplate缓存操作类,类似于jdbcTemplate的一个类;
     *
     * 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
     *
     * 这里在扩展下：RedisTemplate这个类不见得很好操作，我们可以在进行扩展一个我们
     *
     * 自己的缓存类，比如：RedisStorage类;
     *
     * @param factory : 通过Spring进行注入，参数在application.properties进行配置；
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
       RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
       redisTemplate.setConnectionFactory(factory);
      
       //key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
       //所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
       //或者JdkSerializationRedisSerializer序列化方式;
       RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
       
       ObjectMapper mapper = new ObjectMapper();
       mapper.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
       mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
       RedisSerializer<Object> hashValueSerializer = new GenericJackson2JsonRedisSerializer(mapper);
       
       redisTemplate.setKeySerializer(redisSerializer);
       redisTemplate.setValueSerializer(hashValueSerializer);
       redisTemplate.setHashKeySerializer(redisSerializer);
       redisTemplate.setHashValueSerializer(hashValueSerializer);
       
       
       return redisTemplate;
    }
   
    /**
     * 自定义key.
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
     */
    @Override
    public KeyGenerator keyGenerator() {
       System.out.println("RedisCacheConfig.keyGenerator()");
       return new KeyGenerator() {
           @Override
           public Object generate(Object o, Method method, Object... objects) {
              // This will generate a unique key of the class name, the method name
              //and all method parameters appended.
              StringBuilder sb = new StringBuilder();
              sb.append(o.getClass().getName());
              sb.append(method.getName());
              for (Object obj : objects) {
                  sb.append(obj.toString());
              }
              System.out.println("keyGenerator=" + sb.toString());
              
              
              return sb.toString();
           }
       };
    }
 
}
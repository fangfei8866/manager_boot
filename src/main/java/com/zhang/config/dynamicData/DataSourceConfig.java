package com.zhang.config.dynamicData;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

/**
 * 定义多个数据源并且配置 sqlSessionFactory  DataSourceTransactionManager
 * @author hadoop
 */
@Configuration
public class DataSourceConfig {

	/**
     * 加载主数据源配置.
     * @param env
     */
    @Bean(name ="writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "master.datasource")
    public DataSource writeDataSource(){
    	return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }
    
    /**
	 * 有多少个从库就要配置多少个
	 * @return
	 */
	@Bean(name = "readDataSource1")
	@ConfigurationProperties(prefix = "slave1.datasource")
	public DataSource readDataSourceOne() {
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}
	
	 /**
	 * 配置数据源的代理
	 * @return
	 */
	@Bean(name = "roundRobinDataSouceProxy")
	public AbstractRoutingDataSource roundRobinDataSouceProxy() {
		int size = 1;
		RoutingDataSource proxy = new RoutingDataSource(size);
		
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		DataSource writeDataSource = writeDataSource();
		// 写
		targetDataSources.put(DataSourceType.write.getType(),writeDataSource);
		
		for (int i = 0; i < size; i++) {
			targetDataSources.put(i, readDataSourceOne());
		}
		proxy.setDefaultTargetDataSource(writeDataSource);
		proxy.setTargetDataSources(targetDataSources);
		return proxy;
	}
	
	
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(roundRobinDataSouceProxy());
        bean.setTypeAliasesPackage("com.example.po");
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean
    public DataSourceTransactionManager transactionManagers() {
        return new DataSourceTransactionManager(roundRobinDataSouceProxy());
    }
   
}

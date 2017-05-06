package com.zhang.config.dynamicData;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

/**
 * 多数据源切换
 * @author:1032543937
 * @date 2016/5/30 14:34
 */
public class RoutingDataSource extends AbstractRoutingDataSource {
	
    private static final Logger log = LoggerFactory.getLogger(RoutingDataSource.class);
    
	private final int dataSourceNumber;
	
	private AtomicInteger count = new AtomicInteger(0);
	
	public RoutingDataSource(int dataSourceNumber) {
		this.dataSourceNumber = dataSourceNumber;
	}
	
	@Override
	protected Object determineCurrentLookupKey() {
		Object resultObject=null;
		String typeKey = DataSourceContextHolder.getJdbcType();
		
		//如果typeKey为空表示获取主库的datasource
		if (StringUtils.isEmpty(typeKey) || typeKey.equals(DataSourceType.write.getType())){
			resultObject= DataSourceType.write.getType();
		}else{
			// 读简单负载均衡
			int number = count.getAndAdd(1);
			int lookupKey = number % dataSourceNumber;
			resultObject= new Integer(lookupKey);
		}
		
		log.info("determineCurrentLookupKey:"+resultObject);
		return resultObject;
	}
}

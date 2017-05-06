package com.zhang.curator;

import javax.annotation.PostConstruct;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhang.base.MySwitch;

public class ReadWriteEnableListener{
	
	private static Logger logger = LoggerFactory.getLogger(ReadWriteEnableListener.class);
	
	@Autowired
	private CuratorFramework curatorFramework;
	 
    private String path;//要监听的路径
	
	//建立一个PathChildrenCache缓存 第三个参数为是否接受节点数据内容 如果为false 则不接受
    public ReadWriteEnableListener(String path) {
        this.path = path;
    }
	
	//在初始化的时候就进行监听
    @PostConstruct
	protected void call() throws Exception {
		 initData();
		final PathChildrenCache cache = new PathChildrenCache(curatorFramework, path, true);
		cache.start(StartMode.POST_INITIALIZED_EVENT);
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD ADD:"+event.getData().getPath());
					break;
				case CHILD_UPDATED:
					switch (event.getData().getPath()) {
					case "/readwrite/enable":
						String data = new String(event.getData().getData());
						if(data != null){
		                	if("true".equalsIgnoreCase(data)){
		                		MySwitch.readWriteEnable = true;
		                	}if("false".equalsIgnoreCase(data)){
		                		MySwitch.readWriteEnable = false;
		                	}
		                } 
						break;
					default:
						break;
					}
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED:"+event.getData().getPath());
					break;
				default:
					break;
				}
			}
		});
	}

	private void initData() throws Exception {
         if (curatorFramework.checkExists().forPath(path+"/enable") == null) { // 没有节点创建节点 并且把本地的配置同步上去
        		curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path+"/enable", MySwitch.isCacheEnable.toString().getBytes());
         }
	}
}

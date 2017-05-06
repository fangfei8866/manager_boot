# manager_boot
学习springboot  这里集成了平时使用的技术

1 生成实体maven命令:(当前只生成mapper,dao po)
mybatis-generator:generate 

2 集成了如下功能

   1 通用mapper 和分页插件 PageHelper,redis,Druid
   
   2  Curator 实现了监听是否启用redis缓存和是否启用数据库读写分离 
   
   3 实现了多数据源以及动态切换主从数据库的功能 com.zhang.config.dynamicData 配置在这个目录下 ，但是只要是在service层添加了事务就会确定使用哪个数据     源，这里使用AOP拦截@Transactional来实现
   
   4 集成了rabbitmq 并提供了 rabbit多种模式下的dome
   
   5 集成了spring security 并实现了 基于url的页面中的过滤 自定义了权限的决策和元数据等信息，具体的配置目录在com.zhang.config.security包
   

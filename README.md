# micro

### 前言

micro` 微服务模板

### 项目API接口文档

### 项目介绍
xxxx

### 组织结构
```
micro
├── auth -- 认证中心
├── common -- 工具类及通用代码
├── security -- spring security配置
├── mybatis -- mabatis以及实体类
├── eureka -- 注册中心
├── consumer -- API客户端（接口提供）
├── cart -- 购物车服务
├── search -- 搜索服务
└── system -- 系统服务

```

**后端技术**

|        技术        |           说明           |                      官网                       |
| :----------------: | :----------------------: | :---------------------------------------------: |
|     SpringBoot     |       容器+MVC框架       |     https://spring.io/projects/spring-boot      |
|    SpringCloud     |        微服务架构        |     https://spring.io/projects/spring-cloud     |
|    MyBatis    |         ORM框架          |             https://mp.baomidou.com             |
|   Elasticsearch    |         搜索引擎         |    https://github.com/elastic/elasticsearch     |
|      RocketMQ      |         消息队列         |            https://www.rabbitmq.com             |
|   Spring security     |        认证授权        |    https://spring.io/projects/spring-security/    |
|      Redisson      |         分布式锁         |      https://github.com/redisson/redisson       |
|      Seata      |         分布式事务         |      https://seata.io       |

### 环境搭建

#### 开发工具

|     工具      |        说明         |                      官网                       |
| :-----------: | :-----------------: | :---------------------------------------------: |
|     IDEA      |    开发Java程序     |     https://www.jetbrains.com/idea/download     |
| RedisDesktop  | redis客户端连接工具 |        https://redisdesktop.com/download        |
|  SwitchHosts  |    本地host管理     |       https://oldj.github.io/SwitchHosts        |
|    X-shell    |  Linux远程连接工具  | http://www.netsarang.com/download/software.html |
|    Navicat    |   数据库连接工具    |       http://www.formysql.com/xiazai.html       |
| PowerDesigner |   数据库设计工具    |             http://powerdesigner.de             |
|    Postman    |   API接口调试工具   |             https://www.postman.com             |
|    Jmeter     |    性能压测工具     |            https://jmeter.apache.org            |

#### 开发环境

|     工具      | 版本号 |                             下载                             |
| :-----------: | :----: | :----------------------------------------------------------: |
|      JDK      |  1.8   | https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html |
|     Mysql     |  5.6   |                    https://www.mysql.com                     |
|     Redis     | Redis  |                  https://redis.io/download                   |
| Elasticsearch | 7.1.0  |               https://www.elastic.co/downloads               |
|   RocketMQ    | 5.0.0  |            http://www.rabbitmq.com/download.html             |
|   Seata    | 1.5.0  |            http://seata.io             |

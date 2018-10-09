# tyqx-cms 

#### 项目介绍
项目来源网上，本人进行改进并运用开发。
本系统完全开源免费，基于springboot、mybatisplus、shiro、dubbo、zookeeper、log4j、layuicms2.0、mysql5.6、redis、jdk1.8开发而成，内置代码生成器，能够快速生成service层、dao层、实体层代码，节省开发时间，快速构建企业级的web应用系统。具备完整的权限管理功能，代码简洁，容易入门，方便您进行二次开发。

#### 软件架构

- 核心框架：SpringBoot
- 安全框架：Shiro（细粒度控制：目录菜单按钮都按权限加载）
- 缓存框架：Redis
- 分布式框架：Dubbo（注册中心：Zookeeper）
- 持久层框架：MyBatisPlus
- 数据库连接池：Druid
- 日志管理：Log4j
- 前端框架：Layui
- 后台模板：LayuiCMS2.0

#### 使用说明

1. 运行redis、zookeeper
2. 运行服务提供方ServiceApplication，再运行服务消费方Application
3. 导入import.sql到mysql数据库
4. 访问http://127.0.0.1:5555/ （注：管理员账号：admin 密码：123456，测试账号：test 密码：123456）

## SpringCloud 学习所写项目
## 项目各模块介绍

## 使用 Consul 注册中心示例
### SpringBoot-Admin-Server
监控中心，监控服务信息，配置了邮件告警
### SpringCloud-Consul-Config-Server
SpringCloud的Consul公共配置中心，项目启动会自动将(application|application-develop).properties的配置注册到consul上，
在consul上更改配置都会通过消息总线通知到项目自动变更配置，达到不停机更改配置的效果。  
### SpringCloud-Consul-Producer
设置一个测试接口，并注册到consul服务中心上，变成一个服务，供调用测试
### SpringCloud-Consul-Consumer
设置一个服务调用方，使用轮询负载均衡，记录调用链
### SpringCloud-Consul-Consumer-Feign
设置一个服务调用方，使用Feign 模块快速调用服务自带负载均衡，记录调用链。
### SpringCloud-Consul-Turbine
配置服务熔断监控

## 使用 Eureka 注册中心示例
### SpringCloud-Eureka-Client
配置简单的API接口并注册成服务，供测试调用，并配置熔断器防止失败重复请求，或超时造成的雪崩
### SpringCloud-Eureka-Feign-Service
使用Feign调用服务
### SpringCloud-Eureka-Gateway-Service
配置服务网关，对路由进行过滤转发
### SpringCloud-Eureka-Git-Config-Client
git配置中心客户端，利用消息总线通过RabbitMq获取配置
### SpringCloud-Eureka-Git-Config-Server
git配置中心配置启动
### SpringCloud-Eureka-Ribbon-Service
使用Ribbon调用服务
### SpringCloud-Eureka-Server
注册中心服务启动
### SpringCloud-Eureka-Turbine
服务熔断健康
### SpringCloud-Eureka-Zipkin-Server
服务调用链查询
### SpringCloud-Eureka-Zuul
使用Zuul做路由转发和过滤器
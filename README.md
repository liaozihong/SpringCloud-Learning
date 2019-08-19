## 项目各模块介绍

### 使用 Consul 注册中心示例
#### SpringBoot-Admin-Server
服务监控中心，监控服务信息，配置了邮件告警，目前仅配置了监控SpringCloud-Eureka 相应的服务
#### SpringCloud-Consul-Config-Server
SpringCloud的Consul公共配置中心，项目启动会自动将(application|application-develop).properties的配置注册到consul上，
在consul上更改配置都会通过消息总线通知到项目自动变更配置，达到不停机更改配置的效果。  
#### SpringCloud-Consul-Producer
创建了一个测试接口，并注册到consul服务中心上，供调用测试
#### SpringCloud-Consul-Consumer
创建了服务调用方，使用LoadBalancerClient 负载均衡客户端调用服务，设置异常熔断，并记录调用链
#### SpringCloud-Consul-Consumer-Feign
创建了服务调用方，使用Feign 模块快速调用服务自带负载均衡，设置异常熔断，记录调用链。
#### SpringCloud-Consul-Turbine
配置查看服务实时使用情况(熔断记录等)

### 使用 Eureka 注册中心示例
#### SpringCloud-Eureka-Client
配置简单的API接口并注册成服务，供测试调用，并配置熔断器防止失败重复请求，或超时造成的雪崩
#### SpringCloud-Eureka-Feign-Service
使用Feign调用服务，并配置熔断器
#### SpringCloud-Eureka-Gateway-Service
配置服务网关，对路由进行过滤转发
#### SpringCloud-Eureka-Git-Config-Client
git配置中心客户端，利用消息总线通过RabbitMq获取配置
#### SpringCloud-Eureka-Git-Config-Server
git配置中心配置启动
#### SpringCloud-Eureka-Ribbon-Service
使用Ribbon调用服务
#### SpringCloud-Eureka-Server
Eureka注册中心服务端
#### SpringCloud-Eureka-Turbine
服务实时调用情况客户化查看
#### SpringCloud-Eureka-Zipkin-Server
服务调用链查询
#### SpringCloud-Eureka-Zuul
使用Zuul做路由转发和过滤器

### 使用 Nacos 注册中心示例
#### SpringCloud-Nacos-Config-Example
SpringCloud实现统一配置管理、动态更改
#### SpringCloud-Nacos-Service-Provider
SpringCloud 服务提供者，并注册到nacos上
#### SpringCloud-Nacos-Service-Consumer
SpringCloud 服务消费者，配置负载均衡，使用服务名调用服务，请求浏览会分摊到以后的服务上
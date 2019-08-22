## 简介
Spring Cloud Gateway是Spring Cloud官方推出的第二代网关框架，取代Zuul网关。网关作为流量的，在微服务系统中有着非常作用，网关常见的功能有路由转发、权限校验、限流控制等作用。本文首先用官方的案例带领大家来体验下Spring Cloud的一些简单的功能。  

在上面的myRoutes方法中，使用了一个RouteLocatorBuilder的bean去创建路由，除了创建路由RouteLocatorBuilder可以让你添加各种predicates和filters，predicates断言的意思，顾名思义就是根据具体的请求的规则，由具体的route去处理，filters是各种过滤器，用来对请求做各种判断和修改。

网关作为一个系统的流量的入口，有着举足轻重的作用，通常的作用如下：

    协议转换，路由转发
    流量聚合，对流量进行监控，日志输出
    作为整个系统的前端工程，对流量进行控制，有限流的作用
    作为系统的前端边界，外部流量只能通过网关才能访问系统
    可以在网关层做权限的判断
    可以在网关层做缓存

Spring Cloud Gateway作为Spring Cloud框架的第二代网关，在功能上要比Zuul更加的强大，性能也更好。随着Spring Cloud的版本迭代，Spring Cloud官方有打算弃用Zuul的意思。在笔者调用了Spring Cloud Gateway的使用和功能上，Spring Cloud Gateway替换掉Zuul的成本上是非常低的，几乎可以无缝切换。Spring Cloud Gateway几乎包含了zuul的所有功能。  

![](https://ws1.sinaimg.cn/large/006mOQRagy1g674zlr06uj30cb0gjmxj.jpg)  

参考链接：  
https://blog.csdn.net/forezp/article/details/84926662  
[史上最简单的SpringCloud教程 | 第十六篇: Spring Cloud Gateway 之filter篇](https://blog.csdn.net/forezp/article/details/85057268)

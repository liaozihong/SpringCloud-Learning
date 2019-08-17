Spring Cloud Bus 将分布式的节点用轻量的消息代理连接起来。它可以用于广播配置文件的更改或者服务之间的通讯，
也可以用于监控。本文要讲述的是用Spring Cloud Bus实现通知微服务架构的配置文件的更改。

配置文件中配置 spring-cloud-starter-bus-amqp ,所以需要按照Rabbitmq，可基于Docker快速安装：
```
docker run -d --name myrabbitmq -p 5672:5672 -p 15672:15672 docker.io/rabbitmq:3-management
```

![](https://ws1.sinaimg.cn/large/006mOQRagy1fxow4enuxyj30yg0kb0ze.jpg)
参考链接：
https://blog.csdn.net/forezp/article/details/81041062
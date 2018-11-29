## SpringCloud注册中心 Eureka
关于Eureka注册中心简介和设计原理：
## Eureka简介
注册中心，里面有一个注册表，保存了各个服务所在的机器和端口号

## Eureka Server设计精妙的注册表存储结构
微服务注册中心如何承载大型系统的千万级访问。涉及到Eureka的巧妙设计，
## Eureka Server端优秀的多级缓存机制

## 常见注册中心

Feature | Consul	| zookeeper	| etcd	| euerka
---|---|---|---|---
服务健康检查 | 服务状态，内存，硬盘等 | (弱)长连接，keepalive | 连接心跳	| 可配支持
多数据中心 | 支持 | — | — | —
kv存储服务 | 支持 | 支持 | 支持 |—
一致性 | raft | paxos | raft | —
cap | ca | cp | cp | ap
使用接口(多语言能力) | 支持http和dns | 客户端 | http/grpc | http（sidecar）
watch支持 | 全量/支持long polling | 支持 | 支持 long polling | 支持 long polling/大部分增量
自身监控 | metrics | —	| metrics | metrics
安全 | acl/https | acl | https支持（弱）| —
spring cloud集成 | 已支持 | 已支持 | 已支持 | 已支持


具体方志鹏的介绍文章，参考链接：
https://blog.csdn.net/forezp/article/details/83999947

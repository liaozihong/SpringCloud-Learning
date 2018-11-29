## SpringCloud核心组件 Feign
Feign
Spring Cloud Netflix 的微服务都是以 HTTP 接口的形式暴露的，所以可以用 Apache 的 HttpClient 或 Spring 的 RestTemplate 去调用，
而 Feign 是一个使用起来更加方便的 HTTP 客戶端，使用起来就像是调用自身工程的方法，而感觉不到是调用远程方法。

Feign的一个关键机制就是使用了动态代理。
* 首先，如果你对某个接口定义了@FeignClient注解，Feign就会针对这个接口创建一个动态代理
* 接着你要是调用那个接口，本质就是会调用 Feign创建的动态代理，这是核心中的核心
* Feign的动态代理会根据你在接口上的@RequestMapping等注解，来动态构造出你要请求的服务的地址
* 最后针对这个地址，发起请求、解析响应

优点：
1. feign本身里面就包含有了ribbon

2. feign自身是一个声明式的伪http客户端，写起来更加思路清晰和方便

3. fegin是一个采用基于接口的注解的编程方式，更加简便


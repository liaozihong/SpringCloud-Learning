看单个的Hystrix Dashboard的数据并没有什么多大的价值，要想看这个系统的Hystrix Dashboard数据就需要用到
Hystrix Turbine。Hystrix Turbine将每个服务Hystrix Dashboard数据进行了整合。Hystrix Turbine的使用非常简单，
只需要引入相应的依赖和加上注解和配置就可以了。
```text
http://localhost:8506/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8506%2Fturbine.stream
```

## SpringBoot 监控中心
### SpringBoot Admin 介绍
Spring Boot Actuator提供了对单个Spring Boot的监控，信息包含：应用状态、内存、线程、堆栈等等，比较全面的监控了Spring Boot应用的整个生命周期。   
但是Actuator的监控也有一些问题：第一，所有的监控都需要调用固定的接口来查看，如果全面查看应用状态需要调用很多接口；第二，如果Spring Boot应用集群非常大，每个应用都需要调用不同的接口来查看监控信息，操作非常繁琐低效。为了解决上述问题，诞生了另外一个开源应用：Spring Boot Admin。  

Spring Boot Admin 是一个管理和监控Spring Boot 应用程序的开源项目，分为admin-server和admin-client两个组件，admin-server将采集到的相关应用的actuator端点数据显示在spring-boot-admin-ui上。另外，通过 spring-boot-admin 可以动态切换日志级别、监控应用的各项指标等。 
Spring Boot Admin可以同时监控多个Spring Boot应用，通过eureka、consul等注册中心的方式实现多服务的监控和管理，每个Spring Boot应用都是一个admin-client。  
### 配置Admin server
**添加依赖**  
```pom
 <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-server</artifactId>
        <version>2.0.1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
```
**配置文件application.yml**
见下文的完整yml配置  

(1) Spring Boot2.x中，Actuator默认只开放了/actuator/info、/actuator/health两个端点，如果要访问其他端口需要自己通过配置management.endpoints.web.exposure.include属性来实现，下面我们暴露了所有endpoints，如果应用到生产环境，考虑到安全问题，对于Actuator的Endpoints要根据需要进行配置。   
(2) Spring Boot Admin 注册到Eureka Server 中后，可以管理所有注册到 Eureka Server 的应用，包括 Spring Boot Admin自己，Spring Boot Admin 会自动从注册中心抓取这些应用的相关信息并进行监控。  

### 配置密码验证,导入SpringBoot的安全模块 
```pom
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
```
**在yml中配置用户名和密码**
文章下面以贴出admin server的完整yml配置  
security的拦截配置  
```java
    @Profile("insecure")
    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().permitAll()//
                    .and().csrf().disable();
        }
    }

    @Profile("secure")
    @Configuration
    public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

        private final String adminContextPath;

        public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
            this.adminContextPath = adminServerProperties.getContextPath();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");

            http.authorizeRequests()
                    .antMatchers(adminContextPath + "/assets/**").permitAll()
                    .antMatchers(adminContextPath + "/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler)
                    .and()
                    .logout().logoutUrl(adminContextPath + "/logout").and()
                    .httpBasic().and()
                    .csrf().disable();
            // @formatter:on
        }
    }
```
启动，访问localhost:8000，即可看到登录页面。  
![](https://ws1.sinaimg.cn/large/006mOQRagy1fxvossgberj30gy0cwaae.jpg)  
### 邮件告警
Spring Boot Admin将微服务中所有应用信息在后台进行了展示，非常方便我们对微服务整体的监控和治理。当某个服务的状态发生改变时，为保证运维人员能及时知道，Spring Boot Admin支持邮件告警功能。实现步骤：
引入jar包
```pom
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```
需要修改yml配置，这里贴出完整的admin server 配置
```yaml
spring:
  application:
    name: admin-server
  profiles:
    active:
    - secure
  boot:
    admin:
      notify:
        mail:
          to: 15017263266@163.com  # 邮件接收方
          from: 911858173@qq.com  # 邮件发送方
  mail:
    host: smtp.qq.com   # qq邮箱服务器主机host，需要在QQ邮箱设置中开启smtp协议 163邮箱为smtp.163.com
    username: 911858173@qq.com  # qq邮箱账号
    password: nqylfjzcykkpbcch  # 第三方登录的授权码
    properties:
      mail.debug: false
      mail.smtp.auth: true
      mail.smtp.starttls.enable: true
      mail.smtp.ssl.enable: true

# tag::configuration-eureka[]
eureka:   #<1>
  instance:
    leaseRenewalIntervalInSeconds: 10
    health-check-url-path: /actuator/health #2.0后actuator的地址发生了变化
  client:
    registryFetchIntervalSeconds: 5
    serviceUrl:
      defaultZone: ${EUREKA_SERVICE_URL:http://localhost:8761}/eureka/

# 2.0开始，actuator默认不开放，所以要设置为开发
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS
server:
  port: 8000
# end::configuration-eureka[]

---
spring:
  profiles: insecure

---
# admin登录的用户名和密码
spring:
  profiles: secure
  security:
    user:
      name: "root"
      password: "root"

# 注册给eureka的时候告诉eureka自己的密码
eureka:
  instance:
    metadata-map:
      "user.name": ${spring.security.user.name}         #These two are needed so that the server
      "user.password": ${spring.security.user.password} #can access the protected client endpoints

```
日志告警简单配置好后，当有服务重启时，你会收到如下类型的邮件：  
![](https://ws1.sinaimg.cn/large/006mOQRagy1fxvov9ipnxj30gp0fndgt.jpg)  

### 客户端程序配置  
引入jar包
```
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.0.0</version>
</dependency>
```
添加yml配置  
```yaml
server:
  port: 8762
spring:
  application:
    name: service-hi
  boot:
    admin:
      client:
        url: "http://localhost:8000"
        instance:
          prefer-ip: true

eureka:
  instance:
    leaseRenewalIntervalInSeconds: 10
    health-check-url-path: /actuator/health
  client:
    registryFetchIntervalSeconds: 5
    serviceUrl:
      defaultZone: ${EUREKA_SERVICE_URL:http://localhost:8761}/eureka/
# 开启所有端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS
```
启动项目，即可在管理界面看到该服务。  
![](https://ws1.sinaimg.cn/large/006mOQRagy1fxvotl50f8j30q008vt8r.jpg)  
可查看详细信息：  
![](https://ws1.sinaimg.cn/large/006mOQRagy1fxvouj9d3tj31480m2tac.jpg)  

代码已上传至Github: https://github.com/liaozihong/SpringCloud-Learning/tree/master/SpringBoot-Admin-Server  

参考链接：https://blog.csdn.net/u013739073/article/details/80970302





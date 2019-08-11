# resubmit-spring-boot-starter

<p align="center">
    <img src="https://img.shields.io/badge/JDK-1.8+-green.svg" />
</p>

#### 介绍
1. 一个单机版的幂等提交 巧妙的使用了并发缓存之
2. ConcurrentHashMap的putIfAbsent的方法,当已经存在key就不能再set
3. 使用了表单提交,请求参数的content的内容MD5加密后作为key

#### 软件架构
依赖spring-boot-starter-aop

#### 原理说明
AOP + Reflect

#### 作用范围
任意由**spring**调用的方法

#### 安装教程

mvn install 本starter
##### 使用说明
1. 使用@Resubmit(delaySeconds = 5,msg = "用户已经提交了,请勿重新提交")注解
2. 配置异常拦截ResubmitException

##### 使用示例
1.注解

```asp
    @PostMapping("/save/valid")
    @Resubmit(delaySeconds = 5,msg = "用户已经提交了,请勿重新提交22")
    public RspDTO save(@RequestBody @Validated UserDTO userDTO) {
        userService.save(userDTO);
        return RspDTO.success();
    }
```
2.异常拦截
 ```asp
@ExceptionHandler(ResubmitException.class)
    public RspDTO handleResubmitException(ResubmitException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(Constant.RESUBMIT, e.getMessage());
    }
```   
3.依赖
  ```asp
 <dependency>
      <groupId>org.mybot</groupId>
         <artifactId>resubmit-spring-boot-starter</artifactId>
         <version>1.0.0</version>
   </dependency>    
```  
# 一个简易管理系统后端
前后端分离使用JWT(JSON WEB TOKEN)校验登录,数据库使用sqlite,前端代码请跳转[version-publish](https://github.com/DonyWan/version-publish)
## 配置
在download项目到开发环境中,请自行配置数据库位置以及端口号,`application.yml`

```
server:
  port: 8089
spring:
  datasource:
    driver-class-name: org.sqlite.JDBC
    url: jdbc:sqlite:versionpublish.db
```

## 安装
在打包jar完成之后,在服务器上执行下面命令

```
$ nohup java -jar xxx.jar >temp.txt &
```

> 如果打包成war包直接放在tomcat的webapp目录下即可

# 参考
- [springboot](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/)
- [springboot-jpa](https://docs.spring.io/spring-data/jpa/docs/2.2.0.RELEASE/reference/html/#reference)
- [querydsl](http://www.querydsl.com/)
- [jjwt](https://github.com/jwtk/jjwt)

# 联系我
如有问题,请提交[issues](https://github.com/DonyWan/springboot-jpa-querydsl-sqlite/issues) or  
邮件联系dundun.wang
# License
Copyright (c) 2019 [donywan](https://github.com/DonyWan/) by [BSD](LICENSE)

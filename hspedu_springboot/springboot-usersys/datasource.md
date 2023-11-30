# Springboot Test #

1.1 引入依赖

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
</dependency>
```

1.2 测试类

```java

@SpringBootTest//搭配这个注解使用
public class SpringBootDemoApplicationTests {

    @Test
    void forTest() {
//        ......
    }

}
```

# Spring Boot 整合dataSource #

## 1.HikariDataSource ##

### 1.1 xml配置 ###

````xml
<!--引入starter-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jdbc</artifactId>
</dependency>
        <!--引入mysql-->
<dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<version>5.1.49</version>
</dependency>
````

### 1.2 yml配置 ###

```yml
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 50MB
  datasource: #配置数据源 
    url: jdbc:mysql://localhost:3306/spring_boot?useSSL=false&useUnicode=true&characterEncoding=UTF-8
    username: 用户名
    password: 密码
    driver-class-name: com.mysql.jdbc.Driver
```

### 1.3 Java中测试连接 ###

在 test文件夹下

```java

@SpringBootTest
public class ApplicationTest {
    //小伙伴回忆一下，在讲解spring时，讲过JdbcTemplate
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {

        BeanPropertyRowMapper<Furn> rowMapper =
                new BeanPropertyRowMapper<>(Furn.class);

        List<Furn> furns = jdbcTemplate.query("SELECT * FROM `furn`", rowMapper);
        for (Furn furn : furns) {
            System.out.println(furn);
        }

        System.out.println(jdbcTemplate.getDataSource().getClass());
    }
}
```

## 2.整合Druid ##

中文手册: https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98

### 2.1 比较方便的引入方法 ###
#### 2.1.1 引入依赖 ####

```xml

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.17</version>
</dependency>
```
#### 2.1.2 yml配置 ####

```yml
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 50MB
  datasource: #配置数据源
    # 说明: 如果你没有指定useSSL=true ,启动项目会报红警告, 环境的问题，小伙伴们灵活处理
    url: jdbc:mysql://localhost:3306/spring_boot?useSSL=true&useUnicode=true&characterEncoding=UTF-8
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
#    配置druid和监控功能 这些需要你自己去配置
    druid:
      stat-view-servlet:
        enabled: true
        login-username: jack
        login-password: 666
        reset-enable: false
      web-stat-filter: #配置web监控
        enabled: true
        url-pattern: /*
        exclusions: '*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*'
      filter:
        stat: #sql监控
          slow-sql-millis: 1000
          log-slow-sql: true
          enabled: true
        wall: #配置sql防火墙
          enabled: true
          config:
            drop-table-allow: false
            select-all-column-allow: true
```

### 2.2 通过配置类引入 ###
#### 2.2.1 引入依赖 ####

```xml

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.17</version>
</dependency>
```

#### 2.2.2 yml配置 ####

```yml
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 50MB
  datasource: #配置数据源 
    url: jdbc:mysql://localhost:3306/spring_boot?useSSL=false&useUnicode=true&characterEncoding=UTF-8
    username: 用户名
    password: 密码
    driver-class-name: com.mysql.jdbc.Driver
```

#### 2.2.3 config 配置类 ####

```Java

@Configuration
public class DruidDataSourceConfig {

    //配置数据源 application.yml里面的spring的datasource
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {


        DruidDataSource druidDataSource = new DruidDataSource();
        //druidDataSource.setUrl();
        //druidDataSource.setUsername();
        //druidDataSource.setPassword();
//        底层会关联调用setUrl, setUsername, setPassword
        //加入监控功能 stat, 加入了sql防火墙监控 wall
        druidDataSource.setFilters("stat,wall");
        return druidDataSource;
    }

    //配置druid的监控页功能
    @Bean
    public ServletRegistrationBean statViewServlet() {
        //创建StatViewServlet
        StatViewServlet statViewServlet = new StatViewServlet();
//        这里是访问的路径 localhost:8080/druid/*
        ServletRegistrationBean<StatViewServlet> registrationBean =
                new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        //设置init-parameter, 设置用户名和密码
        registrationBean.addInitParameter("loginUsername", "hsp");
        registrationBean.addInitParameter("loginPassword", "666666");

        return registrationBean;


    }

    //配置WebStatFilter, 用于采集web-jdbc关联的监控数据
    @Bean
    public FilterRegistrationBean webStatFilter() {
        //创建 WebStatFilter
        WebStatFilter webStatFilter = new WebStatFilter();

        FilterRegistrationBean<WebStatFilter> filterRegistrationBean =
                new FilterRegistrationBean<>(webStatFilter);

        //默认对所有的url请求进行监控
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        //排除指定的url
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
```
##### 2.2.3.1 测试sql监控功能和监控功能 #####

测试德鲁伊的sql监控功能，刷新这个页面，就可以在监控页面看到sql的执行情况以及uri的监控情况


```java
@Controller
public class DruidSqlController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/sql")
    public List<Furn> crudDB() {

        BeanPropertyRowMapper<Furn> rowMapper = new BeanPropertyRowMapper<>(Furn.class);
        List<Furn> furns = jdbcTemplate.query("select * from `furn`", rowMapper);
        for (Furn furn : furns) {
            System.out.println(furn);
        }
        return furns;
    }

}
```





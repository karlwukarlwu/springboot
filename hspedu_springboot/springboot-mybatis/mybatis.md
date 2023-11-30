## lombok的引入 ##
```xml

        <!--引入配置处理器 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>

        <!--引入lombok-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```


# spring boot集成mybatis #

mybatis 的扫描和配置
application.yml中的配置需要扫描的mapper.xml文件路径和config-location配置文件路径
resources/mapper 文件夹下对应生成的mapper.xml文件,mapper.xml文件扫描<mapper namespace="org.mapper.MonsterMapper">
需要扫描的类加上@Mapper注解

## 1.pom.xml ##
```xml
    <!--引入mybatis starter, 如果小伙伴看不到 版本，自己手写2.2.2-->
    <dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>2.2.2</version>
    </dependency>
    <!--引入mysql驱动: 这里老师使用版本仲裁 8.0.26-->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
    </dependency>
```

## 2.application.yml ##
位于resources目录下的application.yml文件，
负责配置mybatis的扫描和配置文件路径，以及数据库连接信息

自己看着改 有的地方不一样
```yaml
server:
  port: 9090
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springboot_mybatis?useSSL=false&useUnicode=true&characterEncoding=UTF-8
    username: root
    password: root


mybatis:
  #指定要扫描的 Xxxmapper.xml
  mapper-locations: classpath:mapper/*.xml

  #通过config-location 可以指定mybatis-config.xml，可以以传统的方式来配置mybatis
  #config-location:
#  config-location: classpath:mybatis-config.xml

  #我们也可以直接在application.yml进行配置
  #举例说明1. 比如配置原来的 typeAliases
  #举例说明2 配置输出底层的原生sql
  #还有很多其它的配置，我们使用到再说
  type-aliases-package: com.hspedu.springboot.mybatis.bean
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

  #老师说明: 配置mybatis的两种方式的选择: 如果配置比较简单，就直接在application.yml配置即可
  #如果配置内容比较多，可以考虑单独的做一个mybatis-config.xml
```

## 对应的mapper.xml文件 ##
位于mapper目录下的MonsterMapper.xml文件，负责配置sql语句
namespace 负责扫描目标Java类
```xml
<?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
                "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.mapper.MonsterMapper">
<!--配置getMonsterById-->
<select id="getMonsterById" resultType="Monster">
        SELECT * FROM `monster` WHERE id=#{id}
    </select>
</mapper>
```

## 3.对应的Mapper接口 ##
位于mapper目录下的MonsterMapper.java文件，负责配置sql语句
@Mapper 负责扫描目标Java类
```java
@Mapper
public interface MonsterMapper {
    //方法,根据id返回Monster对象
    public Monster getMonsterById(Integer id);
}
```
## 4. 测试 ##
```java
@SpringBootTest
public class ApplicationTest {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource //自动注入MonsterMapper @Mapper
    private MonsterMapper monsterMapper;

//    装配MonsterService 记得service上加@Service
    @Resource
    private MonsterService monsterService;


    @Test
    public void t1() {

        //输出看看当前的数据源是什么
        System.out.println("jdbcTemplate = "+jdbcTemplate.getDataSource().getClass());
    }


//    测试MonsterMapper接口
//    测试MonsterService
    @Test
    public void getMonsterById() {

        //Monster monster =
        //        monsterMapper.getMonsterById(1);
        //System.out.println("monster--" + monster);
        Monster monster = monsterService.getMonsterById(2);
        System.out.println("monster###" + monster);

    }
}
```

## controller 层配置 ##
```java
@Controller
public class MonsterController {

    //装配MonsterService
    @Resource
    private MonsterService monsterService;


    @ResponseBody
    @GetMapping("/monster")
    public Monster getMonsterById(@RequestParam(value = "id") Integer id){

        return monsterService.getMonsterById(id);
    }
}
```

## bean 解决时间不匹配问题 ##
```java
@Data
public class Monster {
    private Integer id;
    private Integer age;
    //这里通过注解来解决时区问题
    //GMT 就是格林尼治标准时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;
    private String email;
    private String name;
    private String gender;
    private Double salary;
}
```








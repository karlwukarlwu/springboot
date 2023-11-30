# springboot 整合mybatisPlus #

## 0.简介 ##

搭配mybatisX使用，可以自动生成mapper.xml文件里面的sql语句
需要手动创建一个mapper文件夹然后在里面创建一个mapper.xml文件

@TableName("表名") 表名和bean不一样的时候用这个

@MapperScan(basePackages = {"org.example.mapper"})
//指定扫描的mapper接口所在的包 用在@SpringBootApplication上 解决用太多的@Mapper的问题

## 1.引入依赖 ##

```xml

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.3</version>
</dependency>
```

## 2.配置文件 ##

```yaml
server:
  port: 9090
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springboot_mybatisplus?useSSL=false&useUnicode=true&characterEncoding=UTF-8
    username: root
    password: root

mybatis-plus:
  configuration: #进行mybatis-plus配置，配置项和mybatis是一样
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```

## 3. mapper的优化 ##

extends BaseMapper<beanName> 这段话是mybatis-plus提供的，可以直接使用

```java
/**
 * baseMapper 来自于mybatis-plus
 *
 * 1. BaseMapper已经默认提供了很多的crud方法，可以直接使用
 * 2. 如果BaseMapper 提供的方法不能满足业务需求，我们可以再开发新的方法，
 *    并在MonsterMapper.xml进行配置 => 使用插件开发
 */
@Mapper
public interface MonsterMapper extends BaseMapper<Monster> {
    //自定义方法
    //增加一个添加方法insert

    int insertSelective(Monster monster);

    int delByEmail(@Param("email") String email);


}
```

## 4. service层的优化 ##

### 4.1 service接口的优化 ###

extends IService<beanName> 这段话是mybatis-plus提供的，可以直接使用
提供了很多的crud方法

```java
/**
 * 1. 传统方式 在接口中 定义方法/声明方法, 然后在实现类中进行实现
 * 2. 在mybatis-plus中，我们可以继承父接口 IService
 * 3. 这个IService接口声明很多方法, 比如crud
 * 4. 如果默认提供方法不能满足需求，我们可以再声明需要的方法, 然后在实现类中进行实现即可
 */
public interface MonsterService extends IService<Monster> {
    //自定义方法
    //public void t1();
}
```

### 4.2 service实现类的优化 ###

```java
/**
 *
 * 1. 传统方式:在实现类中直接进行implements MonsterService
 * 2. 在mybatis-plus中,我们开发Service实现类, 需要继承 ServiceImpl
 * 3. 我们观察看到 ServiceImpl类实现 IService接口
 * 4. MonsterService 接口他继承了 IService接口
 * 5. 这里MonsterServiceImpl 就可以认为是实现了 MonsterService接口, 这样MonsterServiceImpl
 * 就可以使用IService接口方法，也可以理解成可以使用 MonsterService方法
 * 6. 如果MonsterService接口中，声明了其它的方法/自定义方法, 那么我们依然需要在MonsterServiceImpl
 * 类，进行实现
 *
 * ServiceImpl<MonsterMapper, Monster> 解决了 IService<Monster> 需要实现的方法
 */
@Service
public class MonsterServiceImpl
        extends ServiceImpl<MonsterMapper, Monster>
        implements MonsterService {
    //@Override
    //public void t1() {
    //
    //}
}
```

## 5. controller层的使用 ##

```java

@Controller
public class MonsterController {

    @Resource
    private MonsterService monsterService;

    //方法,根据id返回对应对象
    @GetMapping("/monster")
    @ResponseBody
    public Monster getMonsterById(@RequestParam(value = "id") Integer id) {
        return monsterService.getById(id);
    }

    //编写方法，返回所有的monster信息
    //后面我们还会说分页查询
    @GetMapping("/list")
    @ResponseBody
    public List<Monster> listMonster() {
        return monsterService.list();
    }
}
```

## bean的配置 ##

```java

@Data
//@TableName(value = "monster") 我们的mysql有一张monster表 所以这里不需要指定TableName
public class Monster {
    private Integer id;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;
    private String email;
    private String name;
    private String gender;
    private Double salary;
}
```

## 6. 启动 ##
```java
@MapperScan(basePackages = {"org.example.mapper"})//直接指定扫描的包路径
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```
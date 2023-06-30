# 项目结构说明
## controller：
此目录主要是存放**Controllerde ,比如：UserController.java，也有的
项目是把action放在controller目录下，有的是把UserController.java放在action目录下。
## service：
这里分接口和实现类，接口在service目录下，接口实现类在service/impl目录下。
## dao：
持久层，目前比较流行的Mybatis或者jpa之类的。
## entity：
就是数据库表的实体对象。
## param：
放的是请求参数和相应参数UserQueryRequest、BaseResponse等
## util：
通常是一些工具类，比如说：DateUtil.java、自定义的StringUtil.java
## interrupt：
项目统一拦截处理，比如：登录信息，统一异常处理
## exception：
自定义异常，异常错误码
## config：
配置读取相关，比如RedisConfig.java
# 请求/响应
## 请求
### 数组/集合参数
- 请求：http://localhost:8080/arrayParam?hobby=game&hobby=java
#### 数组
- 后端：
```java
@RestController
class test{
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby){
        System.out.println(Arrays.toString(hobby));
    return "OK";
    }
}
```
#### 集合（使用@RequestParam绑定参数）
- 后端：
```java
@RestController
class test{
    @RequestMapping(" /listParam")
        public String listParam(@RequestParam List<String> hobby){
            System. out.println(hobby);
        return "OK";
    }
}
```
### 日期参数（使用@DateTimeFormat注解绑定参数）
- 请求：http://localhost:8080/dateParam?updateTime= 2022-12-1210:05:45
- 后端：
```java
@RestController
class test{
    @RequestMapping(" /dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime){
        System. out.println(updateTime); 
        return "OK"; 
    }
}
```
### Json参数（使用@RequestBody标识）
- JSON数据键名与形参对象属性名相同，定义POJ0类型形参即可接收参数,需要使用@RequestBody标识
- 前端传参数：http://localhost:8080/jsonParam
{
    "name" : "Tom"
    "age" :10.
    "address" :{
        "province": "beijing".
        "city" :"beijing"
    }
}
- 后端
```java
@RestController
class test{
    @RequestMapping ("/jsonParam")
    public String jsonParam (@RequestBody User user) {
        System. out.println(user) ;
        return "OK";
    }
}
```
### 路径参数（使用@PathVariable获取路径参数）
- 请求：http://localhost:8080/path/1/Tom
- 后端：使用@PathVariable获取路径参数（路径参数名称要与形参名称一致）
```java
@RestController
class test{
    @RequestMapping("/path/{id}/{name}")
    public String pathParam(@PathVariable Integer id,@PathVariable String name){
        System. out.println(id + "=" + name); 
        return "OK" ;
    }
}
```
- 响应
## 响应
- @ResponseBody
    - 类型: 方法注解、类注解
    - 位置: Controller方法上/类.上
    - 作用: 将方法返回值直接响应,如果返回值类型是实体对象/集合,将会转换为JSON格式响应
    - 说明: @RestController = @Controller + @ResponseBody;
    - 把返回的数据包装成一个类用来返回
# 分成解耦-三层架构
- controller:控制层，接收前端发送的请求，对请求进行处理，并响应数据。
- service:业务逻辑层，处理具体的业务逻辑。
- dao:数据访问层(Data Access Object) (持久层)，负责数据访问操作，包括数据的增、删、改、查。
## 分层解耦（IOC-DI引入）
- 控制反转:Inversion Of Control,简称IOC。对象的创建控制权由程序自身转移到外部(容器)，这种思想称为控制反转。
- 依赖注入: Dependency Injection,简称DI。容器为应用程序提供运行时，所依赖的资源，称之为依赖注入。
- Bean对象: IOC容器中创建、管理的对象，称之为bean。
- Controller层里面的new的对象由IOC容器来管理，IOC容器来创建Service的实现类交给Controller层
## IOC-DI入门
- ① Service层及Dao层的实现类，交给IOC容器管理（控制反转），
     给Service层及Dao层加上@Component注解
- ② 为Controller及Service注入运行时，依赖的对象。（依赖注入），
     给要new的Service实现类及Dao实现类的对象加上@Autowired注解，
     加上之后IOC容器会自动为需要某个类型的提供该类型的bean对象，并赋值给
     加上了@Autowired注解的该变量
## IOC详解
- Bean的声明
- 要把某个对象交给IOC容器管理，需要在对应的类上加上如下注解之一:
- | 注解 | 说明 | 位置 |
  | ---- | ---- | ---- |
  | @Component | 声明bean的基础注解 | 不属于以下三类时，用此注解 |
  | @Controller | @Component的衍生注解 | 标注在控制器类上(Controller层) |
  | @Service | @Component的衍生注解 | 标注在业务类上(Service层) |
  | @Repository | @Component的衍生注解 | 标注在数据访问类上(Dao层，由于与mybatis整合，用的少) |
- 注意事项
    - 声明bean的时候， 可以通过value属性指定bean的名字, 如果没有指定，默认为类名首字母小写。
    - 使用以上四个注解都可以声明bean,但是在springboot集成web开发中，声明控制器bean只能用@Controller.
    - 控制层@RestController = @Controller + @ResponseBody;所以使用@RestController就包括了@Controller
- Bean组件扫描（如果把类放到扫描包外会报错，需要重新加上扫描注解，并声明扫描的包，重新写会覆盖默认的）
    - 前面声明bean的四大注解，要想生效,还需要被组件扫描注解@ComponentScan扫描。
    - @ComponentScan注解虽然没有显式配置,但是实际上已经包含在了启动类声明注解@SpringBootApplication
      中，默认扫描的范围是启动类所在包及其子包。
## DI详解
- Bean注入
    - @Autowired注解，默认是按照类型进行，如果存在多个相同类型的bean,将会报错
    - 通过以下几种方案来解决:
        - @Primary：  会使用加了这个注解的Bean
        - @Autowired + @Qualifier("bean的名称")：@Autowired默认根据类型注入
        - @Resource(name="bean的名称")：@Resource默认按照名称注入，可以用它替换@Autowired + @Qualifier("bean的名称")这两个
    - @Resource与@Autowired区别
        - @Autowired 是spring框架提供的注解，而@Resource是JDK提供的注解。
        - @Autowired 默认是按照类型注入，而@Resource默认是按照名称注入。
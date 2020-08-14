# Spring4

## 前言 什么是框架

    ·高度抽取可重用代码的一种设计
    ·高度的通用性

    

    工具类：
        commons-fileupload.jar
        commons-io.jar
        ...

    

    框架：
        抽取成一种高度可重用的，事务控制，强大的servlet，项目中的一些工具...
        多个可重用模块的集合，形成一个某个领域的整体解决方案

## 第一章 Spring概述

    容器（可以管理所有的组件）框架
        ※ 组件：具有功能的类

### 核心关注：

    IOC和AOP

### 优良特性：

    - 非侵入式
    - 依赖注入
    - 面向切面编程
    - 容器
    - 组件化
    - 一站式

### 各个模块：

    Test：Spring的单元测试模块

        - spring-test-4.0.0.RELEASE

    Core Container：核心容器（IOC）

        - Beans spring-beans-4.0.0.RELEASE
        - Core spring-core-4.0.0.RELEASE
        - Context spring-core-4.0.0.RELEASE
        - SpEL spring-expression-4.0.0.RELEASE

    AOP + Aspected：面向切面编程模块

        - spring-aop-4.0.0.RELEASE
        - spring-aspects-4.0.0.RELEASE
        - spring-instrument-4.0.0.RELEASE

    Data Access ：访问数据库

        - Jdbc spring-jdbc-4.0.0.RELEASE
        - ORM spring-orm-4.0.0.RELEASE
        - transaction spring-tx-4.0.0.RELEASE

    Integration ：数据集成

        - OXM spring-ox(xml)m-4.0.0.RELEASE
        - jms spring-jms-4.0.0.RELEASE

    Web：开发Web应用的模块

        - WebSocket spring-websocket-4.0.0.RELEASE
        - Servlet spring-web-4.0.0.RELEASE
        - Web spring-webmvc-4.0.0.RELEASE
        - Portlet spring-webmvc-portlet-4.0.0.RELEASE

    总结：
        用哪个模块导哪个包

    ※开发Spring框架的应用，经常要写框架的配置文件，写起来复杂，需要提示
        推荐安装插件

    不装插件可以使用Spring官方提供的sts开发工具

    Spring ---> IOC容器 --> 整合其他框架
           ---> AOP（面向切面编程） --> 声明式事务

## 第二章 IOC

### 2.1 什么是IOC？

    控制反转 - Inversion Of Control
        ·控制：资源的获取方式

            - 主动式：(需要的资源自给创建)

                BookServlet {
                    // 简单对象容易创建
                    BookService bs = new BookService();
                    // 复杂对象的创建是很庞大的工程
                    AirPlane ap = new AirPlane();
                    ap.setA()..
                    ap.setB()..
                }

            - 被动式：(需要的资源并非自己创建，而是交给容器来创建和设置)

                BookServlet {
                    BookService bs;

                    public void test01() {
                        bs.checkout();
                    }
                }

    容器：管理所有的组件（有功能的类）
        假设BookeServlet受容器管理，BookService也受容器管理，
        那么容器可以自动探查出哪些组件（BookServlet）需要用到另一些组件（BookService），
        就创建被需要的组件，并传递给需要它的组件。

    主动地new出资源变为被动接受

### 2.2 相关概念：DI

    依赖注入 - Dependency Injection

        容器通过反射的形式，将准备好的BookService对象赋值到BookServlet中

        只要容器管理的组件，都能使用容器提供的强大功能

### 2.3 HelloWorld（通过各种方式给容器中注册对象）

    自己new对象 -> 交给容器来创建

    ·导包
        导入核心容器4个jar包
    ·写配置
        spring的配置文件中，集合了spring IOC容器管理的所有组件
    ·编写测试类HelloWorld

### 2.4 几个细节

    ·ApplicationContext ioc = new ClassPathXmlApplicationContext("ioc.xml");

        - IOC配置文件在类路径下

        另外一个实现：FileSystemXmlApplicationContext()

        - IOC配置文件在磁盘目录下

    ·组件的创建工作由容器完成，而且在容器创建完成时就已经创建完成了

    ·同一个组件在IOC容器中是单例的

    ·容器中如果没有该组件，则会返回NoSuchBeanDefinitionException

    ·IOC容器在创建组件时利用setter方法为属性赋值

    ·JavaBean的属性名是由getter/setter方法决定

## 第三章 IOC实验

### 3.1 通过IOC容器创建对象，并为属性赋值

    HelloWorld

    

### 3.2 根据bean的类型从IOC容器中获取bean实例

    若果指定类型bean实例有一个以上，会返回NoUniqueBeanDefinitionException

    而且通过类型获取可以获得该类型的所有继承类

### 3.3 通过构造器为bean的属性赋值（index, type属性介绍）

    通过p名称空间为bean赋值

    <book>
        <b:name>西游记</b:name> ← 命名空间避免重复
        <price>19.92</price>
        <author>
            <a:name>吴承恩</a:name> ← 命名空间避免重复
            <gender>男</gender>
        </author>
    </book>

    ·加入名称空间
        xmlns:p="http://www.springframework.org/schema/p"
    ·在bean元素里添加属性p:lastname即可指定调用setter方法赋值

### 3.4 为各种属性赋值

    ·null
        <property name="lastName">
            <null/>
        </property>

    ·引用类赋值（其他bean，内部bean）
        > 其他bean
        <bean id="car01" class="pt.joja.bean.Car">
            <property name="carName" value="BWM"/>
            <property name="color" value="Green"/>
            <property name="price" value="30000"/>
        </bean>

        <property name="car" ref="car01" />

        > 内部bean
        <property name="car">
            <bean id="car02" class="pt.joja.bean.Car">
                <property name="carName" value="Toyota"/>
                <property name="color" value="black"/>
                <property name="price" value="10000"/>
            </bean>
        </property>

        注意：内部bean不能被外部获取，只能内部使用

    ·集合类型（List，Map，Properties）
        > List

        <property name="books">
            <list>
                <bean id="book01" class="pt.joja.bean.Book" p:bookName="西游记"/>
                <ref bean="book02"/>
            </list>
        </property>

        > Map

        <property name="maps">
            <map>
                <entry key="key01" value="Happiness"/>
                <entry key="key02" value="18"/>
                <entry key="key03" value-ref="book02"/>
                <entry key="key04">
                    <bean class="pt.joja.bean.Car">
                        <property name="carName" value="HelloBike"/>
                    </bean>
                </entry>
                <entry key="key05">
                    <map></map>
                </entry>
            </map>
        </property>

        > Properties

        <property name="properties">
            <props>
                <prop key="height">175cm</prop>
                <prop key="weight">80kg</prop>
            </props>
        </property>

    ·util名称空间创建集合类型的bean

        加入util命名空间

        

        <util:map id="myMap">
            <entry key="key01" value="ele01"/>
            <entry key="key02" value="28"/>
        </util:map>

    ·级联属性赋值

        级联属性：属性的属性

        <property name="car" ref="car01" />

        <property name="car.price" value="50000" />

        因为是引用，所以固有对象的属性会被修改

### 3.5 配置通过静态工厂方法创建的bean、实例工厂方法创建的bean、FactoryBean ☆

    bean的创建，默认就是框架利用反射new出来

    工厂模式：
        不必关心创建细节，复杂的工作由工厂完成

        AirPlane ap = AirPlaneFactory.getAirPlane(String jz);

        ·静态工厂

            - 工厂本身不需要创建对象，通过静态方法调用创建对象

            <bean id="plane2" class="pt.joja.factory.AirPlaneStaticFactory" factory-method="getAirPlane">
                <constructor-arg name="jz" value="张三"/>
            </bean>

        ·实例工厂

            - 工厂本身需要被创建对象，通过工厂的实例来创建对象

            <bean id="airplaneFactory" class="pt.joja.factory.AirPlaneInstanceFactory"/>
            <bean id="plane3" class="pt.joja.bean.AirPlane" factory-bean="airplaneFactory" factory-method="getAirPlane">
                <constructor-arg name="jz" value="张三"/>
            </bean>

        ·FactoryBean

            - Spring规定的一个接口
            - 编写实装FactoryBean的类
            - 在Spring中注册

            <bean id="myFactoryBeanImpl" class="pt.joja.factory.MyFactoryBean"/>

            配置完成后用id获取时获得的是工厂创建的对象

            ※IOC容器启动的时候不会创建对象实例（无论isSingleton()返回值如何）

### 3.6 通过继承实现bean配置信息的重用

    <bean id="person06" class="pt.joja.bean.Person" parent="person05">
        <property name="lastName" value="张小毛"/>
    </bean>

    ※class也可以省略，但是推荐加上

### 3.7 通过abstract属性创建一个模板bean

    <bean id="person05" class="pt.joja.bean.Person" abstract="true">

    继承专用，不可以获取实例
    否则会抛出BeanIsAbstractException

### 3.8 bean之间的依赖

    普通情况下bean的创建顺序与配置顺序相同

    <bean id="car" class="pt.joja.bean.Car" depends-on="book,person"></bean>
    <bean id="person" class="pt.joja.bean.Person"></bean>
    <bean id="book" class="pt.joja.bean.Book"></bean>

    book -> person -> car

### 3.9 测试bean的作用域，分别创建单实例和多实例的bean ☆

    bean的作用域：指定bean是否单实例

    bean的scope指定值：
        ·singleton 单实例（默认）

            - 在容器启动完场之前就创建完成
            - 任何时候获取的都是之前创建好的对象

        ·prototype 多实例

            - 容器启动默认不会创建，获取时才会创建
            - 任何时候获取的都是一个新的对象

        ·request 在web环境下，同一次请求创建一个bean实例（没用）
        ·session 在web环境下，同一次会话创建一个bean实例（没用）

### 3.10 创建带有生命周期方法的bean

    生命周期：
        bean从创建到销毁

        可以为bean指定一些生命周期方法，Spring在创建或者销毁bean的时候就会调用指定的方法

        可以自定义初始化方法和销毁方法，但是必须是无参的

        单实例：
            容器启动 -> bean构造器创建 -> 初始化方法 -> 容器关闭 -> 销毁方法

        多实例：
            获取 -> bean构造器创建 -> 初始化方法 -> 容器关闭不会调用销毁方法

### 3.11 测试bean的后置处理器

    后置处理器：
        Spring有一个接口：后置处理器 - 可以在bean的初始化前后调用方法

            BeanPostProcessor

        1. 编写后置处理器的类
        2. 在Spring中注册它

        在所有普通bean的初始化前后都会调用后置处理器的（无论有没有初始化方法）

            - postProcessBeforeInitialization
            - postProcessAfterInitialization

    ※和Servlet的Filter很像

### 3.12 引用外部属性文件 ☆

    应用场景：
        数据库连接池作为单实例是最好的，一个项目就一个连接池。
        可以让Spring来管理连接池。

    ·导包c3p0，dbdriver
    ·在dbconfig.properties中配置好数据库连接池属性
        jdbc.username=root

    ·引用命名空间context
        xmlns:context="http://www.springframework.org/schema/context"

    ·关联外部属性文件
        <context:property-placeholder location="classpath:dbconfig.properties" />

    ·用${}取值
        <property name="user" value="${jdbc.username}" />

    注意！
        username是Spring中的一个关键字，在配置文件中不要使用关键字username
        username关联到的是当前登录系统的用户名

### 3.13 基于XML的自动装配

    自动装配 - 自定义类型自动赋值(仅对于自定义类型有效)

        - 基本类型不需要特殊指定
        - 自定义类型的属性是一个对象，这个对象在容器中可能存在

        bean的autowire属性：
            ·default/no - 不自动装配
            ·byName - 按照名字：以属性名作为id在容器中找到这个组件，给它赋值
                ※如果找不到就装配null

            ·byType - 按照类型：以属性的类型为依据去容器中查找这个组件
                ※如果找不到就装配null
                ※若果容器中有多个此类型组件，则会抛出
                    UnsatisfiedDependencyException
                        -> NoUniqueBeanDefinitionException

                ※List<Bean>, Map<String,Bean>类型的也会自动匹配相应bean进行装配

            ·constructor - 按照构造器进行赋值
                ※需要一个有该类型的有参构造器
                
                匹配：

                    1. 按照构造器参数类型匹配，匹配到1个就进行装配；
                    2. 如果匹配到了多个，参数名作为id继续匹配，找到就装配；
                    3. 不会报错

### 3.14 [SpEL测试I] - Spring Expression Language

    ·在SpEL中使用字面量
        <property name="age" value="#{25}"/>
    ·使用运算符
        <property name="salary" value="#{12345*12}"/>
    ·引用其他bean
        <property name="car" value="#{car}"/>
    ·引用其他bean的某个属性值
        <property name="lastName" value="#{book1.name}"/>
    ·调用非静态方法
        <property name="gender" value="#{book1.getName().toUpperCase()}"/>
    ·调用静态方法
        <property name="email" value="#{T(java.util.UUID).randomUUID().toString().substring(0,10)}@joja.com"/>

    

### 3.15 通过注解分别创建Dao, Service, Controller（控制器 - 控制网站跳转逻辑） ☆

    ·通过给bean上添加某些注解，可以快速地将bean加入到ioc容器中

    ·Spring提供了4个注解：

        - @Controller ： 控制器，推荐给控制器层的组件添加（servlet）
        - @Service    ： 业务逻辑，推荐给业务逻辑层的组件添加（service）
        - @Repository ： 仓库，给数据库/持久化层的组件添加（dao）
        - @Component  ： 组件，给难以分类为MVC的组件添加（util...）

    ·某个类上添加上任何一个注解都能快速地将这个组件加入到IOC容器中

    ·注解可以随意添加，Spring底层不会验证组件的业务功能是否符合注解定义

    ·步骤：

        1. 给组件添加以上4个注解中的任何一个
        2. 配置开启Spring开启自动扫描（依赖context空间）

            context:component-scan - 自动组件扫描
            base-package - 自动扫描基础包下面所有添加了注解的类

            ※注意 com, org .. 可能会把lib路径中的包也包含进去

        3. 注解功能依赖于spring-aop包

    ·使用注解加入到容器中的组件默认行为是相同的

        1. id默认是类名首字母小写 - @Controller("bookController")指定
        2. 作用域默认是单例

### 3.16 使用context:include-filter指定扫描包时要包含的类

    在context:component-scan标签体内
        <context:include-filter type="" expression="" >

    默认就是全部都要所以必须禁用默认规则才会生效

    <context:component-scan base-package="pt.joja" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

### 3.17 使用context:exclude-filter指定扫描包时不包含的类

    在context:component-scan标签体内
        <context:exclude-filter type="" expression="" >

    type:

        - annotation 排除指定注解 ☆

            <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>

        - assignable 排除指定类 ☆

            <context:exclude-filter type="assignable" expression="pt.joja.dao.BookDao"/>

        - aspectj aspectj表达式
        - custom 自定义TypeFilter
        - regex 正则表达式

### 3.18 使用@Autowired注解实现根据类型自动装配 ☆

### 3.19 如果资源类型的bean不止一个，默认根据@Autowired注解标记的成员变量名作为id查找bean ☆

### 3.20 如果根据成员变量名作为id还是找不到bean，可以使用@Qualifier注解明确指定目标bean的id ☆

    (体现DI - 依赖注入的威力的时候了)

    @Autowired - Spring会自动为这个属性赋值（从容器中找到对应的组件）

        @Autowired
        private BookService bookService;

    ·工作步骤

        1. 先按照类型从容器中去找对应组件

            bookService = ioc.getBean(BookService.class)

            -> 找到0个，NoSuchBeanDefinitionException
            -> 找到1个，赋值结束
            -> 找到多个，步骤2

        2. 按照变量名作为id继续匹配

            bookService = ioc.getBean("bookService")

            -> 找到0个，回到步骤1，NoUniqueBeanDefinitionException
            -> 找到1个，赋值结束

    ·可以使用@Qualifier("beanId")来指定寻找的beanId
    　-> 此指定的优先级高于上述2个默认规则，而且要求beanId确实为指定值

### 3.21 在方法的形参位置使用@Qualifier注解

    @Autowired注解在方法上时

        - 在bean创建时方法会自动运行
        - 方法上的每一个参数都会自动注入

### 3.22 @Autowired注解的required属性指定某个属性不允许被设置

    @Autowired(required = false) -> 找不到装配null
    @Autowired(required = true)

    ※几种自动装配的注解

    @org.springframework.beans.factory.annotation.Autowired
    @javax.annotation.Resource
    @Inject => EJB标准下的注解

| @Autowired | @Resource         |
|------------|-------------------|
| 功能强大    | 功能相对单一        |
| Spring原生 | java标准，扩展性强 |

#### 使用Spring的单元测试：

    1. 导包：Spring的单元测试包 spring-test

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>4.0.0.RELEASE</version>
        </dependency>

    2. 编写Spring测试类

        ·为测试类class添加@Configuration注解，指定配置文件路径
            @ContextConfiguration(locations="classpath:applicationContext.xml")

                - org.springframework.test.context. ContextConfiguration

        ·为测试类class添加@RunWith注解，指定Junit的Runner类为SpringJUnit4ClassRunner
            @RunWith(SpringJUnit4ClassRunner.class)

                - org.junit.runner.RunWith
                - org.springframework.test.context.junit4.SpringJUnit4ClassRunner

### 3.23 泛型依赖注入 ☆

    BaseService<T> {                        BaseDao<T> {}
        @Autowired                              
        private BaseDao<T> baseDao;
    }

    @Service                                @Repository
    BookService extends BaseService<Book>   BookDao extends BaseDao<Book>
        --> BaseDao<Book> baseDao  -------↗

    @Service                                @Repository
    UserService extends BaseService<User>   UserDao extends BaseDao<User>
        --> BaseDao<User> baseDao  -------↗

        可以理解为找到了继承BaseDao<T>的组件

    ※jdk支持获取运行时泛型和父类

    bookService.getClass()
     - class pt.joja.service.BookService

    bookService.getClass().getSuperclass()
     - pt.joja.service.BaseService

    bookService.getClass().getGenericSuperclass()
     - pt.joja.service.BaseService<pt.joja.bean.Book>

### 3.24 IOC总结

    IOC是一个容器，帮我们管理所有的组件

        1. 依赖注入：@Autowired自动装配
        2. 某个组件要使用Spring提供的功能，它必须加入到容器中

## 第四章 AOP

### 4.1 什么是AOP？

    AOP(Aspect Oriented Programming) - 面向切面编程

    指在程序运行期间，将某段代码动态地切入到指定方法的指定位置进行运行的编程方式

    ·场景：
        计算器运行计算方法时进行日志记录

        Calculator 

        - CalculatorImpl

            ·add(int i, int j)
            ·sub(int i, int j)

        实装完成后想要为计算器添加日志记录功能

        > 传统方案1：
            在每一个实装方法里添加日志输出功能

            缺点：
                ·维护工作量很大
                ·辅助功能和业务逻辑混在，耦合度高，增加代码复杂度

        > 传统方案2：
            编写LogUtil类，在每一个实装方法里通过LogUtil类来进行日志输出

            一定程度上减少了维护工作量

            缺点：
                ·LogUtil类的方法设计困难
                ·辅助功能和业务逻辑混在，耦合度高，增加代码复杂度

        > AOP实现方案之 JDK动态代理
            编写动态代理生成类

                - CalculatorProxy

                    ·Calculator getProxy(Calculator calculator)
                    ·编写java.lang.reflect.InvocationHandler的实现类
                    ·利用java.lang.reflect.Proxy.newProxyInstance()方法生成动态代理

                ※也可以把LogUtil的呼出编写在动态代理当中

            好处：
                ·日志记录功能更加强大
                ·与业务逻辑解耦

            缺点：
                ·编写困难
                ·如果目标对象没有实现接口，则JDK默认的Proxy无法为它生成代理
                 （代理对象和被代理对象唯一的关联就是它们实现了相同的接口）

        > AOP实现方案之 Spring动态代理

            避免了JDK动态代理的缺点：
                ·编写简单，简化了AOP编程的过程
                ·不要求目标对象实现接口

### 4.2 AOP专业术语

    Calculator

       add        sub        mul        div                        LogUtil - 切面类
        |          |          |          |
     [start]     start      start      start    -  横切关注点1  ->  通知方法1
        |          |          |          |
      return     return    [return]    return   -  横切关注点2  ->  通知方法2
        |          |          |          |
    exception   exception  exception  exception -  横切关注点3  ->  通知方法3
        |          |          |          |
     finally    finally    finally   [finally]  -  横切关注点4  ->  通知方法4
        ↑                                ↑
      连接点                           切入点

    切入点：

        - 在众多连接点中选出的真正感兴趣的点

    切入点表达式：

        - 选取切入的的表达式

### 4.3 Spring AOP

    课题：
        如何将切面类（LogUtil）的日志方法在目标程序的各方法运行阶段进行调用？

    步骤：

        1. 导包

            Core Container：核心容器（IOC）

                - Beans spring-beans-4.0.0.RELEASE
                - Core spring-core-4.0.0.RELEASE
                - Context spring-core-4.0.0.RELEASE
                - SpEL spring-expression-4.0.0.RELEASE

            AOP + Aspected：面向切面编程模块

                - spring-aop-4.0.0.RELEASE
                - spring-aspects-4.0.0.RELEASE

            加强版AOP包(即使目标对象没有实现接口也能创建动态代理)

                - com.springsource.net.sf.cglib-2.2.0.jar
                - com.springsource.org.aopalliance-1.0.0.jar
                - com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar

        2. 写配置
            - 将目标类和切面类加入IOC容器
            - 指定切面类@Aspect
            - 指定切面类方法的运行时机

            try {
                @Before - 前置通知
                method.invoke();
                @AfterReturning - 返回通知
            } catch (Exception e) {
                @AfterThrowing - 异常通知
            } finally {
                @After - 后置通知
            }

            @Round - 环绕通知

            切入点表达式：
                @Before("execution(public int pt.joja.lab.impl.CalculatorSimpleImpl.*(int, int))")

        3. 开启基于注解的AOP模式

            <aop:aspectj-autoproxy proxy-target-class="false"/>
            proxy-target-class
            　true - CGLIB基于类的代理对象
              false - sun的原生Proxy代理对象
              不显式指定时则根据有没有Interface自动判断

            Spring发现一个对象被AOP切面关注之后，就会用代理对象取代原生对象存入容器中

### 4.4 Spring AOP细节

    1. 细节一

        有接口时转换为接口类，没有接口时转换为本类

    2. 细节二

        切入点表达式的写法：

            - 固定格式：

                execution(访问权限符 返回值类型 方法全类名(参数表))

            - 通配符：

                1. *匹配符

                    > 匹配一个或者多个字符
                        MyMath*.method()
                        MyMath*r.method()
                    > 匹配任意一个参数类型（不改变参数个数）
                        MyMath.method(int, *)
                    > 匹配任意一层路径(以*开头的路径则为任意路径)
                        pt.joja.*.MyMath.method(int, int)

                    > 不能在权限位置使用（省略即为public）

                2. ..匹配符

                    > 匹配任意多个参数、任意参数类型
                        MyMath.method(..)
                    > 匹配任意多层路径
                        pt.joja..MyMath.method(int, int)

                3. &&, ||, ! 逻辑交集
                    - execution(...)&&execution(...)
                    - execution(...)||execution(...)
                    - !execution(...)

        最精确的写法：
            execution(public int pt.joja.lab.impl.CaluculatorSimpleImpl.add(int,int))
        最模糊的写法：
            execution(* *.*(..))    !不要写

    3. 细节三

        通知方法的执行顺序

            - 正常返回时：

                @Before
                @After
                @AfterReturning

            - 异常返回时：

                @Before
                @After
                @AfterThrowing

    4. 细节四

        在通知方法运行时获取目标方法的详细信息

            - 为通知方法的参数列表写一个参数JoinPoint joinPoint即可

                @Before("execution(* pt.joja.lab.impl.*.*(..))")
                public static void logStart(JoinPoint joinPoint)

            - 添加参数用于接收返回值，并在@AfterReturning中指明该属性

                @AfterReturning(value="execution(* pt.joja.lab.impl.*.*(..))",returning="result")
                public static void logReturn(JoinPoint joinPoint, Object result)

            - 添加参数用于接收异常，并在@AfterThrowing中指明该属性

                @AfterThrowing(value="execution(* pt.joja.lab.impl.*.*(..))", throwing="exception")
                public static void logException(JoinPoint joinPoint, Exception exception)

        ※如果参数的Exception·Result类型明确到了具体类型，
          则无法Cast到该类型的异常·返回值出现时，
                @AfterReturning
                @AfterThrowing
          通知方法不会被呼出

        ※Spring对通知方法的要求不严格，不局限于static, void等，但是参数列表要严格书写
            JoinPoint以外的参数都要声明用途

    5. 抽取可重用切入点表达式
        > 随便声明一个没有实现的返回void的空方法，添加@Pointcut标签

            @Pointcut("execution(* pt.joja.lab.impl.*.*(..))")
            public void myPoint() {}

        > 在想要重用的表达上指明该方法

            @Before("myPoint()")
            public void logStart(JoinPoint joinPoint)

### 4.5 环绕通知

    环绕通知是Spring中最强大的通知，基本相当于手写动态代理

    try {
        @Before
        method.invoke()
        @AfterReturning
    } catch (Exception e) {
        @AfterThrowing
    } finally {
        @After
    }

    四合一就是环绕通知@Around

    @Around("myPoint()")
    public Object logAround(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        try {
            System.out.println("around before..");
            Object result = pjp.proceed(args);
            System.out.println("around afterReturn..");
            return result;
        } catch (Throwable throwable) {
            System.out.println("around afterThrowing..");
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("around after..");
        }
    }

    和动态代理如出一辙
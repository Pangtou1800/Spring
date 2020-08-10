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

### 3.14 [SpEL测试I]

    ·在SpEL中使用字面量
    ·引用其他bean
    ·引用其他bean的某个属性值
    ·调用非静态方法
    ·调用静态方法
    ·使用运算符

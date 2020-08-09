# Spring4

## 前言 什么事框架

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
    
    核心关注：IOC和AOP

    优良特性：

        - 非侵入式
        - 依赖注入
        - 面向切面编程
        - 容器
        - 组件化
        - 一站式

    各个模块：
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

    开发Spring框架的应用，经常要写框架的配置文件，写起来复杂，需要提示
    推荐安装插件

        1. 不装插件可以使用Spring官方提供的sts开发工具

        2. 

    Spring ---> IOC容器 --> 整合其他框架
           ---> AOP（面向切面编程） --> 声明式事务

## 第二章 IOC

    2.1 什么是IOC？

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

    2.2 相关概念：DI

        依赖注入 - Dependency Injection

            容器通过反射的形式，将准备好的BookService对象赋值到BookServlet中

            只要容器管理的组件，都能使用容器提供的强大功能

    2.3 HelloWorld（通过各种方式给容器中注册对象）

        自己new对象 -> 交给容器来创建

        ·导包
            导入核心容器4个jar包
        ·写配置
            spring的配置文件中，集合了spring IOC容器管理的所有组件
        ·编写测试类HelloWorld

    2.4 几个细节

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

    3.1 通过IOC容器创建对象，并为属性赋值

        HelloWorld
    
    3.2 根据bean的类型从IOC容器中获取bean实例

        若果指定类型bean实例有一个以上，会返回NoUniqueBeanDefinitionException

    3.3 通过构造器为bean的属性赋值（index, type属性介绍）
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

    3.4 为各种属性赋值
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

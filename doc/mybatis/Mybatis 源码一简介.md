Mybatis源码解析一简介
===================

#### Mybatis简介
MyBatis是一个可以自定义SQL、存储过程和高级映射的持久层框架。MyBatis摒除了大部门的JDBC代码，手工设置参数和结果集重获。MyBatis只使用简单的XML和注解来配置和映射基本数据类型、Map接口和POJO到数据记录，相对Hibernate是一种半自动化的ORM实现。
**ORM工具的基本思想：**
1. 从配置文件（通常是XML配置文件中）得到SessionFactory。
1. 由SessionFactory产生Session。
1. 在Session中完成对数据的增删改查和事务提交等。
1. 在用完之后关闭Session。
1. 在Java对象和数据库之间做mapping的配置文件，也通常是xml文件。
#### 一、MyBatis功能架构
###### 1.1 功能架构
![MyBatis三层架构](https://upload-images.jianshu.io/upload_images/8907519-6d131bac18666124.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
1. API接口层：提供给外部使用的接口API，开发人员通过这些本地API来操作数据库。接口层一接到调用请求就会调用数据处理层来完成具体的数据处理。
1. 数据处理层：负责具体的SQL查找、SQL解析、SQL执行和执行结果映射处理等。它主要的目的是根据调用的请求完成一次数据库操作。
1. 基础支撑层：负责最基础的功能支撑，包括连接管理、事务管理、配置加载和缓存管理，这些都是共用的东西，将它们抽取出来作为最基础的插件。为上层的数据处理层提供最基础的支撑。

下面是MyBatis的源码包结构图：
![源码包结构图](https://upload-images.jianshu.io/upload_images/8907519-fcc2f4210a807e2d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
###### 1.2 快速入门
![MyBatis整体流程图](https://upload-images.jianshu.io/upload_images/8907519-16d952779bb162f2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
**1. SqlSessionFactoryBuilder**  
每一个MyBatis的应用程序的入口是SqlSessionFactoryBuilder，它的作用是通过XML配置文件创建Configuration对象，也可以在程序中自行创建，然后通过build方法创建SqlSessionFactory对象。没有必要每次访问MyBatis就创建一次SqlSessionFactoryBuilder，通常的做法就是创建一个全局的对象就可以了。
```
 private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
   private static SqlSessionFactory sqlSessionFactory;
   private static void init() throws IOException {
       String resource = "mybatis-config.xml";
       Reader reader = Resources.getResourceAsReader(resource);
       sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
       sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
   }
```
**2. SqlSessionFactory**  
SqlSessionFactory对象由SqlSessionFactoryBuilder创建。他的主要功能是创建SqlSession对象，和SqlSessionFactoryBuilder对象一样，没有必要每次访问MyBatis就创建一次SqlSessionFactory，通常的做法是创建一个全局的对象就可以了。SqlSessionFactory对象一个必要的属性是Configuration对象，它是保存Mybatis全局配置的一个配置对象，通常由SqlSessionFactoryBuilder从XML配置文件创建。
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC 
	"-//mybatis.org//DTD Config 3.0//EN"
	"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!-- 配置别名 -->
	<typeAliases>
		<typeAlias type="org.iMybatis.abc.dao.UserDao" alias="UserDao" />
		<typeAlias type="org.iMybatis.abc.dto.UserDto" alias="UserDto" />
	</typeAliases>
	<!-- 配置环境变量 -->
	<environments default="development">
		<environment id="development">
			<transactionManager type="JDBC" />
			<dataSource type="POOLED">
				<property name="driver" value="com.mysql.jdbc.Driver" />
				<property name="url" value="jdbc:mysql://127.0.0.1:3306/iMybatis?characterEncoding=GBK" />
				<property name="username" value="iMybatis" />
				<property name="password" value="iMybatis" />
			</dataSource>
		</environment>
	</environments>
	<!-- 配置mappers -->
	<mappers>
		<mapper resource="org/iMybatis/abc/dao/UserDao.xml" />
	</mappers>
</configuration>
```
**3. SqlSession**  
SqlSession对象的主要功能是完成一次数据库的访问和结果的映射，它类似于数据库的session概念，由于不是线程安全的，所以SqlSession对象的作用域需要限制方法内。SqlSession的默认实现类是DefaultSqlSession，它有两个必须配置的属性：Configuration和Excutor。Configuration前文说到了。SqlSession读数据库的操作都是通过Executor来完成的。
SqlSession有一个重要的方法getMapper(),这个方法是用来获取Mapper对象的。Mapper对象在官方手册里面，应用程序除了要初始并启动MyBatis外，还需要定义一些接口；接口里定义访问数据库的方法，存放接口的包路径下需要放置同名的XML配置文件。SqlSession的getMapper()方法是联系应用程序和MyBatis的纽带，应用程序访问getMapper()时，MyBatis会根据传入的接口类型和对应的XML配置文件生成一个代理对象，这个代理对象就叫Mapper对象。应用程序获得Mapper对象后，就应该通过这个Mapper对象来访问MyBatis的SqlSession对象。
```
SqlSession session= sqlSessionFactory.openSession();  
UserDao userDao = session.getMapper(UserDao.class);  
UserDto user = new UserDto();  
user.setUsername("iMybatis");  
List<UserDto> users = userDao.queryUsers(user);  
```
```
public interface UserDao {
   public List<UserDto> queryUsers(UserDto user) throws Exception;
}
```
```
<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
<mapper namespace="org.iMybatis.abc.dao.UserDao">  
    <select id="queryUsers" parameterType="UserDto" resultType="UserDto"  
        useCache="false">  
        <![CDATA[ 
        select * from t_user t where t.username = #{username} 
        ]]>  
    </select>  
</mapper>  
```
**4. Executor**  
Executor对象在创建Configuration对象的时候创建，并且缓存在Configuration对象里。Executor对象的主要功能是调用StatementHandler访问数据库，并且查询结果存入缓存中。  
**5. StatementHandler**  
StatementHandler 是真正访问数据库的地方，并调用ResultSetHandler处理查询结果。  
**6. ResultSetHandler**  
处理查询结果
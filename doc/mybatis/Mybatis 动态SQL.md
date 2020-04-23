Mybatis 动态SQL
==============
#### 一、概述
传统的使用JDBC的方法，相信大家在组合复杂的的SQL语句的时候，需要去拼接，稍不注意哪怕少了个空格，都会导致错误。Mybatis的动态SQL功能正是为了解决这种问题， 其通过 if, choose, when, otherwise, trim, where, set, foreach标签，可组合成非常灵活的SQL语句，从而提高开发人员的效率。
#### 二、动态SQL标签
**1 if**
```
<select id="findUserById" resultType="user">
    select * from user where 
        <if test="id != null">
               id=#{id}
        </if>
    and deleteFlag=0;
</select>
```
上面例子： 如果传入的id 不为空， 那么才会SQL才拼接id = #{id}。 这个相信大家看一样就能明白，不多说。细心的人会发现一个问题：“你这不对啊！ 要是你传入的id为null, 那么你这最终的SQL语句不就成了 select * from user where and deleteFlag=0, 这语句有问题！”

是啊，这时候，mybatis的 where 标签就该隆重登场啦。  
**2 where**
```
<select id="findUserById" resultType="user">
    select * from user 
        <where>
            <if test="id != null">
                id=#{id}
            </if>
            and deleteFlag=0;
        </where>
</select>
```
有些人就要问了： “你这都是些什么玩意儿！ 跟上面的相比， 不就是多了个where标签嘛！ 那这个还会不会出现 select * from user where and deleteFlag=0 ？”  
的确，从表面上来看，就是多了个where标签而已， 不过实质上， mybatis是对它做了处理，当它遇到AND或者OR这些，它知道怎么处理。其实我们可以通过 trim 标签去自定义这种处理规则。  
**3 trim**
```
<trim prefix="WHERE" prefixOverrides="AND |OR ">
    ... 
</trim>
```
它的意思就是：当WHERE后紧随AND或则OR的时候，就去除AND或者OR。 除了WHERE以外， 其实还有一个比较经典的实现，那就是SET。  
**4 set**
```
<update id="updateUser" parameterType="com.dy.entity.User">
    update user set 
        <if test="name != null">
            name = #{name},
        </if> 
        <if test="password != null">
            password = #{password},
        </if> 
        <if test="age != null">
            age = #{age}
        </if> 
        <where>
            <if test="id != null">
                id = #{id}
            </if>
            and deleteFlag = 0;
        </where>
</update>
```
问题又来了： “如果我只有name不为null, 那么这SQL不就成了 update set name = #{name}, where ........ ? 你那name后面那逗号会导致出错啊！”  
是的，这时候，就可以用mybatis为我们提供的set 标签了。下面是通过set标签改造后：
```
<update id="updateUser" parameterType="com.dy.entity.User">
    update user
        <set>
            <if test="name != null">name = #{name},</if> 
            <if test="password != null">password = #{password},</if> 
            <if test="age != null">age = #{age},</if> 
        </set>
        <where>
            <if test="id != null">
                id = #{id}
            </if>
            and deleteFlag = 0;
        </where>
</update>
这个用trim 可表示为：

<trim prefix="SET" suffixOverrides=",">
  ...
</trim>
```
WHERE是使用的 prefixOverrides（前缀）， SET是使用的 suffixOverrides （后缀）。  
**5 foreach**  
MyBatis中有foreach, 可通过它实现循环，循环的对象当然主要是java容器和数组。
```
<select id="selectPostIn" resultType="domain.blog.Post">
    SELECT *
    FROM POST P
    WHERE ID in
    <foreach item="item" index="index" collection="list"
        open="(" separator="," close=")">
        #{item}
    </foreach>
</select>
```
将一个 List 实例或者数组作为参数对象传给 MyBatis：当这么做的时候，MyBatis 会自动将它包装在一个 Map 中并以名称为键。List 实例将会以“list”作为键，而数组实例的键将是“array”。同样，当循环的对象为map的时候，index其实就是map的key。  
**6 choose**
```
<select id="findActiveBlogLike"
     resultType="Blog">
    SELECT * FROM BLOG WHERE state = ‘ACTIVE’
    <choose>
        <when test="title != null">
            AND title like #{title}
        </when>
        <when test="author != null and author.name != null">
            AND author_name like #{author.name}
        </when>
        <otherwise>
            AND featured = 1
        </otherwise>
    </choose>
</select>
```
以上例子中： 当title和author都不为null的时候， 那么选择二选一（前者优先）， 如果都为null, 那么就选择 otherwise中的， 如果tilte和author只有一个不为null, 那么就选择不为null的那个。
#### 三 关于动态SQL的接口和类
**1.SqlNode**  
简单理解就是xml中的每个标签，比如上述sql的update,trim,if标签：
```
public interface SqlNode {
    boolean apply(DynamicContext context);
}
```
![SqlNode实现类](https://upload-images.jianshu.io/upload_images/8907519-5e440a55b8ccd0ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
**2. SqlSource**  
SqlSource是Sql源接口，代表从xml文件或注解映射的sql内容，主要就是用于创建BoundSql，有实现类DynamicSqlSource(动态Sql源)，StaticSqlSource(静态Sql源)等：
```
public interface SqlSource {
    BoundSql getBoundSql(Object parameterObject);
}
```
![SqlSource实现类](https://upload-images.jianshu.io/upload_images/8907519-06032834c809e560.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
**3.BoundSql**  
封装mybatis最终产生sql的类，包括sql语句，参数，参数源数据等参数：
![](https://upload-images.jianshu.io/upload_images/8907519-e7cbce53e17691d3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
**4.XNode**  
一个Dom API中的Node接口的扩展类：
![XNode](https://upload-images.jianshu.io/upload_images/8907519-7127376e26a6eef5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
**5.BaseBuilder**  
![BaseBuilder接口及其实现类](https://upload-images.jianshu.io/upload_images/8907519-c5e20a9106dd8178.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
XMLConfigBuilder：解析mybatis中configLocation属性中的全局xml文件，内部会使用XMLMapperBuilder解析各个xml文件。  
XMLMapperBuilder：遍历mybatis中mapperLocations属性中的xml文件中每个节点的Builder，比如user.xml，内部会使用XMLStatementBuilder处理xml中的每个节点。  
XMLStatementBuilder：解析xml文件中各个节点，比如select,insert,update,delete节点，内部会使用XMLScriptBuilder处理节点的sql部分，遍历产生的数据会丢到Configuration的mappedStatements中。  
XMLScriptBuilder：解析xml中各个节点sql部分的Builder。  
**6.LanguageDriver**  
该接口主要的作用就是构造sql:
![image.png](https://upload-images.jianshu.io/upload_images/8907519-039e61b60406b7d2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
简单分析下XMLLanguageDriver(处理xml中的sql，RawLanguageDriver处理静态sql)：XMLLanguageDriver内部会使用XMLScriptBuilder解析xml中的sql部分。
#### 四 源码分析
Spring与Mybatis整合的时候需要配置SqlSessionFactoryBean，该配置会加入数据源和mybatis xml配置文件路径等信息：
```
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:mybatisConfig.xml"/>
    <property name="mapperLocations" value="classpath*:org/format/dao/*.xml"/>
</bean>
```
SqlSessionFactoryBean实现了Spring的InitializingBean接口，InitializingBean接口的afterPropertiesSet方法中会调用buildSqlSessionFactory方法 该方法内部会使用XMLConfigBuilder解析属性configLocation中配置的路径，还会使用XMLMapperBuilder属性解析mapperLocations属性中的各个xml文件。部分源码如下：
![image.png](https://upload-images.jianshu.io/upload_images/8907519-cbfc0d08d8f50265.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
由于XMLConfigBuilder内部也是使用XMLMapperBuilder，我们就看看XMLMapperBuilder的解析细节：
![输入图片说明](https://upload-images.jianshu.io/upload_images/8907519-e28e530d19c6a637.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "在这里输入图片标题")
![输入图片说明](https://upload-images.jianshu.io/upload_images/8907519-e4fb343485730816.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "在这里输入图片标题")
增删改查节点的解析：
![输入图片说明](https://upload-images.jianshu.io/upload_images/8907519-d358e4b708dff4c1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "在这里输入图片标题")
XMLStatementBuilder的解析：
![输入图片说明](https://upload-images.jianshu.io/upload_images/8907519-7d4303d0b2b94084.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "在这里输入图片标题")
`默认会使用XMLLanguageDriver创建SqlSource（Configuration构造函数中设置）`。
XMLLanguageDriver创建SqlSource：
![输入图片说明](https://upload-images.jianshu.io/upload_images/8907519-8bf14e2c2240d320.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "在这里输入图片标题")
XMLScriptBuilder解析sql：
![输入图片说明](https://upload-images.jianshu.io/upload_images/8907519-9e523ca62a1877fc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "在这里输入图片标题")
得到SqlSource之后，会放到Configuration中，有了SqlSource，就能拿BoundSql了，BoundSql可以得到最终的sql。
#### 五 实例分析
以下面的xml解析大概说下parseDynamicTags的解析过程
```
<update id="update" parameterType="org.format.dynamicproxy.mybatis.bean.User">
    UPDATE users
    <trim prefix="SET" prefixOverrides=",">
        <if test="name != null and name != ''">
            name = #{name}
        </if>
        <if test="age != null and age != ''">
            , age = #{age}
        </if>
        <if test="birthday != null and birthday != ''">
            , birthday = #{birthday}
        </if>
    </trim>
    where id = ${id}
</update>
```
parseDynamicTags方法的返回值是一个List，也就是一个Sql节点集合。SqlNode本文一开始已经介绍，分析完解析过程之后会说一下各个SqlNode类型的作用。  
首先根据update节点(Node)得到所有的子节点，分别是3个子节点：  
(1) 文本节点 \n UPDATE users  
(2) trim子节点 ...  
(3) 文本节点 \n where id = #{id}  
遍历各个子节点：  
(1) 如果节点类型是文本或者CDATA，构造一个TextSqlNode或StaticTextSqlNode；  
(2) 如果节点类型是元素，说明该update节点是个动态sql，然后会使用NodeHandler处理各个类型的子节点。这里的NodeHandler是XMLScriptBuilder的一个内部接口，其实现类包括TrimHandler、WhereHandler、SetHandler、IfHandler、ChooseHandler等。看类名也就明白了这个Handler的作用，比如我们分析的trim节点，对应的是TrimHandler；if节点，对应的是IfHandler...这里子节点trim被TrimHandler处理，TrimHandler内部也使用parseDynamicTags方法解析节点。  
遇到子节点是元素的话，重复以上步骤：  
trim子节点内部有7个子节点，分别是文本节点、if节点、是文本节点、if节点、是文本节点、if节点、文本节点。文本节点跟之前一样处理，if节点使用IfHandler处理。遍历步骤如上所示，下面我们看下几个Handler的实现细节。  
IfHandler处理方法也是使用parseDynamicTags方法，然后加上if标签必要的属性：
```
private class IfHandler implements NodeHandler {
    public void handleNode(XNode nodeToHandle, List<SqlNode> targetContents) {
      List<SqlNode> contents = parseDynamicTags(nodeToHandle);
      MixedSqlNode mixedSqlNode = new MixedSqlNode(contents);
      String test = nodeToHandle.getStringAttribute("test");
      IfSqlNode ifSqlNode = new IfSqlNode(mixedSqlNode, test);
      targetContents.add(ifSqlNode);
    }
}
```
TrimHandler处理方法也是使用parseDynamicTags方法，然后加上trim标签必要的属性：
```
private class TrimHandler implements NodeHandler {
    public void handleNode(XNode nodeToHandle, List<SqlNode> targetContents) {
      List<SqlNode> contents = parseDynamicTags(nodeToHandle);
      MixedSqlNode mixedSqlNode = new MixedSqlNode(contents);
      String prefix = nodeToHandle.getStringAttribute("prefix");
      String prefixOverrides = nodeToHandle.getStringAttribute("prefixOverrides");
      String suffix = nodeToHandle.getStringAttribute("suffix");
      String suffixOverrides = nodeToHandle.getStringAttribute("suffixOverrides");
      TrimSqlNode trim = new TrimSqlNode(configuration, mixedSqlNode, prefix, prefixOverrides, suffix, suffixOverrides);
      targetContents.add(trim);
    }
}
```
以上update方法最终通过parseDynamicTags方法得到的SqlNode集合如下：  
![输入图片说明](https://upload-images.jianshu.io/upload_images/8907519-80d678299140cb09.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "在这里输入图片标题")
trim节点：
![输入图片说明](https://upload-images.jianshu.io/upload_images/8907519-588fee30538d48b1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "在这里输入图片标题")
由于这个update方法是个动态节点，因此构造出了DynamicSqlSource。DynamicSqlSource内部就可以构造sql了:
![输入图片说明](https://upload-images.jianshu.io/upload_images/8907519-71a42f5de2a2d361.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240 "在这里输入图片标题")
DynamicSqlSource内部的SqlNode属性是一个MixedSqlNode。然后我们看看各个SqlNode实现类的apply方法。下面分析一下各个SqlNode实现类的apply方法实现：  
MixedSqlNode：MixedSqlNode会遍历调用内部各个sqlNode的apply方法
```
public boolean apply(DynamicContext context) {
   for (SqlNode sqlNode : contents) {
     sqlNode.apply(context);
   }
   return true;
}
```
StaticTextSqlNode：直接append sql文本。
```
public boolean apply(DynamicContext context) {
   context.appendSql(text);
   return true;
}
```
IfSqlNode：这里的evaluator是一个ExpressionEvaluator类型的实例，内部使用了OGNL处理表达式逻辑。
```
public boolean apply(DynamicContext context) {
   if (evaluator.evaluateBoolean(test, context.getBindings())) {
     contents.apply(context);
     return true;
   }
   return false;
}
```
TrimSqlNode：
```
public boolean apply(DynamicContext context) {
    FilteredDynamicContext filteredDynamicContext = new FilteredDynamicContext(context);
    boolean result = contents.apply(filteredDynamicContext);
    filteredDynamicContext.applyAll();
    return result;
}

public void applyAll() {
    sqlBuffer = new StringBuilder(sqlBuffer.toString().trim());
    String trimmedUppercaseSql = sqlBuffer.toString().toUpperCase(Locale.ENGLISH);
    if (trimmedUppercaseSql.length() > 0) {
        applyPrefix(sqlBuffer, trimmedUppercaseSql);
        applySuffix(sqlBuffer, trimmedUppercaseSql);
    }
    delegate.appendSql(sqlBuffer.toString());
}

private void applyPrefix(StringBuilder sql, String trimmedUppercaseSql) {
    if (!prefixApplied) {
        prefixApplied = true;
        if (prefixesToOverride != null) {
            for (String toRemove : prefixesToOverride) {
                if (trimmedUppercaseSql.startsWith(toRemove)) {
                    sql.delete(0, toRemove.trim().length());
                    break;
                }
            }
        }
        if (prefix != null) {
            sql.insert(0, " ");
            sql.insert(0, prefix);
        }
   }
}
```
TrimSqlNode的apply方法也是调用属性contents(一般都是MixedSqlNode)的apply方法，按照实例也就是7个SqlNode，都是StaticTextSqlNode和IfSqlNode。 最后会使用FilteredDynamicContext过滤掉prefix和suffix。
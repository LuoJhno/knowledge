Java学习路线
====
### 知识图谱

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Markmap</title>
<style>
* {
  margin: 0;
  padding: 0;
}
#mindmap {
  display: block;
  width: 100vw;
  height: 100vh;
}
</style>

</head>
<body>
<svg id="mindmap"></svg>
<script src="https://cdn.jsdelivr.net/npm/d3@5"></script><script src="https://cdn.jsdelivr.net/npm/markmap-lib@0.9.1/dist/browser/view.min.js"></script><script>((a,e)=>{const{Markmap:t}=a();window.mm=t.create("svg#mindmap",null,e)})(()=>window.markmap,{"t":"heading","d":1,"p":{},"v":"Java学习路线","c":[{"t":"heading","d":2,"p":{},"v":"基础知识","c":[{"t":"heading","d":3,"p":{},"v":"Java技术","c":[{"t":"list_item","d":5,"p":{"index":1},"v":"1. Java基础"},{"t":"list_item","d":5,"p":{"index":2},"v":"2. Java容器-List，Map，Array，Queue，Stack"},{"t":"list_item","d":5,"p":{"index":3},"v":"3. Java IO，NIO"},{"t":"list_item","d":5,"p":{"index":4},"v":"4. Java并发编程-sychronized，lock，线程池，阻塞队列，AQS，volatile"},{"t":"list_item","d":5,"p":{"index":5},"v":"5. Java反射"},{"t":"list_item","d":5,"p":{"index":6},"v":"6. EffectiveJava经验"},{"t":"list_item","d":5,"p":{"index":7},"v":"7. JVM(JVM内存划分，JVM内存模型，类加载机制，垃圾回收算法，垃圾回收器，性能调优)"}]},{"t":"heading","d":3,"p":{},"v":"数据库","c":[{"t":"list_item","d":5,"p":{},"v":"MySQL","c":[{"t":"list_item","d":7,"p":{"index":1},"v":"1. DDL，DML语句"},{"t":"list_item","d":7,"p":{"index":2},"v":"2. 事务和锁"},{"t":"list_item","d":7,"p":{"index":3},"v":"3. 数据库设计（设计原则-范式原则）"},{"t":"list_item","d":7,"p":{"index":4},"v":"4. 数据库索引"},{"t":"list_item","d":7,"p":{"index":5},"v":"5. 数据库存储引擎（InnoDB，MyISAM等）"},{"t":"list_item","d":7,"p":{"index":6},"v":"6. 数据库分区"},{"t":"list_item","d":7,"p":{"index":7},"v":"7. 性能优化"},{"t":"list_item","d":7,"p":{"index":8},"v":"8. 数据库主从及读写分离"},{"t":"list_item","d":7,"p":{"index":9},"v":"9. Explain执行计划"}]},{"t":"list_item","d":5,"p":{},"v":"NoSQL","c":[{"t":"list_item","d":7,"p":{"index":1},"v":"1. Redis（单机，集群）"},{"t":"list_item","d":7,"p":{"index":2},"v":"2. MongoDB\n......"}]}]},{"t":"heading","d":3,"p":{},"v":"算法和数据结构","c":[{"t":"list_item","d":5,"p":{},"v":"数据结构","c":[{"t":"list_item","d":7,"p":{"index":1},"v":"1. 数组"},{"t":"list_item","d":7,"p":{"index":2},"v":"2. 链表"},{"t":"list_item","d":7,"p":{"index":3},"v":"3. 栈"},{"t":"list_item","d":7,"p":{"index":4},"v":"4. 树-普通树，二叉树，平衡树，红黑树"},{"t":"list_item","d":7,"p":{"index":5},"v":"5. 堆"},{"t":"list_item","d":7,"p":{"index":6},"v":"6. 图"},{"t":"list_item","d":7,"p":{"index":7},"v":"7. 跳跃表"},{"t":"list_item","d":7,"p":{"index":8},"v":"8. B-Tree，B+Tree"}]},{"t":"list_item","d":5,"p":{},"v":"算法","c":[{"t":"list_item","d":7,"p":{"index":1},"v":"1. 数组，链表，栈，树的操作及常见思路"},{"t":"list_item","d":7,"p":{"index":2},"v":"2. B-Tree，B+Tree的操作"},{"t":"list_item","d":7,"p":{"index":3},"v":"3. 跳跃表的原理"},{"t":"list_item","d":7,"p":{"index":4},"v":"4. 二分法"},{"t":"list_item","d":7,"p":{"index":5},"v":"5. Hash算法"},{"t":"list_item","d":7,"p":{"index":6},"v":"6. 常见排序算法"},{"t":"list_item","d":7,"p":{"index":7},"v":"7. 递归"},{"t":"list_item","d":7,"p":{"index":8},"v":"8. 分冶算法"},{"t":"list_item","d":7,"p":{"index":9},"v":"9. 回溯算法"},{"t":"list_item","d":7,"p":{"index":10},"v":"10. 贪心算法"},{"t":"list_item","d":7,"p":{"index":11},"v":"11. 动态规划"}]}]},{"t":"heading","d":3,"p":{},"v":"计算机网络及计算机组成","c":[{"t":"list_item","d":5,"p":{"index":1},"v":"1. 计算机网络基础"},{"t":"list_item","d":5,"p":{"index":2},"v":"2. HTTP/HTTPS"},{"t":"list_item","d":5,"p":{"index":3},"v":"3. IO模型"},{"t":"list_item","d":5,"p":{"index":4},"v":"4. 计算机操作系统"}]},{"t":"heading","d":3,"p":{},"v":"设计模式","c":[{"t":"list_item","d":5,"p":{"index":1},"v":"1. 策略模式"},{"t":"list_item","d":5,"p":{"index":2},"v":"2. 单例模式"},{"t":"list_item","d":5,"p":{"index":3},"v":"3. 观察者模式"},{"t":"list_item","d":5,"p":{"index":4},"v":"4. 适配器模式"},{"t":"list_item","d":5,"p":{"index":5},"v":"5. 装饰者模式"},{"t":"list_item","d":5,"p":{"index":6},"v":"6. 工厂模式"},{"t":"list_item","d":5,"p":{"index":7},"v":"7. 代理模式（静态代理，JDK代理，CGLib代理）\n......"}]}]},{"t":"heading","d":2,"p":{},"v":"框架及技术","c":[{"t":"list_item","d":4,"p":{"index":1},"v":"1. SpringBoot"},{"t":"list_item","d":4,"p":{"index":2},"v":"2. SpringCloud"},{"t":"list_item","d":4,"p":{"index":3},"v":"3. Mybatis"},{"t":"list_item","d":4,"p":{"index":4},"v":"4. Guava"},{"t":"list_item","d":4,"p":{"index":5},"v":"5. Nginx"},{"t":"list_item","d":4,"p":{"index":6},"v":"6. Netty"},{"t":"list_item","d":4,"p":{"index":7},"v":"7. Dubbo"},{"t":"list_item","d":4,"p":{"index":8},"v":"8. Electicsearch"},{"t":"list_item","d":4,"p":{"index":9},"v":"9. Cannel"},{"t":"list_item","d":4,"p":{"index":10},"v":"10. Seata"},{"t":"list_item","d":4,"p":{"index":11},"v":"11. RabbitMQ（正常队列，死信队列），RocketMQ"},{"t":"list_item","d":4,"p":{"index":12},"v":"12. Kafka"}]},{"t":"heading","d":2,"p":{},"v":"Linux操作","c":[{"t":"list_item","d":4,"p":{"index":1},"v":"1. 常见命令"},{"t":"list_item","d":4,"p":{"index":2},"v":"2. 数据检索"},{"t":"list_item","d":4,"p":{"index":3},"v":"3. 常见配置"},{"t":"list_item","d":4,"p":{"index":4},"v":"4. vim"}]},{"t":"heading","d":2,"p":{},"v":"CI/CD","c":[{"t":"list_item","d":4,"p":{"index":1},"v":"1. Docker/DockerCompose"},{"t":"list_item","d":4,"p":{"index":2},"v":"2. Jekins或其他付费云"},{"t":"list_item","d":4,"p":{"index":3},"v":"3. Grovy脚本及Lua脚本"},{"t":"list_item","d":4,"p":{"index":4},"v":"4. 开发中的规范（PR代码行，分支开启原则）"},{"t":"list_item","d":4,"p":{"index":5},"v":"5. Git操作"},{"t":"list_item","d":4,"p":{"index":6},"v":"6. Git集成自动化测试"}]},{"t":"heading","d":2,"p":{},"v":"项目实战","c":[{"t":"list_item","d":4,"p":{"index":1},"v":"1. 分布式系统（业务服务...，鉴权服务，支付服务，网关服务，A dmin，分布式定时任务服务）"},{"t":"list_item","d":4,"p":{"index":2},"v":"2. 对接微信支付和支付宝支付，对接其他服务"},{"t":"list_item","d":4,"p":{"index":3},"v":"3. 日志系统ELK"},{"t":"list_item","d":4,"p":{"index":4},"v":"4. 对外提供接口（安全性，稳定性，幂等性）"},{"t":"list_item","d":4,"p":{"index":5},"v":"5. 系统安全考虑（操作权限，数据权限）"},{"t":"list_item","d":4,"p":{"index":6},"v":"6. 数据安全考虑（敏感信息，脱敏）"},{"t":"list_item","d":4,"p":{"index":7},"v":"7. 秒杀服务设计及实现"},{"t":"list_item","d":4,"p":{"index":8},"v":"8. 代码设计及重构（涉及到设计模式及代码质量）"},{"t":"list_item","d":4,"p":{"index":9},"v":"9. 单元测试，集成测试"}]}]})</script>
</body>
</html>

### 基础知识
##### Java技术
1. Java基础
2. Java容器-List，Map，Array，Queue，Stack
3. Java IO，NIO
4. Java并发编程-sychronized，lock，线程池，阻塞队列，AQS，volatile
5. Java反射
6. EffectiveJava经验
7. JVM(JVM内存划分，JVM内存模型，类加载机制，垃圾回收算法，垃圾回收器，性能调优)
##### 数据库
* MySQL
   1. DDL，DML语句
   2. 事务和锁
   3. 数据库设计（设计原则-范式原则）
   4. 数据库索引
   5. 数据库存储引擎（InnoDB，MyISAM等）
   6. 数据库分区
   7. 性能优化
   8. 数据库主从及读写分离
   9. Explain执行计划
* NoSQL
   1. Redis（单机，集群）
   2. MongoDB
   ......
##### 算法和数据结构
* 数据结构
    1. 数组
    2. 链表
    3. 栈
    4. 树-普通树，二叉树，平衡树，红黑树
    5. 堆
    6. 图
    7. 跳跃表
    8. B-Tree，B+Tree
* 算法
    1. 数组，链表，栈，树的操作及常见思路
    2. B-Tree，B+Tree的操作
    3. 跳跃表的原理
    4. 二分法
    5. Hash算法
    6. 常见排序算法
    7. 递归
    8. 分冶算法
    9. 回溯算法
    10. 贪心算法
    11. 动态规划
##### 计算机网络及计算机组成
1. 计算机网络基础
2. HTTP/HTTPS
3. IO模型
4. 计算机操作系统
##### 设计模式
1. 策略模式
2. 单例模式
3. 观察者模式
4. 适配器模式
5. 装饰者模式
6. 工厂模式
7. 代理模式（静态代理，JDK代理，CGLib代理）
......
### 框架及技术
1. SpringBoot
2. SpringCloud
3. Mybatis
4. Guava
5. Nginx
6. Netty
7. Dubbo
8. Electicsearch
9. Cannel
10. Seata
11. RabbitMQ（正常队列，死信队列），RocketMQ
12. Kafka
### Linux操作
1. 常见命令
2. 数据检索
3. 常见配置
4. vim
### CI/CD
1. Docker/DockerCompose
2. Jekins或其他付费云
3. Grovy脚本及Lua脚本
4. 开发中的规范（PR代码行，分支开启原则）
5. Git操作
6. Git集成自动化测试
### 项目实战
1. 分布式系统（业务服务...，鉴权服务，支付服务，网关服务，A dmin，分布式定时任务服务）
2. 对接微信支付和支付宝支付，对接其他服务
3. 日志系统ELK
4. 对外提供接口（安全性，稳定性，幂等性）
5. 系统安全考虑（操作权限，数据权限）
6. 数据安全考虑（敏感信息，脱敏）
7. 秒杀服务设计及实现
8. 代码设计及重构（涉及到设计模式及代码质量）
9. 单元测试，集成测试




[Google Guava](https://github.com/google/guava) 
====
* [引言](#引言)
* [目录](#目录)
# 引言
Guava工程包含多个核心库，例如：集合 [collections] 、缓存 [caching] 、原生类型支持 [primitives support] 、并发库 [concurrency libraries] 、通用注解 [common annotations] 、字符串处理 [string processing] 、I/O 等等。

# 目录
## 基本工具 [Basic utilities]
让使用Java语言变得更舒适
1. [使用和避免null](./basicUtilties/使用和避免null.md)：null是模棱两可的，会引起令人困惑的错误，有些时候它让人很不舒服。很多Guava工具类用快速失败拒绝null值，而不是盲目地接受
2. [前置条件](./basicUtilties/前置条件.md): 让方法中的条件检查更简单
3. [常见Object方法](./basicUtilties/常见的Object方法.md): 简化Object方法实现，如hashCode()和toString()
4. [排序](./basicUtilties/排序.md): Guava强大的”流畅风格比较器”
5. [Throwables](./basicUtilties/Throwables.md)：简化了异常和错误的传播与检查

## 集合[Collections]
Guava对JDK集合的扩展，这是Guava最成熟和为人所知的部分
1. [不可变集合](./collections/不可变集合.md): 用不变的集合进行防御性编程和性能提升。
2. [新集合类型](./collections/新集合类型.md): multisets, multimaps, tables, bidirectional maps等
3. [强大的集合工具类](./collections/强大的集合工具类.md): 提供java.util.Collections中没有的集合工具
4. [扩展工具类](./collections/强大的集合工具类.md)：让实现和扩展集合类变得更容易，比如创建Collection的装饰器，或实现迭代器
## 缓存[Caches]
[Guava Cache](./cache/缓存.md)：本地缓存实现，支持多种缓存过期策略
## 函数式风格[Functional idioms]
Guava的函数式支持可以显著简化代码，但请谨慎使用它
## 并发[Concurrency]
强大而简单的抽象，让编写正确的并发代码更简单
1. [ListenableFuture](./concurrency/ListenableFuture.md)：完成后触发回调的Future
2. [Service框架](./cache/Service框架.md)：抽象可开启和关闭的服务，帮助你维护服务的状态逻辑
## 字符串处理[Strings]
[字符串处理](./strings/字符串处理.md)，非常有用的字符串工具，包括分割、连接、填充等操作
## 原生类型[Primitives]
扩展 JDK 未提供的原生类型（如int、char）操作， 包括某些类型的无符号形式
## 区间[Ranges]
可比较类型的区间API，包括连续和离散类型
## I/O
简化I/O尤其是I/O流和文件的操作，针对Java5和6版本
## 散列[Hash]
提供比Object.hashCode()更复杂的散列实现，并提供布鲁姆过滤器的实现
## 事件总线[EventBus]
发布-订阅模式的组件通信，但组件不需要显式地注册到其他组件中
## 数学运算[Math]
优化的、充分测试的数学工具类
## 反射[Reflection]
Guava 的 Java 反射机制工具类


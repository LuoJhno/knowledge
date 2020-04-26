RabbitMQ持久化
=====
持久化是为提高rabbitmq消息的可靠性，防止在异常情况(重启，关闭，宕机)下数据的丢失。
rabbitMQ持久化分为三个部分: 交换机的持久化、队列的持久化和消息的持久化。
##### 交换机持久化
交换机持久化是通过声明队列时，将durable参数设置为true实现。如果交换机不设置持久化，在rabbitMQ服务重启之后，相关的交换机元数据将会丢失；不过消息不会丢失，只是不能讲消息发送到这个交换机中了。
```
// 方法参数
queueDeclare(queue, durable, exclusive, autoDelete,  arguments)

// 声明交换机
channel.queueDeclare(q_name, true, false, false, map);
```
##### 队列持久化
队列的持久化是通过声明队列时，将durable参数设置为true实现的。如果队列不设置持久化，那么rabbitmq服务重启之后，相关的队列元数据将会丢失，而消息是存储在队列中的，所以队列中的消息也会被丢失。
##### 消息持久化
队列的持久化只能保证其队列本身的元数据不会被丢失，但是不能保证消息不会被丢失。所以消息本身也需要被持久化，可以在投递消息前设置AMQP.BasicProperties的属性deliveryMode为2即可。
```
AMQP.BasicProperties low = new AMQP.BasicProperties
                .Builder()
                .deliveryMode(2)
                .build();
```

> 问题
描述：将交换器、队列和消息都设置持久化之后就能保证数据不会被丢失吗？当然不是，多个方面：
消费者端: 消费者订阅队列将autoAck设置为true,虽然消费者接收到了消息，但是没有来得及处理就宕机了，那数据也会丢失，解决方案就是以为手动确认接收消息，待处理完消息之后，手动删除消息。
在rabbitmq服务端，如果消息正确被发送，但是rabbitmq未来得及持久化，没有将数据写入磁盘，服务异常而导致数据丢失，解决方案，可以通过rabbitmq集群的方式实现消息中间件的高可用性。

静态代理，动态代理，CGLIB代理
======
##### 概述
Java代理就是客户类不再直接和委托类打交道，二十通过一个中间层来访问，这个中间层就是代理，代理可以隐藏委托类的实现，可以实现客户和委托类之间的解耦，在不修改委托类代码的情况下能做一些额外的处理。
代理主要分为静态代理、JDK动态代理和CGLIB动态代理。
##### 静态代理
静态代理的关系在编译期间就已经确定了。首先需要定义接口和其实现类，然后定义代理对象，代理对象中注入接口的实例，然后通过代理对象去调用真正的实现类。它可以实现在不修改委托类的强开下做一些额外的处理；试用于代理类较少的情况。如果在委托类很多的情况下，那就需要使用动态代理了。
```
public interface IHelloService {

    String sayHello(String name);

}

public class HelloService implements IHelloService {

    @Override
    public String sayHello(String name) {
        System.out.println("hello:" + name);
        return "HelloService:" + name;

    }

}

public class StaticProxy implements IHelloService {

    @Override
    public String sayHello(String name) {
        System.out.println("hello:" + name);
        return "HelloService:" + name;

    }

}

```

##### JDK动态代理
在JVM中，类加载阶段主要有三个阶段：
* 通过一个类的全限定名来获取这个类的二进制字节流，包括从ZIP包，网络，运行时计算生成，其他文件，数据库获取。
* 将字节流所代表的的静态存储结构转换为方法区的运行时结构。
* 在内存中生成一个代表这个类的java.lang.Class对象，作为方法区访问这个类的入口。

而其中第一阶段的运行时计算生成的类就是动态代理技术，在Proxy类中，就是用了ProxyGenerator.generateProxyClass来为特定接口生成*$Proxy代理类的二进制字节流。

在JDK动态代理中主要涉及到java.lang.reflect.Proxy和java.lang.reflect.InvocationHandler。需要实现一个InvocationHandler接口的中间类，这个接口只有一个invoke方法，委托类的所有方法的调用都会变成对invoke方法的调用，这样就可以在invoke方法中添加统一的处理逻辑。中间类有一个委托类对象的引用，把外部对invoke的调用最终对转换为对委托类的调用。
```
/**
 * {@code InvocationHandler} is the interface implemented by
 * the <i>invocation handler</i> of a proxy instance.
 *
 * <p>Each proxy instance has an associated invocation handler.
 * When a method is invoked on a proxy instance, the method
 * invocation is encoded and dispatched to the {@code invoke}
 * method of its invocation handler.
 *
 * @author      Peter Jones
 * @see         Proxy
 * @since       1.3
 */
public interface InvocationHandler {

    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with.
     *
     * @param   proxy the proxy instance that the method was invoked on
     *
     * @param   method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param   args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return  the value to return from the method invocation on the
     * proxy instance.  If the declared return type of the interface
     * method is a primitive type, then the value returned by
     * this method must be an instance of the corresponding primitive
     * wrapper class; otherwise, it must be a type assignable to the
     * declared return type.  If the value returned by this method is
     * {@code null} and the interface method's return type is
     * primitive, then a {@code NullPointerException} will be
     * thrown by the method invocation on the proxy instance.  If the
     * value returned by this method is otherwise not compatible with
     * the interface method's declared return type as described above,
     * a {@code ClassCastException} will be thrown by the method
     * invocation on the proxy instance.
     *
     * @throws  Throwable the exception to throw from the method
     * invocation on the proxy instance.  The exception's type must be
     * assignable either to any of the exception types declared in the
     * {@code throws} clause of the interface method or to the
     * unchecked exception types {@code java.lang.RuntimeException}
     * or {@code java.lang.Error}.  If a checked exception is
     * thrown by this method that is not assignable to any of the
     * exception types declared in the {@code throws} clause of
     * the interface method, then an
     * {@link UndeclaredThrowableException} containing the
     * exception that was thrown by this method will be thrown by the
     * method invocation on the proxy instance.
     *
     * @see     UndeclaredThrowableException
     */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable;
}
```
实际上中间类和委托类构成了静态代理关系，然后代理类和中间类也构成了静态代理关系，也就是说动态代理是由两组静态代理关系组成。
```
public class JavaProxyInvocationHandler implements InvocationHandler {
    private Object object;

    public JavaProxyInvocationHandler(Object object) {
        this.object = object;
    }

    public Object newProxyInstance(Object object) {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke before");
        Object result = method.invoke(object, args);
        System.out.println("invoke after");
        return result;
    }
}
```
JDK代理的最大的特点就是动态生成的代理类和委托类实现同一个接口。JDK动态代理其实就是内部通过反射机制实现，就是已知一个对象，在运行时动态调用它的方法，并且调用的时候还可以加一些自己的逻辑。
##### CGLIB代理
JDK动态代理依赖接口实现，当只有类没有接口的时候，就需要用到CGLIB代理，CGLIB代理时第三方实现的。
CGLIB代理时针对类来实现代理的，原理是对指定的委托类生成一个子类并重写其中业务方法实现代理，代理类对象是由Enhancer类创建的。
CGLIB创建动态代理类的模式是：
* 查找目标类的所有非final的public方法（final方法不能被重写）。
* 将这些方法定义转成字节码。
* 将组成的字节码转换成相应的代理的Class对象然后通过反射获得代理类的实例对象。
* 实现MethodInterceptor接口，用来处理对代理类上所有方法的请求。
```
public class CglibInterceptor implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用前");
        Object o = methodProxy.invokeSuper(object, args);
        System.out.println("调用后");
        return o;
    }

    public Object newProxyInstance(Class<?> c) {
        enhancer.setSuperclass(c);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
```
对于需要代理的类，只是动态生成了一个子类以覆盖非final的方法，同时绑定回调自定义的拦截器。这种实现比JDK动态代理快。

##### 总结
1. 静态代理需要委托类和代理类来自同一个接口，然后地里种调用真正的实现类，静态代理的关系在编译期间就确定了。而动态代理是在运行期确定的。静态代理实现简单，适合代理类较少且确定的情况，动态代理具有更大的灵活性。
2. JDK动态代理的代理类，是在程序调用的时候才被JVM创建，JVM根据传进来的对象和方法名，动态创建一个代理类的class文件并执行，然后通过该代理类对象进行方法调用。
3. 静态代理和JDK动态代理都是基于接口实现的，对于没有提供接口只有实现类的类，只能用CGLIB动态代理。
4. JDK代理和CGLIB代理的区别
   * JDK动态代理基于Java的反射机制实现，必须要要实现接口的实现类才能使用这种方法生成代理对象。
   * CGLIB动态代理基于ASM通过生成业务类的子类来实现。
   * JDK动态代理的优势是最小依赖关系，减少以来以为着简化开发和维护，并且有JDK自身支持，可以平滑的进行JDK升级，代码实现简单。
   * 基于CGLIB的又是是无须实现接口，达到代理类无侵入。

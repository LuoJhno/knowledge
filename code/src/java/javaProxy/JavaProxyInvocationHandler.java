package java.javaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

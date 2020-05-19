package java.staticProxy;

public class StaticProxy implements IHelloService {

    @Override
    public String sayHello(String name) {
        System.out.println("hello:" + name);
        return "HelloService:" + name;

    }

}
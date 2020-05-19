package java.staticProxy;

public class HelloService implements IHelloService {

    @Override
    public String sayHello(String name) {
        System.out.println("hello:" + name);
        return "HelloService:" + name;

    }

}
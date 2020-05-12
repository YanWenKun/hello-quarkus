package fun.yanwk.helloquarkus.helloworld;

import javax.enterprise.context.ApplicationScoped;

// 如果注释掉该注解，能正常编译，但启动即报错，也就过不了单元测试
@ApplicationScoped
public class GreetingService {

    public String greeting(String name) {
        return "Hello, " + name + "!";
    }
}

package fun.yanwk.helloquarkus.helloworld;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 1. 在 Quarkus 中，独创建一个 Application 类并不是必须的。
 * 2. 在 Quarkus 中，一个资源（REST术语）只创建一个实例，而不是每次请求创建一个。
 * 3. 你可以用 *Scoped 注解来配置实例的创建关注点。
 */
@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Path("/greeting/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String greeting(@PathParam String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "你好，世界！"; // 默认即无乱码
    }
}

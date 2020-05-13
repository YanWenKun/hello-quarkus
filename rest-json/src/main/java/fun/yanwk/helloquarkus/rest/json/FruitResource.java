package fun.yanwk.helloquarkus.rest.json;

import javax.ws.rs.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/fruits")
@Produces("application/json") // 虽然 RESTEasy 支持自动协商，
@Consumes("application/json") // 但 Quarkus 强烈建议添加这两个注解，有助于 native 构建时分析所需 JAX-RS provider 种类，缩减程序大小。
public class FruitResource {

    private final Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    /**
     * 填充一点初始数据
     */
    public FruitResource() {
        fruits.add(new Fruit("Apple", "Winter fruit"));
        fruits.add(new Fruit("Pineapple", "Tropical fruit"));
    }

    @GET
    public Set<Fruit> list() {
        return fruits;
    }

    @POST
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    /**
     * 删除逻辑还是有点问题的
     * @param fruit 这个对象的反序列化有问题
     * @return 返回全部列表
     */
    @DELETE
    public Set<Fruit> delete(Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
        return fruits;
    }

}

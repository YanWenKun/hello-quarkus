package fun.yanwk.helloquarkus.rest.json;

public class Fruit {

    public String name;
    public String description;

    /**
     * 使用 JSON 序列化层，必须有默认构造器
     */
    public Fruit() {

    }

    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

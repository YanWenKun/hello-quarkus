package fun.yanwk.helloquarkus.rest.json;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Vegetable {

    public String name;
    public String description;

    public Vegetable() {

    }

    public Vegetable(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

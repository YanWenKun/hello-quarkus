package fun.yanwk.helloquarkus.hibernate.food;

import javax.persistence.*;

@Entity
@Table(name = "known_fruits") // 显式指定表名
@NamedQuery(name = "Fruits.findAll", // 显示指定查询语句
    query = "SELECT f FROM Fruit f ORDER BY f.name",
    hints = @QueryHint(name = "org.hibernate.cacheable", value = "true")) // 显示指定缓存查询结果
@Cacheable // 这是应用层／业务层的可缓存（二级缓存）
public class Fruit {

    @Id
    @SequenceGenerator(name = "fruitsSequence", sequenceName = "known_fruits_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fruitsSequence")
    private Integer id;

    @Column(length = 40, unique = true)
    private String name;

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

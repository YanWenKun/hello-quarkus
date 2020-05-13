package fun.yanwk.helloquarkus.hibernate.region;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable // 缓存并没有照顾到刷新问题（无锁）。缓存不包括集合类、与其他实体类的关联（外键）
public class Country {

    @Id
    @GeneratedValue
    Long id;

    int dialInCode;

    String name;

    @OneToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY) // 指定读缓存，写不缓存
    List<City> cities;

}

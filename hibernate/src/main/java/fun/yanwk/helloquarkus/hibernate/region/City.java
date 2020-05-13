package fun.yanwk.helloquarkus.hibernate.region;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Cacheable // 缓存默认最多 1000 条，最长 100 秒空闲
public class City {
    @Id
    @GeneratedValue
    Long dialInCode;

    String name;
}

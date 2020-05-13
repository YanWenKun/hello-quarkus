package fun.yanwk.helloquarkus.hibernate.goods;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped // 该注解类似 @Configuration，但是更加限定了上下文范围，此处为整个程序
public class SantaClausService {

    // 只要项目依赖中有 quarkus-hibernate-orm，程序就会自动创建一个 EntityManagerFactory
    // 因此可以直接注入 EntityManager
    @Inject
    EntityManager entityManager;

    @Transactional // 该注解类似 @Bean，并且表明事务性，在提交事务时会征用并刷新 EntityManager
    public void createGift(String giftDescription) {
        Gift gift = new Gift();
        gift.setName(giftDescription);
        entityManager.persist(gift); // 所有数据库写操作都应确保事务性
    }
}

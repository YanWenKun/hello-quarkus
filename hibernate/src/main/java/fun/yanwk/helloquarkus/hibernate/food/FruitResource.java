package fun.yanwk.helloquarkus.hibernate.food;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Path("/fruits")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FruitResource {
    private static final Logger LOGGER = Logger.getLogger(FruitResource.class.getName());

    @Inject
    EntityManager entityManager;

    @GET
    public Fruit[] get() {
        return entityManager.createNamedQuery("Fruits.findAll", Fruit.class)
            .getResultList().toArray(new Fruit[0]);
    }

    @GET
    @Path("{id}")
    public Fruit getSingle(@PathParam Integer id) {
        Fruit entity = entityManager.find(Fruit.class, id);
        if (id == null) {
            throw new WebApplicationException("水果 id: " + id + " 不存在！", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Fruit fruit) { // 注意这里返回的是 Response，也存在注册反射的问题
        if (fruit.getId() != null) { // ID 已被占据
            throw new WebApplicationException("ID 无效！", 422);
        }

        entityManager.persist(fruit);
        return Response.ok(fruit).status(201).build(); // 如果不加 201，则返回 200
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Fruit update(@PathParam Integer id, Fruit fruit) {
        if (fruit.getName() == null) { // 没有这个名字的条目
            throw new WebApplicationException("名称无效！", 422);
        }

        Fruit entity = entityManager.find(Fruit.class, id);

        if (entity == null) {
            throw new WebApplicationException("按 ID 查找水果： " + id + " 什么也没查到。", 404);
        }

        entity.setName(fruit.getName());

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Integer id) {
        Fruit entity = entityManager.getReference(Fruit.class, id);
        if (entity == null) {
            throw new WebApplicationException("找不到 ID： " + id, 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build(); // HTTP 204 No Content
    }

    /**
     * 使异常返回 HTTP 500，而不是默认的抛出 HTML StackTrace
     */
    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("处理请求失败", exception);

            int code = 500;
            if (exception instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            JsonObjectBuilder entityBuilder = Json.createObjectBuilder()
                .add("exceptionType", exception.getClass().getName())
                .add("code", code);

            if (exception.getMessage() != null) {
                entityBuilder.add("error", exception.getMessage());
            }

            return Response.status(code)
                .entity(entityBuilder)
                .build();
        }
    }

}

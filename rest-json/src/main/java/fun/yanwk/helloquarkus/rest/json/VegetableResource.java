package fun.yanwk.helloquarkus.rest.json;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Path("/vegetables")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VegetableResource {

    private Set<Vegetable> vegetables = Collections.synchronizedSet(new LinkedHashSet<>());

    public VegetableResource() {
        vegetables.add(new Vegetable("萝卜", "又名大根"));
        vegetables.add(new Vegetable("黄瓜", "又名小根"));
    }

    @GET
    public Response list() {
        return Response.ok(vegetables).build();
    }

}

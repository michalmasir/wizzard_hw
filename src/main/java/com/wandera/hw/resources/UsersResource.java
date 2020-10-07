package com.wandera.hw.resources;

import com.codahale.metrics.annotation.Timed;
import com.wandera.hw.api.User;
import com.wandera.hw.service.DataStructureWrapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    private final DataStructureWrapper dataStructureWrapper;

    public UsersResource(DataStructureWrapper dataStructureWrapper) {
        this.dataStructureWrapper = dataStructureWrapper;
    }

    @GET
    @Timed
    public Response getAllUsers() {
        List<User> result = dataStructureWrapper.getUserList();
        return result == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(result).build();
    }
}

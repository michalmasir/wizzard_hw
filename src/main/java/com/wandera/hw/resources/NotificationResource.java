package com.wandera.hw.resources;

import com.codahale.metrics.annotation.Timed;
import com.wandera.hw.api.Notification;
import com.wandera.hw.service.DataStructureWrapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.TreeSet;

@Path("/notification")
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {

    private final DataStructureWrapper dataStructureWrapper;

    public NotificationResource(DataStructureWrapper dataStructureWrapper) {
        this.dataStructureWrapper = dataStructureWrapper;
    }

    @GET
    @Timed
    @Path("/list/{userGuid}")
    public Response getNotificationsByUserId(@PathParam("userGuid") String userGuid) {
        HashMap<String, TreeSet<Notification>> result = dataStructureWrapper.getNotificationStructureByUserId(userGuid);
        return result == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(result).build();
    }

    @GET
    @Timed
    @Path("/{notificationGuid}")
    public Response getNotificationById(@PathParam("notificationGuid") String notificationGuid) {
        Notification result = dataStructureWrapper.getNotification(notificationGuid);
        return result == null ? Response.status(Response.Status.NOT_FOUND).build() : Response.ok(result).build();
    }

    @PUT
    @Timed
    @Path("/{notificationGuid}")
    public Response setNotificationDisplayedStatus(@PathParam("notificationGuid") String notificationGuid, Notification notification) {
        Notification result = dataStructureWrapper.getNotification(notificationGuid);
        if (result != null) {
            result.setDisplayedStatus(notification.getDisplayedStatus());
            return Response.ok(result).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Timed
    @Path("/{notificationGuid}")
    public Response deleteNotification(@PathParam("notificationGuid") String notificationGuid) {
        boolean result = dataStructureWrapper.deleteNotification(notificationGuid);
        return result ? Response.ok().build() : Response.status(Response.Status.NOT_FOUND).build();
    }


}

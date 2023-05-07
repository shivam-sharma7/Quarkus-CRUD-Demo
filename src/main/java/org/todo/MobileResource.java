package org.todo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/mobile")
public class MobileResource {
     List<String> mobileList = new ArrayList<>();

     @GET
     @Produces(MediaType.TEXT_PLAIN)
     public Response getMobile(){
         return Response.ok(mobileList).build();
     }

     @POST
     @Consumes(MediaType.TEXT_PLAIN)
     @Produces(MediaType.TEXT_PLAIN)
     public Response addNewMobile(String mobileName){
         mobileList.add(mobileName);
         return Response.ok(mobileName).build();
     }

    @PUT
    @Path("/{oldmobilename}?{newmobilename}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobile(@PathParam("oldmobilename") String oldMobileName, @QueryParam("newmobilename") String newMobileName) {
        mobileList.stream().map(mobile -> {
            if(mobile.equals(oldMobileName)){
                return newMobileName;

            } else{
                return mobile;
            }

        }).collect(Collectors.toList());

        return Response.ok(mobileList).build();
    }
}

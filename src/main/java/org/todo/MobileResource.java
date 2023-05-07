package org.todo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

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
     public Response addNewMobile(String mobileName){
         mobileList.add(mobileName);
         return Response.ok(mobileName).build();
     }

}

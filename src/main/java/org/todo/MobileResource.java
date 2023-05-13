package org.todo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*Created an array list to store mobiles list */

@Path("/mobile")
public class MobileResource {
     List<String> mobileList = new ArrayList<>();


    /*Created a Get method to show mobile list  */

     @GET
     @Produces(MediaType.TEXT_PLAIN)
     public Response getMobile(){
         return Response.ok(mobileList).build();
     }

    /*Created a POST method to add new mobile  */

     @POST
     @Consumes(MediaType.TEXT_PLAIN)
     @Produces(MediaType.TEXT_PLAIN)
     public Response addNewMobile(String mobileName){
         mobileList.add(mobileName);
         return Response.ok(mobileName).build();
     }

    /*Created a PUT method to update old mobile names in list with new mobile name  */

    @PUT
    @Path("/{oldmobilename}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMobile(@PathParam("oldmobilename") String oldMobileName, @QueryParam("newmobilename") String newMobileName) {
        mobileList = mobileList.stream().map(mobile -> {
            if(mobile.equals(oldMobileName)){
                return newMobileName;

            } else{
                return mobile;
            }

        }).collect(Collectors.toList());

        return Response.ok(mobileList).build();
    }

    /*Created a DELETE method to delete a mobile from  list  */

   @DELETE
   @Path("{mobileToDelete}")
   @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobileToDelete") String mobileName){

        boolean isRemoved =  mobileList.remove(mobileName);
        if(isRemoved){
            return Response.noContent().build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}

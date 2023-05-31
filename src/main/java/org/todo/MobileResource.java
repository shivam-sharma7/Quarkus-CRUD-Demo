package org.todo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*Created an array list to store mobiles list and his properties */

@Path("/mobile")
public class MobileResource {
    List<Mobile> mobileList = new ArrayList<>();

    /*Created a Get request to get all mobile list and his properties */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }

    /*Created a POST request to Post a mobile list and his properties */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMobile(Mobile newMobile){
        mobileList.add(newMobile);
        return Response.ok(newMobile).build();
     }

    /*Created a PUT request to update a mobile list and his properties.
     here we have take @Path("/{id}") because we can tell which mobile properties we want to change, so in this case id would be different for every mobile */

     @PUT
     @Path("/{id}")
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
    public Response updateMobile(@PathParam("id") int id, Mobile mobileToUpdate) {
         mobileList = mobileList.stream().map(mobile->{
             if(mobile.getId() == id){
                 return mobileToUpdate;
             }else{
                 return mobile;
             }
         }).collect(Collectors.toList());

         return Response.ok(mobileList).build();
     }

    /*Created a DELETE request to Delete a mobile list and his properties */

    @DELETE
    @Path("/{id}")
    public Response deleteMobile(@PathParam("id") int mobileIdToDelete){
       Optional<Mobile> mobileToDelete  = mobileList.stream()
               .filter(mobile -> mobile.getId()== mobileIdToDelete)
               .findFirst();

       if(mobileToDelete.isPresent()){
           mobileList.remove(mobileToDelete.get());
           return Response.ok(mobileList).build();
       } else{
           return Response.status(Response.Status.BAD_REQUEST).build();
       }

    }

    /*Created a Get request to get a mobile list and his properties by the id */

    @GET
    @Path("/{id}")
    public Response getMobileById(@PathParam("id") int id){
        Optional<Mobile>optionalMobile = mobileList.stream().filter(mobile -> mobile.getId() == id).findFirst();
        if(optionalMobile.isPresent()){
            return Response.ok(optionalMobile.get()).build();
        }else{

            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}

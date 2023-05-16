package org.todo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

/*Created an array list to store mobiles list and his properties */

@Path("/mobile")
public class MobileResource {
    List<Mobile> mobileList = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMobileList(){
        return Response.ok(mobileList).build();
    }

    @POST
    public Response createMobile(Mobile newMobile){
        mobileList.add(newMobile);
        return Response.ok(newMobile).build();

     }


}

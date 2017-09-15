package rest;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/userServices")
public class UserService {

    UserDAO userDAO = new UserDAO();
    private static final String SUCCESS_RESULT="<result>success</result>";
    private static final String FAILURE_RESULT="<result>failure</result>";

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    public List<User> getUsers(){
        return userDAO.getAllUsers();
    }

    @GET
    @Path("users/{userId}")
    @Produces(MediaType.APPLICATION_XML)
    public User getUser(@PathParam("userId") int userId){
        return userDAO.getUser(userId);
    }

    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createUser(@FormParam("name") String name,
                             @FormParam("profession") String profession,
                             @Context HttpServletResponse servletResponse) throws IOException{
        userDAO.saveUser(name, profession);
    }

    @PUT
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateUser(@FormParam("id") int id,
                           @FormParam("name") String name,
                           @FormParam("profession") String profession,
                           @Context HttpServletResponse servletResponse) throws IOException{
        int result = userDAO.updateUser(id, name, profession);
        if (result == 1){
            return SUCCESS_RESULT;
        } else {
            return FAILURE_RESULT;
        }
    }

    @DELETE
    @Path("/users/{userid}")
    @Produces(MediaType.APPLICATION_XML)
    public String deleteUser(@PathParam("userid") int userId){
        int result = userDAO.deleteUser(userId);
        if (result == 1){
            return SUCCESS_RESULT;
        } else {
            return FAILURE_RESULT;
        }
    }

    @OPTIONS
    @Path("/users")
    @Produces(MediaType.APPLICATION_XML)
    public String getSupportedOperations(){
        return "<operations>GET, PUT, POST, DELETE</operations>";
    }




}

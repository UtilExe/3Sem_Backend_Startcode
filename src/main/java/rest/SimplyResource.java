package rest;

//Todo Remove or change relevant parts before ACTUAL use

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieRequestDTO;
import dto.MovieResponseDTO;
import dto.SimplyResponseDTO;
import facades.FacadeExample;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import utils.Helper;
import utils.HttpUtils;
import utils.Keys;

@Path("simply")
public class SimplyResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample facade =  FacadeExample.getFacadeExample(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String simplyURL = "https://api.simply.com/1/UE304869/tMvabF8omRgQnSpq/my/products/";
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static Helper helper = new Helper();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // Not working, jsonStr = scan.nextLine(); in HttpUtills never hitting cause it's null properly(?). Maybe some header issue?
    @Path("list")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
 //   @RolesAllowed({"admin"})
    public String getSimplyProducts() throws IOException {
        String test ="https://api.simply.com/1/UE304869/hiddenKey/my/products/";
        String simply = HttpUtils.fetchData(test);
        SimplyResponseDTO simplyDTO = gson.fromJson(simply, SimplyResponseDTO.class);
        return gson.toJson(simplyDTO);
    }
    
    /*
     @Path("admin")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public String getDigitalOceanInfo() throws IOException {
        String URL = digitalOceanURL + "droplets";
        String digitalOcean = HttpUtils.fetchData(URL);
        DigitalOceanResponseDTO digitalOceanDTO = gson.fromJson(digitalOcean, DigitalOceanResponseDTO.class);
        return gson.toJson(digitalOceanDTO);
    }
    */
}
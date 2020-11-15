package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DigitalOceanResponseDTO;
import dto.SallingResponseDTO;
import facades.FacadeExample;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
import utils.Helper;
import utils.HttpUtils;

@Path("salling")
public class SallingResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final FacadeExample facade = FacadeExample.getFacadeExample(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String sallingHolidayURL = "https://api.sallinggroup.com/v1/holidays/";
    private static final ExecutorService es = Executors.newCachedThreadPool();
    private static Helper helper = new Helper();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("holidays")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
   // @RolesAllowed({"admin"})
    public String getHolidays() throws IOException {
        String URL = sallingHolidayURL+"?translation=da-dk";
        String salling = HttpUtils.fetchData(URL);
        SallingResponseDTO sallingRespDTO = gson.fromJson(salling, SallingResponseDTO.class);
        return gson.toJson(sallingRespDTO);
    }
}

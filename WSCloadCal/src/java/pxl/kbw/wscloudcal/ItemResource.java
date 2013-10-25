/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pxl.kbw.wscloudcal;

import com.sun.jersey.api.core.InjectParam;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import pxl.items.ws.model.Item;


/**
 * REST Web Service
 *
 * @author Jonas
 */
@Path("item")

public class ItemResource {

    @Context
    private UriInfo context;
    
    @InjectParam
    private ItemStorage itemStorage;

    /**
     * Creates a new instance of ContactResource
     */
    public ItemResource() {
    }

    /**
     * Retrieves representation of an instance of be.pxl.contacts.ws.rest.ContactResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public List<Item> getJson() {
        //TODO return proper representation object
        return this.itemStorage.getItems();
    }

    /**
     * PUT method for updating or creating an instance of ContactResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(Item item) {
        this.itemStorage.addItem(item);
                
    }
}

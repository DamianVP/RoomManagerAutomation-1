package steps.hooks;

import entities.ConferenceRooms;
import entities.Location;
import entities.OutOfOrders;
import entities.Resource;
import framework.APIManager;
import framework.DBQuery;
import ui.PageTransporter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/16/15
 * Time: 7:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class SetUpConferenceRoom {

    public static ConferenceRooms conferenceRooms;
    public static Resource resource;
    public static Location location;
    public static OutOfOrders outOfOrders;

    public static  ArrayList<Resource> resourcesList = new ArrayList<>();
    public static  ArrayList<Location> locationsList = new ArrayList<>();



//    public static String nameLocation="Location1";
//    public static ConferenceRooms conferenceRooms;


    public static void beforeResourceFeature(){

        System.out.println("###################### : Before Feature ROOMS ");

    }
    public static void afterConferenceRoomsFeature(){
        System.out.println("##################### : After Feature ROOMS ");
        /*
        Delete Resource created
         */

//        String idResource=DBQuery.getInstance().getIdByKey(EnumKeys.RESOURCE_KEY.nameCollection,EnumKeys.RESOURCE_KEY.name,"Printer");
//        System.out.println("ID Resource to be eliminated : "+ idResource);
//        resource.setName("Printer");
//        resource.setID(idResource);
        resourcesList.add(resource);
        APIManager.getInstance().deleteResourcesById(resourcesList);
        /*
        Delete the location created
         */

//        String idLocation=DBQuery.getInstance().getIdByKey(EnumKeys.LOCATION_KEY.nameCollection, EnumKeys.LOCATION_KEY.name,nameLocation)  ;
//        location.setName(nameLocation);
//        location.setId(idLocation);
        locationsList.add(location);
        APIManager.getInstance().deleteLocationByID(locationsList);
        /*
        Delete the outOfOrders
         */
//        String serviceId = DBQuery.getInstance().getIdByKey("services","name","Microsoft Exchange Server 2010 SP3");
//        String roomId = DBQuery.getInstance().getIdByKey("rooms","displayName","Floor1Room14");
//        String outOfOrderId = DBQuery.getInstance().getIdByKey("outoforders","roomId", roomId);
//        APIManager.getInstance().deleteOutOfOrder(serviceId,roomId,outOfOrderId);

        String serviceId = DBQuery.getInstance().getIdByKey("services","name","Microsoft Exchange Server 2010 SP3");
        String roomId = DBQuery.getInstance().getIdByKey("rooms","displayName",conferenceRooms.getName());
        String outOfOrderId = DBQuery.getInstance().getIdByKey("outoforders","roomId", conferenceRooms.getId());
        APIManager.getInstance().deleteOutOfOrder(serviceId,roomId,outOfOrderId);
        /*
        Enable the room disable in the steps
         */
        APIManager.getInstance().activateConferenceRooms(conferenceRooms.getId());
        PageTransporter.getInstance().refreshPage();
    }
}

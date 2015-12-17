package steps.hooks;

import common.EnumKeys;
import entities.ConferenceRooms;
import entities.Location;
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
    public static ArrayList<Resource> resources=new ArrayList<Resource>();
    public static Resource resource;


    public static ArrayList<Location> locations=new ArrayList<Location>();
    public static Location location;
    static String nameLocation="Location1";
    public static ConferenceRooms conferenceRooms;


    public static void beforeResourceFeature(){

        System.out.println("########## : Before Feature ROOMS ");

    }
    public static void afterConferenceRoomsFeature(){
        /*
        Delete Resource created
         */
        String idResource= DBQuery.getInstance().getIdByKey(EnumKeys.RESOURCEKEY.nameCollection,EnumKeys.RESOURCEKEY.name,"Printer");
        resource.setName("Printer");
        resource.setID(idResource);
        resources.add(resource);
        APIManager.getInstance().deleteResourcesById(resources);
        /*
        Delete the location created
         */
        String idLocation=DBQuery.getInstance().getIdByKey(EnumKeyLocation.LOCATIONKEY.nameCollection,EnumKeyLocation.LOCATIONKEY.name,nameLocation)  ;
        location.setName(nameLocation);
        location.setId(idLocation);
        locations.add(location);
        APIManager.getInstance().deleteLocationByID(locations);
        /*
        Delete the outOfOrders
         */
        String serviceId = DBQuery.getInstance().getIdByKey("services","name","Microsoft Exchange Server 2010 SP3");
        String roomId = DBQuery.getInstance().getIdByKey("rooms","displayName","Floor1Room14");
        String outOfOrderId = DBQuery.getInstance().getIdByKey("outoforders","roomId", roomId);
        APIManager.getInstance().deleteOutOfOrder(serviceId,roomId,outOfOrderId);
        /*
        Enable the room disable in the steps
         */
        APIManager.getInstance().activateConferenceRooms(roomId);
        PageTransporter.getInstance().refreshPage();
    }

}

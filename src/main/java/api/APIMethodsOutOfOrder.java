package api;

import com.jayway.restassured.response.Response;
import entities.OutOfOrder;
import org.json.JSONArray;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/19/15
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class APIMethodsOutOfOrder {
    private static APIManager apiManager = APIManager.getInstance();

    public static OutOfOrder getOutOfOrderByTitle(String roomId) {
        OutOfOrder outOfOrder = new OutOfOrder();
        Response response = given().when().get("/out-of-orders");
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString("roomId").equalsIgnoreCase(roomId)) {
                outOfOrder.set_Id(jsonArray.getJSONObject(indice).getString("_id"));
                outOfOrder.setTitle(jsonArray.getJSONObject(indice).getString("title"));
                outOfOrder.setRoomID(jsonArray.getJSONObject(indice).getString("roomId"));
            }
        }
        return outOfOrder;
    }

    public static void deleteOutOfOrder(String serviceId, String roomId, String outOfOrderId){
        given()
                .header("Authorization", "jwt " + apiManager.getToken())
                .delete("/services/"+serviceId+"rooms/"+roomId+"/out-of-orders"+outOfOrderId);
    }

    public static void deleteOutOfOrder(OutOfOrder outOfOrder){
        given()
                .header("Authorization", "jwt " + apiManager.getToken())
                .delete("/services/"+apiManager.getServiceId()+"rooms/"+outOfOrder.getRoomID()+"/out-of-orders"+
                        outOfOrder.get_Id())
        ;

    }
}

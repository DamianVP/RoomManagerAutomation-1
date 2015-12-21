package api;

import com.jayway.restassured.response.Response;
import common.Enum;
import entities.ConferenceRoom;
import org.json.JSONArray;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: jhasmanyquiroz
 * Date: 12/19/15
 * Time: 6:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class APIMethodsRoom {
    private static APIManager apiManager = APIManager.getInstance();

    public static void activateConferenceRooms(String roomId){
        given()
                .header("Authorization", "jwt "+ apiManager.getToken())
                .parameters("enabled", true)
                .put("/rooms/"+roomId);
    }

    //REVISAR
    public static ConferenceRoom getConferenceRoomByName(ConferenceRoom conferenceRoom) {
        ConferenceRoom conferenceRoom1 = new ConferenceRoom();

        Response response = given().when().get("/rooms");
        JSONArray jsonArray = new JSONArray(response.asString());
        for (int indice = 0; indice < jsonArray.length(); indice++) {
            if (jsonArray.getJSONObject(indice).getString(common.Enum.ROOM_KEY.name).equalsIgnoreCase(conferenceRoom.getName())) {
                conferenceRoom1.setName(jsonArray.getJSONObject(indice).getString(Enum.ROOM_KEY.name));
                conferenceRoom1.setDisplayName(jsonArray.getJSONObject(indice).getString(Enum.ROOM_KEY.customName));
                conferenceRoom1.setId(jsonArray.getJSONObject(indice).getString(Enum.ROOM_KEY.id));
                conferenceRoom1.setEnabled(jsonArray.getJSONObject(indice).getBoolean(Enum.ROOM_KEY.enable));
            }
        }
        return conferenceRoom1;
    }
}

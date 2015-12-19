package framework;

import com.jayway.restassured.response.Response;
import entities.ConferenceRooms;
import org.json.JSONArray;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created with IntelliJ IDEA.
 * User: DamianVillanueva
 * Date: 12/14/15
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class testApi {
    public static void main(String ar[]) {
        ConferenceRooms conferenceRooms = APIManager.getInstance()
                .getConferenceRoomByName("Floor1Room6");
        System.out.println("Name "+conferenceRooms.getResource());
        System.out.println("Id "+conferenceRooms.getId());

        Response response = given().when().get("/rooms/565f3bf404da548007b798d0/resources");
        JSONArray jsonArray = new JSONArray(response.asString());
        System.out.println("JSON conference room - "+response.asString());

//        Meeting meeting = APIManager.getInstance().createMeeting()
    }


}

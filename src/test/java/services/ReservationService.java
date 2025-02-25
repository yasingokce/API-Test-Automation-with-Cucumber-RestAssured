package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Auth;
import models.Booking;
import models.BookingDates;
import models.BookingResponse;

import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest {
    // create token
    /*
     * curl -X POST \
     * https://restful-booker.herokuapp.com/auth \
     * -H 'Content-Type: application/json' \
     * -d '{
     * "username" : "admin",
     * "password" : "password123"
     * }'
     */

    public String createToken() {
        Auth authBody = new Auth("admin", "password123");
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");
        response
                .then()
                .statusCode(200);

        return response.jsonPath().getJsonObject("token");

    }

    // create reservation
    /*
     * curl -X POST \
     * https://restful-booker.herokuapp.com/booking \
     * -H 'Content-Type: application/json' \
     * -d '{
     * "firstname" : "Jim",
     * "lastname" : "Brown",
     * "totalprice" : 111,
     * "depositpaid" : true,
     * "bookingdates" : {
     * "checkin" : "2018-01-01",
     * "checkout" : "2019-01-01"
     * },
     * "additionalneeds" : "Breakfast"
     * }'
     */

    public BookingResponse createBooking() {
        BookingDates bookingDates = new BookingDates("2023-04-01", "2023-05-01");
        Booking booking = new Booking("Jennifer", "Lopez", 1000, false, bookingDates, "have animal available room");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");
        response.then().statusCode(200);

        return response.as(BookingResponse.class);

    }

    // delete reservation
    public void deleteReservation(String token, int bookingid) {
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + bookingid);

        response
                .then()
                .statusCode(201);

        /*-curl -X DELETE \
        https://restful-booker.herokuapp.com/booking/1 \
        -H 'Content-Type: application/json' \
        -H 'Cookie: token=abc123' */

    }

}

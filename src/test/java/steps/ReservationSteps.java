package steps;

import models.BookingResponse;
import services.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {

    ReservationService reservationService;
    String authKey;
    BookingResponse bookingResponse;

    @Given("User creates a new reservation")
    public void startCalling() {
        reservationService = new ReservationService();
    }

    @Given("The user provides the information required for the reservation")
    public void createAuth() {
        authKey = reservationService.createToken();
    }

    @When("User creates hotel reservation")
    public void createReservation() {
        bookingResponse = reservationService.createBooking();
    }

    @Then("Reservation was successfully created")
    public void reservationAssertions() {
        Assertions.assertEquals("Jennifer", bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Lopez", bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(1000, bookingResponse.getBooking().getTotalprice());
        Assertions.assertFalse(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("have animal available room", bookingResponse.getBooking().getAdditionalneeds());
    }

    @Then("User cancels the created reservation")
    public void cancelReservation() {
        reservationService.deleteReservation(authKey, bookingResponse.getBookingid());
    }
}
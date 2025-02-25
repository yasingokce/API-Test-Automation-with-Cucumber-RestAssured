package models;

public class BookingDates {
    private String checkin;
    private String checkout;

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingDates() {

    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    /*"checkin" : "2018-01-01",
        "checkout" : "2019-01-01" */

}

package socar;


public class ReservationConfirmed extends AbstractEvent {

    private Long rsvId;
    private Long carId;
    private String status;
    private Long payId;

    public Long getrsvId() {
        return rsvId;
    }

    public void setrsvId(Long rsvId) {
        this.rsvId = rsvId;
    }
    public Long getcarId() {
        return carId;
    }

    public void setcarId(Long carId) {
        this.carId = carId;
    }
    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }
    public Long getpayId() {
        return payId;
    }

    public void setpayId(Long payId) {
        this.payId = payId;
    }
}

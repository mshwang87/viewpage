package socar;


public class PaymentCancelled extends AbstractEvent {

    private Long payId;
    private Long rsvId;
    private Long carId;
    private String status;

    public Long getpayId() {
        return payId;
    }

    public void setpayId(Long payId) {
        this.payId = payId;
    }
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
}

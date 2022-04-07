package socar;


public class CarRegistered extends AbstractEvent {

    private Long carId;
    private String status;
    private String desc;
    private String amount;
    private String carName;
    private String carType;

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
    public String getdesc() {
        return desc;
    }

    public void setdesc(String desc) {
        this.desc = desc;
    }
    public String getamount() {
        return amount;
    }

    public void setamount(String amount) {
        this.amount = amount;
    }
    public String getcarName() {
        return carName;
    }

    public void setcarName(String carName) {
        this.carName = carName;
    }
    public String getcarType() {
        return carType;
    }

    public void setcarType(String carType) {
        this.carType = carType;
    }
}

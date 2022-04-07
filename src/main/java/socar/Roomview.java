package socar;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Roomview_table")
public class Roomview {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long carId;
        private String carStatus;
        private Long rsvId;
        private String rsvStatus;
        private Long payId;
        private String payStatus;
        private String carName;
        private String carType;


        public Long getCarId() {
            return carId;
        }

        public void setCarId(Long carId) {
            this.carId = carId;
        }
        public String getCarStatus() {
            return carStatus;
        }

        public void setCarStatus(String carStatus) {
            this.carStatus = carStatus;
        }
        public Long getRsvId() {
            return rsvId;
        }

        public void setRsvId(Long rsvId) {
            this.rsvId = rsvId;
        }
        public String getRsvStatus() {
            return rsvStatus;
        }

        public void setRsvStatus(String rsvStatus) {
            this.rsvStatus = rsvStatus;
        }
        public Long getPayId() {
            return payId;
        }

        public void setPayId(Long payId) {
            this.payId = payId;
        }
        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }
        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }
        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

}
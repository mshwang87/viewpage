package socar;

import socar.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CarviewViewHandler {


    @Autowired
    private CarviewRepository carviewRepository;

    // 차량이 등록되었을 때 insert -> viewpage table 
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCarRegistered_then_CREATE_1 (@Payload CarRegistered carRegistered) {
        try {

            if (!carRegistered.validate()) return;

            // view 객체 생성
            Carview carview = new Carview();
            // view 객체에 이벤트의 Value 를 set 함
            carview.setCarId(carRegistered.getcarId());
            carview.setCarStatus(carRegistered.getstatus());
            carview.setCarName(carRegistered.getcarName());
            carview.setCarType(carRegistered.getcarType());
            // view 레파지 토리에 save
            carviewRepository.save(carview);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // 차량이 수정되었을 때 update -> viewpage table 
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCarModified_then_UPDATE_1(@Payload CarModified carModified) {
        try {
            if (!carModified.validate()) return;
                // view 객체 조회
            Optional<Carview> carviewOptional = carviewRepository.findById(carModified.getcarId());

            if( carviewOptional.isPresent()) {
                 Carview carview = carviewOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 carview.setCarStatus(carModified.getstatus());
                 carview.setCarName(carModified.getcarName());
                 carview.setCarType(carModified.getcarType());
                // view 레파지 토리에 save
                 carviewRepository.save(carview);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // 예약이 확정되었을 때 update -> viewpage table 
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationConfirmed_then_UPDATE_2(@Payload ReservationConfirmed reservationConfirmed) {
        try {
            if (!reservationConfirmed.validate()) return;
                // view 객체 조회
            Optional<Carview> carviewOptional = carviewRepository.findById(reservationConfirmed.getcarId());

            if( carviewOptional.isPresent()) {
                 Carview carview = carviewOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 carview.setRsvId(reservationConfirmed.getrsvId());
                 carview.setRsvStatus(reservationConfirmed.getstatus());
                // view 레파지 토리에 save
                 carviewRepository.save(carview);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // 결제가 완료 되었을 때 update -> viewpage table 
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_3(@Payload PaymentApproved paymentApproved) {
        try {
            if (!paymentApproved.validate()) return;
                // view 객체 조회

                    List<Carview> carviewList = carviewRepository.findByRsvId(paymentApproved.getrsvId());
                    for(Carview carview : carviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    carview.setPayId(paymentApproved.getpayId());
                    carview.setPayStatus(paymentApproved.getstatus());
                // view 레파지 토리에 save
                carviewRepository.save(carview);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // 예약이 취소 되었을 때 update -> viewpage table
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCancelled_then_UPDATE_4(@Payload ReservationCancelled reservationCancelled) {
        try {
            if (!reservationCancelled.validate()) return;
                // view 객체 조회

                    List<Carview> carviewList = carviewRepository.findByRsvId(reservationCancelled.getrsvId());
                    for(Carview carview : carviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    carview.setRsvStatus(reservationCancelled.getstatus());
                // view 레파지 토리에 save
                carviewRepository.save(carview);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // 결제가 취소 되었을 때 update -> viewpage table
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCancelled_then_UPDATE_5(@Payload PaymentCancelled paymentCancelled) {
        try {
            if (!paymentCancelled.validate()) return;
                // view 객체 조회

                    List<Carview> carviewList = carviewRepository.findByPayId(paymentCancelled.getpayId());
                    for(Carview carview : carviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    carview.setPayStatus(paymentCancelled.getstatus());
                // view 레파지 토리에 save
                carviewRepository.save(carview);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 차량정보를 삭제 하였을 때  delete -> viewpage table
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCarDeleted_then_DELETE_1(@Payload CarDeleted carDeleted) {
        try {
            if (!carDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            carviewRepository.deleteById(carDeleted.getcarId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


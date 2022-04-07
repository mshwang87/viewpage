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

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCarRegistered_then_CREATE_1 (@Payload CarRegistered carRegistered) {
        try {

            if (!carRegistered.validate()) return;

            // view 객체 생성
            Carview carview = new Carview();
            // view 객체에 이벤트의 Value 를 set 함
            carview.setcarId(carRegistered.getcarId());
            carview.setcarStatus(carRegistered.getstatus());
            carview.setcarName(carRegistered.getcarName());
            carview.setcarType(carRegistered.getcarType());
            // view 레파지 토리에 save
            carviewRepository.save(carview);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenCarModified_then_UPDATE_1(@Payload CarModified carModified) {
        try {
            if (!carModified.validate()) return;
                // view 객체 조회
            Optional<Carview> carviewOptional = carviewRepository.findBycarId(carModified.getcarId());

            if( carviewOptional.isPresent()) {
                 Carview carview = carviewOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 carview.setcarStatus(carModified.getstatus());
                 carview.setcarName(carModified.getcarName());
                 carview.setcarType(carModified.getcarType());
                // view 레파지 토리에 save
                 carviewRepository.save(carview);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationConfirmed_then_UPDATE_2(@Payload ReservationConfirmed reservationConfirmed) {
        try {
            if (!reservationConfirmed.validate()) return;
                // view 객체 조회
            Optional<Carview> carviewOptional = carviewRepository.findBycarId(reservationConfirmed.getcarId());

            if( carviewOptional.isPresent()) {
                 Carview carview = carviewOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 carview.setRsvId(reservationConfirmed.getRsvId());
                 carview.setRsvStatus(reservationConfirmed.getStatus());
                // view 레파지 토리에 save
                 carviewRepository.save(carview);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentApproved_then_UPDATE_3(@Payload PaymentApproved paymentApproved) {
        try {
            if (!paymentApproved.validate()) return;
                // view 객체 조회

                    List<Carview> carviewList = carviewRepository.findByRsvId(paymentApproved.getRsvId());
                    for(Carview carview : carviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    carview.setPayId(paymentApproved.getPayId());
                    carview.setPayStatus(paymentApproved.getStatus());
                // view 레파지 토리에 save
                carviewRepository.save(carview);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCancelled_then_UPDATE_4(@Payload ReservationCancelled reservationCancelled) {
        try {
            if (!reservationCancelled.validate()) return;
                // view 객체 조회

                    List<Carview> carviewList = carviewRepository.findByRsvId(reservationCancelled.getRsvId());
                    for(Carview carview : carviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    carview.setRsvStatus(reservationCancelled.getStatus());
                // view 레파지 토리에 save
                carviewRepository.save(carview);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaymentCancelled_then_UPDATE_5(@Payload PaymentCancelled paymentCancelled) {
        try {
            if (!paymentCancelled.validate()) return;
                // view 객체 조회

                    List<Carview> carviewList = carviewRepository.findByPayId(paymentCancelled.getPayId());
                    for(Carview carview : carviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    carview.setPayStatus(paymentCancelled.getStatus());
                // view 레파지 토리에 save
                carviewRepository.save(carview);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

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


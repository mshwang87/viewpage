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
public class RoomviewViewHandler {


    @Autowired
    private RoomviewRepository roomviewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCarRegistered_then_CREATE_1 (@Payload CarRegistered carRegistered) {
        try {

            if (!carRegistered.validate()) return;

            // view 객체 생성
            Roomview roomview = new Roomview();
            // view 객체에 이벤트의 Value 를 set 함
            roomview.setId(carRegistered.getRoomId());
            roomview.setDesc(carRegistered.getDesc());
            roomview.setReviewCnt(carRegistered.getReviewCnt());
            roomview.setRoomStatus(carRegistered.getStatus());
            // view 레파지 토리에 save
            roomviewRepository.save(roomview);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenCarModified_then_UPDATE_1(@Payload CarModified carModified) {
        try {
            if (!carModified.validate()) return;
                // view 객체 조회
            Optional<Roomview> roomviewOptional = roomviewRepository.findById(carModified.getRoomId());

            if( roomviewOptional.isPresent()) {
                 Roomview roomview = roomviewOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 roomview.setDesc(carModified.getDesc());
                 roomview.setReviewCnt(carModified.getReviewCnt());
                 roomview.setRoomStatus(carModified.getStatus());
                // view 레파지 토리에 save
                 roomviewRepository.save(roomview);
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
            Optional<Roomview> roomviewOptional = roomviewRepository.findById(reservationConfirmed.getRoomId());

            if( roomviewOptional.isPresent()) {
                 Roomview roomview = roomviewOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 roomview.setRsvId(reservationConfirmed.getRsvId());
                 roomview.setRsvStatus(reservationConfirmed.getStatus());
                // view 레파지 토리에 save
                 roomviewRepository.save(roomview);
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

                    List<Roomview> roomviewList = roomviewRepository.findByRsvId(paymentApproved.getRsvId());
                    for(Roomview roomview : roomviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    roomview.setPayId(paymentApproved.getPayId());
                    roomview.setPayStatus(paymentApproved.getStatus());
                // view 레파지 토리에 save
                roomviewRepository.save(roomview);
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

                    List<Roomview> roomviewList = roomviewRepository.findByRsvId(reservationCancelled.getRsvId());
                    for(Roomview roomview : roomviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    roomview.setRsvStatus(reservationCancelled.getStatus());
                // view 레파지 토리에 save
                roomviewRepository.save(roomview);
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

                    List<Roomview> roomviewList = roomviewRepository.findByPayId(paymentCancelled.getPayId());
                    for(Roomview roomview : roomviewList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    roomview.setPayStatus(paymentCancelled.getStatus());
                // view 레파지 토리에 save
                roomviewRepository.save(roomview);
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
            roomviewRepository.deleteById(carDeleted.getRoomId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


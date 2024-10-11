package com.ktu.haewooso_ver2.impl.messagePush;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.ktu.haewooso_ver2.domain.pushMessage.SendMsg;
import com.ktu.haewooso_ver2.dto.pushMessage.MessagePushDto;
import com.ktu.haewooso_ver2.repository.MessagePushRepository;
import com.ktu.haewooso_ver2.service.pushMessage.FCMNotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FCMNotificationServiceImpl implements FCMNotificationService {
    private final MessagePushRepository messagePushRepository;
    private final FirebaseMessaging firebaseMessaging;
    @Autowired
    public FCMNotificationServiceImpl(MessagePushRepository messagePushRepository, FirebaseMessaging firebaseMessaging){
        this.messagePushRepository = messagePushRepository;
        this.firebaseMessaging = firebaseMessaging;
    }

    /* 랜덤 푸시토큰 조회 */
    @Override
    public String getRandomReceiverToken(String uuid) {
        try{
            String randomToken = messagePushRepository.findByRandomReceiverToken(uuid);
            return randomToken;
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("400", HttpStatus.BAD_REQUEST).getBody();
        }
    }

    /* 푸시 알림 수신자의 UUID 조회 */
    @Override
    public String getReceiverUuid(String pushToken) {
        try{
            String uuidOfRendomToken = messagePushRepository.findByUuidOfRandomPushToken(pushToken);
            return uuidOfRendomToken;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("400", HttpStatus.BAD_REQUEST).getBody();
        }
    }

    @Override
    public ResponseEntity<String> sendNotificationByToken(MessagePushDto messagePushDto, String receiverToken) {

        Notification notification = Notification.builder()
                .setTitle(messagePushDto.getTitle())
                .setBody(messagePushDto.getContent())
                .build();

        Message message = Message.builder()
                .setToken(receiverToken)
                .setNotification(notification)
                .build();

        try{
            firebaseMessaging.send(message);
            return new ResponseEntity<String>("200", HttpStatus.OK);
        }catch (FirebaseMessagingException e){
            e.printStackTrace();
            return new ResponseEntity<String>("400", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> insertMessageInfomation(MessagePushDto messagePushDto, String secretAt) {
        try{
            SendMsg sendMsg = SendMsg.builder()
                    .sendUuid(messagePushDto.getSendUuid())
                    .receiveUuid(messagePushDto.getReceiveUuid())
                    .title(messagePushDto.getTitle())
                    .content(messagePushDto.getContent())
                    .secretAt(secretAt)
                    .build();
            messagePushRepository.save(sendMsg);
            System.out.println("sendMsg = " + sendMsg.toString());
            return new ResponseEntity<String>("200", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("400", HttpStatus.OK);
        }

    }
}

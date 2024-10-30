package com.ktu.haewooso_ver2.controller.secretPushController;

import com.ktu.haewooso_ver2.dto.count.ByUuid.RequestByUuidDto;
import com.ktu.haewooso_ver2.dto.pushMessage.SecretPushDto;
import com.ktu.haewooso_ver2.enums.board.SecretAt;
import com.ktu.haewooso_ver2.service.pushMessage.FCMNotificationService;
import com.ktu.haewooso_ver2.service.secretPushService.SecretPushService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="Haewooso Secret Message Push API", description = "해우소 앱의 특정 상대만 푸시 메시지 알람을 보내는 API")
@RestController
@RequestMapping("secret/")
public class SecretMessagePushController {

    private final SecretPushService secretPushService;
    private final FCMNotificationService FCMNotificationService;

    @Autowired
    public SecretMessagePushController(SecretPushService secretPushService, FCMNotificationService FCMNotificationService){
        this.secretPushService = secretPushService;
        this.FCMNotificationService = FCMNotificationService;
    }

    @Operation(summary = "시크릿 코드 생성 후 데이터 INSERT API v1", description="**시크릿 코드 생성 및 해당 시크릿 푸시 정보를 INSERT하는 API**" +
            "\n\n**_<<필요 파라미터>>_**\n\n" +
            "\n\nuuid : 시크릿코드를 생성한 유저의 uuid\n\n\n" +
            "\n\n\n**_<<HTTP 상태 코드>>_**\n\n" +
            "\n\n200 : SUCCESS\n\n" +
            "\n\n400 : BAD REQUEST\n\n")
    @PostMapping("v1/secret-code")
    public String creteRandomSecretCode(@RequestBody RequestByUuidDto requestByUuidDto){
        String requestId = requestByUuidDto.getUuid();
        System.out.println("===== requestId ===== : " + requestId);

        String result = secretPushService.createRandomCode();

        String resultStatus = secretPushService.createSecretCodeMsg(requestId, result).getBody();
        System.out.println("===== resultStatus ===== : " + resultStatus);

        // @Code 200 success
        // @Code 400 bad_request
        return result;
    }
    @Operation(summary = "시크릿 코드를 가진 유저에게 푸시 메시지를 보내는 API v1", description="**시크릿 코드를 가진 유저에게 푸시 메시지를 보내는 API**"
            +"\n\n**_<<필요 파라미터>>_**"
            +"\n\n_<<sendUuid : 시크릿 푸시 메시지를 보내는 유저의 uuid(발신자)>>_\n\n"
            +"\n\n_<<receiveUuid : 수신자 uuid (null 또는 보낼필요 없음.)>>_\n\n"
            +"\n\n_<<secretCode : 푸시 알림을 받을 유저의 시크릿 코드 (수신자)>>_\n\n"
            +"\n\n_<<title : 시크릿 푸시 메시지의 제목>>_\n\n"
            +"\n\n_<<content : 시크릿 푸시 메시지의 내용>>_\n\n"
            +"\n\n**_<<HTTP 상태코드>>_**"
            +"\n\n_200 : SUCCESS_\n\n"
            +"\n\n_400 : BAD REQUEST_\n\n"
            +"\n\n_452 : Secret Code NOT IN DataBase_\n\n")
    @PostMapping("v1/send")
    public String pushSecretMessageUser(@RequestBody @Valid SecretPushDto secretPushDto){

        // 해당 시크릿 코드를 가지고 있는 수신자의 푸시토큰 조회
        String receiveSecretCode = secretPushDto.getSecretCode();                                                  // 클라이언트에서 보낸 받는 사람의 시크릿코드
        String secretCodeInDB = secretPushService.validSecretCode(receiveSecretCode).orElse(null);           // DB에 존재하는 시크릿코드
        System.out.println("receiveSecretCode = " + receiveSecretCode);
        System.out.println("secretCodeInDB = " + secretCodeInDB);

        // 241029 시크릿코드 유효성 체크
        if(receiveSecretCode.equals(secretCodeInDB)) {

            String secretMessageReceiveToken = secretPushService.findPushTokenBySecretCode(receiveSecretCode);
            System.out.println("=== receiveSecretCode === : " + receiveSecretCode);
            System.out.println("=== secretMessageReceiveToken === : " + secretMessageReceiveToken);

            // 시크릿 푸시 메시지 send
            String result = FCMNotificationService.sendNotificationByToken(secretPushDto, secretMessageReceiveToken).getBody();
            // 수신자 uuid 조회
            String receiverUuid = FCMNotificationService.getReceiverUuid(secretMessageReceiveToken);
            secretPushDto.setReceiveUuid(receiverUuid);

            // 푸시알림 메시지 전송 성공 시, 보낸 푸시 알림 메시지 정보 insert
            if ("200".equals(result)) {
                // 시크릿 푸시 구분코드 생성 (시크릿 푸시 : S)
                SecretAt secretAt = SecretAt.S;
                result = FCMNotificationService.insertMessageInfomation(secretPushDto, secretAt).getBody();
            }

            // @HTTP_STATUS_CODE :
            //      200 (SUCCESS)
            //      400 (BAD REQUEST)
            return result;

        }else{
            System.out.println("Secret Code NOT IN DataBase");
            // @HTTP_STATUS_CODE :
            //      452 (Secret Code NOT IN DataBase)
            return new ResponseEntity<String>("452", HttpStatus.BAD_REQUEST).getBody();
        }
    }
}

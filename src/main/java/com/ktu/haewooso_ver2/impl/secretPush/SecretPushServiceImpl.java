package com.ktu.haewooso_ver2.impl.secretPush;

import com.ktu.haewooso_ver2.domain.member.Member;
import com.ktu.haewooso_ver2.domain.pushMessage.SecretCodeMsg;
import com.ktu.haewooso_ver2.repository.MemberRepository;
import com.ktu.haewooso_ver2.repository.SecretPushRepository;
import com.ktu.haewooso_ver2.service.secretPushService.SecretPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@Transactional
public class SecretPushServiceImpl implements SecretPushService {

    private final SecretPushRepository secretPushRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public SecretPushServiceImpl(SecretPushRepository secretPushRepository, MemberRepository memberRepository){
        this.secretPushRepository = secretPushRepository;
        this.memberRepository = memberRepository;
    }

    private static final int STRING_LENGTH = 7;

    @Override
    public String createRandomCode() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(STRING_LENGTH);

        int[][] ranges = {{49, 57},
                {65, 90},
                {97, 122}
        };

        for(int i = 0; i < STRING_LENGTH; i++){
            int randomIdx = secureRandom.nextInt(ranges.length);
            int[] selectRandomRange = ranges[randomIdx];

            char c = (char) (secureRandom.nextInt(selectRandomRange[1] - selectRandomRange[0]) + selectRandomRange[0]);
            sb.append(c);
        }

        return sb.toString();
    }

    @Override
    public ResponseEntity<String> createSecretCodeMsg(String uuid, String result) {
        try{
            Optional<Member> findMember = memberRepository.findByUuid(uuid);
            System.out.println(findMember.get().getUuid());

            SecretCodeMsg secretCodeMsg = SecretCodeMsg.builder()
                    .id(result)
                    .uuid(findMember.get().getUuid())
                    .useYn("Y")
                    .build();

            secretPushRepository.save(secretCodeMsg);

            return new ResponseEntity<String>("200", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("400", HttpStatus.OK);

        }
    }
}

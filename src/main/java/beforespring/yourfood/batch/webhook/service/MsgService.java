package beforespring.yourfood.batch.webhook.service;

import beforespring.yourfood.batch.webhook.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MsgService {
    @Value("${discord.webhook.url}")
    String webhookUrl;

    public boolean sendMsg(String msg){

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json; utf-8");
            HttpEntity<Message> messageEntity = new HttpEntity<>(new Message(msg), httpHeaders);

            RestTemplate template = new RestTemplate();
            ResponseEntity<String> response = template.exchange(
                webhookUrl,
                HttpMethod.POST,
                messageEntity,
                String.class
            );


            // response에  처대한리
            if(response.getStatusCode().value() != HttpStatus.NO_CONTENT.value()){
                log.error("메시지 전송 이후 에러 발생");
                return false;
            }

        } catch (Exception e) {
            log.error("에러 발생 :: " + e);
            return false;
        }

        return true;
    }
}

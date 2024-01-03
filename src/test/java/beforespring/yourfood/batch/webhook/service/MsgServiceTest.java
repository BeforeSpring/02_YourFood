package beforespring.yourfood.batch.webhook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MsgServiceTest {
    @Autowired
    private MsgService msgService;

    @Test
    public void 디스코드_메시지_테스트() {
        // given
        msgService.sendMsg();
    }
}
package beforespring.yourfood.batch.webhook.service;

import beforespring.yourfood.batch.webhook.domain.DiscordWebhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
@Slf4j
public class MsgService {
    @Value("${discord.webhook.url}")
    String webhookUrl;

    public void sendMsg() {
        DiscordWebhook webhook = new DiscordWebhook(webhookUrl);

        webhook.setUsername("LunchHere");
//        webhook.setTts(true); // 음성 출력여부
        webhook.setAvatarUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN9lF93jsUSQ2J5jX4f4OcOvJf4I37mCdrfg&usqp=CAU");
        webhook.setContent("Your LunchHere! 오늘의 점심 추천 맛집은~");
        webhook.addEmbed(
            new DiscordWebhook.EmbedObject()
                .setAuthor("Subway 00점♫", "https://www.subway.co.kr/", "https://yt3.googleusercontent.com/GlB3mCrGfVlntfG1XyRftbaW3SVoNdbWOJydxN2ZPsM8urc8hciWxn8COMQ8w8U7pSK36dqyat0=s900-c-k-c0x00ffffff-no-rj")
                .setDescription("신선함이 가득, 즐거움이 가득!")
                .setColor(Color.BLUE)
                .addField(":egg: 에그마요", "부드러운 달걀과 고소한 마요네즈가 만나 더 부드러운 스테디셀러\n추천 소스 : 랜치, 스위트 칠리", false)
                .addField(":poultry_leg:이탈리안 비엠티", "페퍼로니, 살라미 그리고 햄이 만들어내는 최상의 조화! 전세계가 사랑하는 써브웨이의 베스트셀러! Biggest Meatiest Tastiest, its’ B.M.T. 추천 소스 : 스위트 어니언, 랜치", false)
                .addField(":bacon: 비엘티", "오리지널 아메리칸 스타일 베이컨의 풍미와 바삭함 그대로~ 추천 소스 : 마요네즈, NEW 사우스웨스트 치폴레", true)
                .setFooter("언제나 당신을 위한 맛집과 함께 돌아올게요, Enjoy your LunchHere :)", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN9lF93jsUSQ2J5jX4f4OcOvJf4I37mCdrfg&usqp=CAU")
        );
        webhook.addEmbed(
            new DiscordWebhook.EmbedObject()
                .setAuthor("Webhook Information", "", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkkqaddbEgITDj4ScVsIi1wRND2Z4g1nUm_w&usqp=CAU")
                .setColor(Color.getColor("6815584"))
                .addField("우리 팀의 Webhook URL", "https://discord.com/api/webhooks/1169807718605930598/WaCaSpF-xltA2IlsSbK5IjM-ex_MK8PisMvGp_6J51vlsTguA-skIqUc9BlfPH_YyKPy", false)
                .addField("get decimal color", "https://html-color.org/67ff60 , Discord Webhook color use 'Decimal' value, search your color in this site ex) #67ff60 > 6815584", false)
                .addField("discord-webhooks-guide", "https://birdie0.github.io/discord-webhooks-guide/index.html", false)
        );
        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

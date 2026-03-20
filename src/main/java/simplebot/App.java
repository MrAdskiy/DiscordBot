package simplebot;

import config.BotConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Bot bot = new Bot();
        String token = BotConfig.getBotToken();

        JDA jda = JDABuilder.createDefault(token)
                .addEventListeners(bot)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
//                .enableIntents(
//                        List.of(
//                                GatewayIntent.GUILD_MESSAGES,
//                                GatewayIntent.MESSAGE_CONTENT
//                        )
//                )
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.watching("Бдит"))
                .build();

        jda.awaitReady();
        System.out.println("Бот успешно запущен!");
    }
}

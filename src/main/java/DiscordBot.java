import config.BotConfig;
import listeners.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.List;

public class DiscordBot {
    private static JDA jda;

    public static void main(String[] args) throws InterruptedException {
        String borToken = BotConfig.getBotToken();
        var intents = List.of(GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT);

        try {
            jda = JDABuilder.createLight(borToken)
                    .addEventListeners(new CommandListener())
                    .enableIntents(intents)
                    .build();
        } catch (Exception e) {
            System.out.println("Error starting bot: " + e);
        }

        jda.awaitReady();
        System.out.println("Бот запущен");

        registerSlashCommands();
    }

    private static void registerSlashCommands () {
        jda.updateCommands()
                .addCommands(
                        Commands.slash("ping", "ping")
                ).queue();
    }
}

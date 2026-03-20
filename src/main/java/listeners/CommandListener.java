package listeners;

import commands.stringcommands.ApodCommand;
import commands.stringcommands.StringCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class CommandListener extends ListenerAdapter {
    private HashMap<String, StringCommand> stringCommands = new HashMap<>();

    public CommandListener() {
        stringCommands.put("!apod", new ApodCommand());
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String command = event.getMessage().getContentRaw().trim();
        StringCommand stringCommand = stringCommands.getOrDefault(command, null);

        if (stringCommand != null) {
            stringCommand.execute(event);
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if ("ping".equals(event.getName())) {
            event.reply("pong! 🤫 " + event.getJDA().getGatewayPing() + "ms")
                    .setEphemeral(true)
                    .queue();
        }
    }
}

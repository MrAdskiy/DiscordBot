package commands.stringcommands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.util.EventListener;

public interface StringCommand {
    void execute(MessageReceivedEvent event);
}

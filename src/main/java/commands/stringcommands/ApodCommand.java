package commands.stringcommands;

import api.NasaApi;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import utils.FileManager;

import java.io.IOException;

public class ApodCommand implements StringCommand {
    private NasaApi api = new NasaApi();

    @Override
    public void execute(MessageReceivedEvent event) {
        String apodResponse = "";
        MessageChannel channel = event.getChannel();

        try {
            apodResponse = api.getAPOD(1);
        } catch (InterruptedException | IOException e) {
            if (channel.canTalk()) {
                channel.sendMessage(event.getAuthor().getAsMention() + "\n" + e.getMessage())
                        .queue();
                return;
            }
        }

        var answers = FileManager.parseApodResponse(apodResponse);
        if (answers == null) return;
        if (answers.size() == 1 && channel.canTalk()) {
            channel.sendMessage(event.getAuthor().getAsMention() + " " + answers.get("error"))
                    .queue();
            return;
        }
        if (channel.canTalk()) {
            channel.sendMessage(
                    event.getAuthor().getAsMention() + "\n"
                            + answers.getOrDefault("copyright", "") + " " + answers.getOrDefault("date", "") + "\n"
                            + answers.getOrDefault("title", "") + "\n"
                            + answers.getOrDefault("explanation", "") + "\n"
                            + answers.getOrDefault("url", "")
            ).queue();
        }
    }
}

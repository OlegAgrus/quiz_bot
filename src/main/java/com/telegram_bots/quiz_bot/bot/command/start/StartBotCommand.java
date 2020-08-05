package com.telegram_bots.quiz_bot.bot.command.start;

import com.telegram_bots.quiz_bot.bot.command.AbstractBotCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

@Component
public class StartBotCommand extends AbstractBotCommand {

    public static final String COMMAND_KEY = "/start";

    public static final String MESSAGE = "Hello dodik, try to use '/hello' command";

    @Override
    public String getCommandKey() {
        return COMMAND_KEY;
    }

    @Override
    public BotApiMethod<? extends Serializable> processUpdate(Update update, String[] args) {
        sendMessage(createTextMessage(update, "..."));
        return createTextMessage(update, MESSAGE);
    }

}

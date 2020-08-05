package com.telegram_bots.quiz_bot.bot.command.incorrect_command;

import com.telegram_bots.quiz_bot.bot.command.AbstractBotCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

@Component
public class IncorrectCommandBotCommand extends AbstractBotCommand {

    public static final String BOT_COMMAND_INCORRECT_COMMAND = "_IncorrectBotCommand";

    public static final String MESSAGE = "No such command, іди нахуй";

    @Override
    public String getCommandKey() {
        return BOT_COMMAND_INCORRECT_COMMAND;
    }

    @Override
    public BotApiMethod<? extends Serializable> processUpdate(Update update, String[] args) {
        return createTextMessage(update, MESSAGE);
    }

}

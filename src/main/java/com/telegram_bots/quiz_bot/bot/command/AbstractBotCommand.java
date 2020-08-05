package com.telegram_bots.quiz_bot.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractBotCommand implements BotCommand {

    private Logger logger = LoggerFactory.getLogger(AbstractBotCommand.class);

    private AbsSender absSender;

    @Override
    public void saveAbsSender(AbsSender absSender) {
        this.absSender = absSender;
    }

    @Override
    public void executeCommand(Update update) {
        String[] args = parseArgs(update);

        logger.debug("Execution of command: " + getCommandKey() + ", with args: " + Arrays.toString(args));

        BotApiMethod<? extends Serializable> botApiMethod = processUpdate(update, args);
        sendMessage(botApiMethod);

        logger.debug("Command executed");
    }

    public void sendMessage(BotApiMethod<? extends Serializable> botApiMethod) {
        if (botApiMethod != null) {
            try {
                absSender.execute(botApiMethod);
            } catch (TelegramApiException e) {
                logger.warn("Execute method failed. " + e.getMessage());
            }
        }
    }

    public SendMessage createTextMessage(Update update, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(message);
        return sendMessage;
    }

    private String[] parseArgs(Update update) {
        return Stream
                .of(update.getMessage().getText().split(" "))
                .skip(1)
                .toArray(String[]::new);
    }

    public abstract BotApiMethod<? extends Serializable> processUpdate(Update update, String[] args);

}

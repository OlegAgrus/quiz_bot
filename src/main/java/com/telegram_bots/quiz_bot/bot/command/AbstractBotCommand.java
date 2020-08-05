package com.telegram_bots.quiz_bot.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

public abstract class AbstractBotCommand implements BotCommand {

    private Logger logger = LoggerFactory.getLogger(AbstractBotCommand.class);

    @Override
    public void executeCommand(Update update, AbsSender absSender) {
        logger.debug("Execution of command: " + getCommandKey());

        BotApiMethod<? extends Serializable> botApiMethod = processUpdate(update);
        if (botApiMethod != null) {
            try {
                absSender.execute(botApiMethod);
            } catch (TelegramApiException e) {
                logger.warn("Execute method failed. " + e.getMessage());
            }
        }

        logger.debug("Command executed");
    }

    public SendMessage createTextMessage(Update update, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(message);
        return sendMessage;
    }

    public abstract BotApiMethod<? extends Serializable> processUpdate(Update update);

}

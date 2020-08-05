package com.telegram_bots.quiz_bot.bot.command;

import com.telegram_bots.quiz_bot.bot.TelegramInfoProviderBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public interface BotCommand {

    String getCommandKey();

    @Autowired
    default void registerYourself(TelegramInfoProviderBot telegramInfoProviderBot) {
        telegramInfoProviderBot.registerCommand(this);
        saveAbsSender(telegramInfoProviderBot);
    }

    void saveAbsSender(AbsSender absSender);

    void executeCommand(Update update);

}

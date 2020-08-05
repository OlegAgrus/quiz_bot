package com.telegram_bots.quiz_bot.bot;

import com.telegram_bots.quiz_bot.bot.command.BotCommand;
import com.telegram_bots.quiz_bot.bot.command.CommandContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class TelegramInfoProviderBot extends TelegramLongPollingBot implements CommandContainer {

    private Logger logger = LoggerFactory.getLogger(TelegramInfoProviderBot.class);

    @Value("${bot.username}")
    private String botUserName;

    @Value("${bot.token}")
    private String botToken;

    private Map<String, BotCommand> commandMap = new HashMap<>();

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @PostConstruct
    public void init() {
        logger.info("Initialized bot with username: " + botUserName);
    }

    @Override
    public void onUpdateReceived(Update update) {
        threadPoolExecutor.execute(() -> {
            if (update.hasMessage()) {
                String message = update.getMessage().getText();

                logger.debug("Update received, Chat: " + update.getMessage().getChatId() + ", User: " + update.getMessage().getChat().getUserName() + ", Message: " + message);

                if (message != null) {
                    if (message.startsWith(BotConstants.COMMAND_PREFIX)) {
                        onCommandReceived(update, message);
                    }
                    else {
                        onNonCommandReceived(update, message);
                    }
                }
            }
            else {
                logger.warn("Update has no message!");
            }
        });
    }

    private void onCommandReceived(Update update, String message) {
        String commandKey = message.split(" ")[0];
        Optional
            .ofNullable(commandMap.get(commandKey))
            .orElse(commandMap.get(BotConstants.CURRENT_INCORRECT_BOT_COMMAND))
            .executeCommand(update);
    }

    private void onNonCommandReceived(Update update, String message) {
        sendMsg(update.getMessage().getChatId().toString(), "Хулі ти мені пишеш");
    }

    private void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void registerCommand(BotCommand command) {
        logger.debug("Command registered: " + command.getCommandKey());
        commandMap.put(command.getCommandKey(), command);
    }

}

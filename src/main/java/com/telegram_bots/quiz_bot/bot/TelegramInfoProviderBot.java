package com.telegram_bots.quiz_bot.bot;

import com.telegram_bots.quiz_bot.bot.command.BotCommand;
import com.telegram_bots.quiz_bot.bot.command.CommandContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class TelegramInfoProviderBot extends TelegramLongPollingBot implements CommandContainer {

    @Value("${bot.username}")
    private String botUserName;

    @Value("${bot.token}")
    private String botToken;

    private Map<String, BotCommand> commandMap = new HashMap<>();

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @PostConstruct
    public void init() {
        System.out.println("Initialized bot with username: " + botUserName + " and token: " + botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        threadPoolExecutor.execute(() -> {
            if (update.hasMessage()) {
                String message = update.getMessage().getText();
                System.out.println("Thread: " + Thread.currentThread().getName() + " " + Thread.currentThread().getId() + " Chat: " + update.getMessage().getChatId() + " User: " + update.getMessage().getChat().getUserName() + " Message recieved: " + message);

                if (message.startsWith(BotConstants.COMMAND_PREFIX)) {
                    onCommandReceived(update, message);
                }
                else {
                    onNonCommandReceived(update, message);
                }
            }
        });
    }

    private void onCommandReceived(Update update, String message) {
        String commandKey = message.split(" ")[0];
        Optional
            .ofNullable(commandMap.get(commandKey))
            .orElse(commandMap.get(BotConstants.CURRENT_INCORRECT_BOT_COMMAND))
            .executeCommand(update, this);
    }

    private void onNonCommandReceived(Update update, String message) {
        sendMsg(update.getMessage().getChatId().toString(), "Хулі ти мені пишеш");
    }

    private void executeMethod(BotApiMethod<? extends Serializable> botApiMethod) {
        try {
            execute(botApiMethod);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
        commandMap.put(command.getCommandKey(), command);
    }
}

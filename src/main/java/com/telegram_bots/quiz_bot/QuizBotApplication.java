package com.telegram_bots.quiz_bot;

import com.telegram_bots.quiz_bot.bot.TelegramInfoProviderBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
public class QuizBotApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ThreadPoolExecutor telegramBotThreadPool() {
		int threadCount = 10;
		return (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
	}

	public static void main(String[] args) {
		ApiContextInitializer.init();
		ConfigurableApplicationContext context = SpringApplication.run(QuizBotApplication.class, args);

		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		TelegramInfoProviderBot telegramInfoProviderBot = context.getBean(TelegramInfoProviderBot.class);

		try {
			telegramBotsApi.registerBot(telegramInfoProviderBot);
		} catch (TelegramApiRequestException e) {
			e.printStackTrace();
		}
	}
}

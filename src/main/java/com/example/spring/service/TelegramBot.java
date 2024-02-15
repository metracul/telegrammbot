package com.example.spring.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;



@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botname;
    @Value("${bot.token}")
    private String bottoken;

    private static final Logger log = Logger.getLogger(TelegramBot.class);

    @Override
    public void onUpdateReceived(Update update) {
        var originMessage = update.getMessage();
        log.debug(originMessage.getText());
//        if(update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
//            long chatId = update.getMessage().getChatId();
//            switch (messageText) {
//                case "/start":
//                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
//            }
//        }

    }

    @Override
    public String getBotUsername() {
        return botname;
    }

    @Override
    public String getBotToken(){
        return bottoken;
    }
    private void startCommandReceived(long chatId, String name){
        String answer = "Hi, " + name +", nice to meet you!";
        sendMessage(chatId, answer);
    }
    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
    }
}

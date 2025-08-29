package graf;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AdvancedForwarderBot extends TelegramLongPollingBot {
    private static final String BOT_TOKEN = "8031185838:AAHnFYqPEiOVOwEHO_dF8PGSU8gIsvuLJr0";
    private static final Long SOURCE_CHAT_ID = -1002826245278L;
    private static final Long TARGET_CHAT_ID = -1002518739586L;

    @Override
    public String getBotUsername() {
        return "AdvancedForwarderBot";
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            if (message.getChatId().equals(SOURCE_CHAT_ID)) {
                forwardToTarget(message);
            }
            else if (message.getChatId().equals(TARGET_CHAT_ID)) {
                forwardToSource(message);
            }
        }
    }

    private void forwardToTarget(Message originalMessage) {
        try {
            // Вариант 1: Простая пересылка (сохраняет оригинального отправителя)
            ForwardMessage forward = new ForwardMessage();
            forward.setChatId(TARGET_CHAT_ID);
            forward.setFromChatId(originalMessage.getChatId().toString());
            forward.setMessageId(originalMessage.getMessageId());
            execute(forward);

            // Вариант 2: Копирование с упоминанием автора (лучше для реакций ботов)
            if (originalMessage.hasText()) {
                String authorName = getAuthorName(originalMessage);
                SendMessage copy = new SendMessage();
                copy.setChatId(TARGET_CHAT_ID.toString());
                copy.setText(authorName + ":\n" + originalMessage.getText());
                execute(copy);
            }

        } catch (TelegramApiException e) {
            System.err.println("Forward error: " + e.getMessage());
        }
    }

    private void forwardToSource(Message originalMessage) {
        // Аналогичная логика для обратного направления
    }

    private String getAuthorName(Message message) {
        User user = message.getFrom();
        if (user.getUserName() != null) {
            return "@" + user.getUserName();
        }
        return user.getFirstName() + (user.getLastName() != null ? " " + user.getLastName() : "");
    }


}
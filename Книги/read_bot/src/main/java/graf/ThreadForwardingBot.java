package graf;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadForwardingBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(ThreadForwardingBot.class);

    private final String botToken;
    private final Long sourceChatId;       // ID исходной группы
    private final Integer sourceThreadId;  // ID треда в группе
    private final Long destinationChatId;  // ID целевого чата

    public ThreadForwardingBot(String botToken, Long sourceChatId, Integer sourceThreadId, Long destinationChatId) {
        this.botToken = botToken;
        this.sourceChatId = sourceChatId;
        this.sourceThreadId = sourceThreadId;
        this.destinationChatId = destinationChatId;
    }

    @Override
    public String getBotUsername() {
        return "ThreadForwardingBot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            // Если сообщение из исходного треда → пересылаем в целевой чат
            if (message.getChatId().equals(sourceChatId)
                    && message.isTopicMessage()
                    && message.getMessageThreadId().equals(sourceThreadId)) {
                forwardToDestination(message);
            }
            // Если сообщение из целевого чата → пересылаем обратно в тред
            else if (message.getChatId().equals(destinationChatId)) {
                forwardBackToThread(message);
            }
        }
    }

    private void forwardToDestination(Message message) {
        ForwardMessage forward = new ForwardMessage();
        forward.setChatId(destinationChatId);
        forward.setFromChatId(sourceChatId.toString());
        forward.setMessageId(message.getMessageId());

        try {
            execute(forward);
            logger.info("Сообщение из треда переслано в чат: " + message.getMessageId());
        } catch (TelegramApiException e) {
            logger.error("Ошибка пересылки в чат: " + e.getMessage());
        }
    }

    private void forwardBackToThread(Message message) {
        SendMessage reply = new SendMessage();
        reply.setChatId(sourceChatId.toString());
        reply.setMessageThreadId(sourceThreadId);
        reply.setText(message.getText() != null ? message.getText() : "📎 Медиа-сообщение");

        try {
            execute(reply);
            logger.info("Сообщение из чата переслано в тред: " + message.getMessageId());
        } catch (TelegramApiException e) {
            logger.error("Ошибка пересылки в тред: " + e.getMessage());
        }
    }
}

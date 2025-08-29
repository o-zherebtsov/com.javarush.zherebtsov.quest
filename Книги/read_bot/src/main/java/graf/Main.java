package graf;

import graf.ThreadForwardingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//
//            // Настройки бота
//            String botToken = "8031185838:AAHnFYqPEiOVOwEHO_dF8PGSU8gIsvuLJr0";
//            Long sourceChatId = -1002826245278L;     // ID группы с тредом
//            Integer sourceThreadId = 2;           // ID треда
//            Long destinationChatId = -1002518739586L; // ID целевого чата
//
//            // Регистрация бота
//            botsApi.registerBot(new ThreadForwardingBot(
//                    botToken,
//                    sourceChatId,
//                    sourceThreadId,
//                    destinationChatId
//            ));
//
//            System.out.println("Бот запущен!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

            try {
                new TelegramBotsApi(DefaultBotSession.class).registerBot(new AdvancedForwarderBot());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

    }
}
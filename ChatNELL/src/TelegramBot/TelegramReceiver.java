/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramBot;

import Model.Login;
import Model.MessageInterface;
import Model.User;
import Senders.TelegramSender;
import java.time.LocalTime;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardHide;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 *
 * @author wesley
 */
public class TelegramReceiver extends TelegramLongPollingBot{
    @Override
    public String getBotToken() {
        //return BotConfig.MARKET_TOKEN;
        return Login.TOKEN;
    }

    @Override
    public String getBotUsername() {
        return Login.USER;
    }
    
    @Override
    public void onUpdateReceived(Update update) {
        User user = new User(update.getMessage().getFrom().getFirstName(), 
                update.getMessage().getChatId().toString(), 
                MessageInterface.TELEGRAM);
        
        
        
        
        Message message = update.getMessage();
        System.out.println("\n"+LocalTime.now()+":[SweetMarket] "+user.getName()+": "+message.getText());
        
        if(message.isCommand()){
            switch(message.getText()){
                case Command.START:
                    break;
                case Command.HELP: 
                    help(user);
                    break;
            }
        }else{
            //TODO: Identificar o usuário
            
            //TODO:Armazenar Mensagem
            
            //Resposta somente para teste:
            Senders.TelegramSender ts = new TelegramSender();
            ts.send(user, "Hi "+user.getName()+"!!!");
        }
        
    }
    
    private void help(User user){
        String text = "I am the ChatNELL! A bot that answer questions using NELL's Knowledge Base.\n"
                + "My developers are upgrading me frequently. So, if I wont answer one question to you today, maybe I'll can do it in future.\n\n"
                + "If you want to see more about NELL's knowledge base, go to: http://rtw.ml.cmu.edu .";
        
        Senders.TelegramSender ts = new TelegramSender();
            ts.send(user, text);
        
    }
    
}

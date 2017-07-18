/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatnell;

/**
 *
 * @author WesleyW
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Thread t = new Thread(new QAMonitor.QAMonitor());
        t.start();
        TelegramBot.BotRunner.main(args);
        
        
    }
    
}

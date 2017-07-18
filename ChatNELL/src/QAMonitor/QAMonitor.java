/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QAMonitor;

import APIai.model.Response;
import Model.AnswerOrder;
import Senders.TelegramSender;
import APIai.connection.ApiAiConnector;
import APIai.connection.Intent;
import WebCrawlers.Zenodotus;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author WesleyW
 */
public class QAMonitor implements Runnable {

    AnswerOrder answer_order;
    
    @Override
    public void run() {

        while (true) {

            //Busca a próxima mensagem não respondida
            answer_order = ChattingManager.ChattingManager.nextMessage();

            if (answer_order != null) {
                String question = answer_order.getMessage().getQuestion();
                String answer = "";
                Response response = ApiAiConnector.getResponse(question);
        
        String intent = "";
        if(response.getResult().getMetadata().getIntent_name() != null){
            intent = response.getResult().getMetadata().getIntent_name();
        }
        if(intent.equals(Intent.TO_KNOW_ABOUT_SOMEONE)){
            String category_instance = response.getResult().getParameters().getCategoryInstance();
            
            //Enviando uma mensagem prévia
            Model.User user = ChattingManager.ChattingManager.getUser(answer_order.getId(), answer_order.getMessage_interface());
            Senders.TelegramSender ts = new TelegramSender();
            ts.send(user, "Wait, I am thinking about "+category_instance+"...");
           
            //Chamar know about something
            answer = knowAboutSomething(category_instance);
            
        }else if(intent.equals(Intent.INSTANCE_IS_CATEGORY)){
            String category_instance = response.getResult().getParameters().getCategoryInstance();
            String category_name = response.getResult().getParameters().getCategory_name();
            
            //Enviando uma mensagem prévia
            Model.User user = ChattingManager.ChattingManager.getUser(answer_order.getId(), answer_order.getMessage_interface());
            Senders.TelegramSender ts = new TelegramSender();
            ts.send(user, "Wait, I am thinking if "+category_instance+" is "+category_name+"...");
            
            answer = instanceIsCategory(category_instance,category_name);
            
        }else{
            answer = response.getResult().getFulfillment().getSpeech();
           
        }
               //answer = "Hi " + answer_order.getUser_name() + "!";

                answer_order.getMessage().setAnswer(answer, System.currentTimeMillis());

                //Envia a resposta através do TelegramSender
                Senders.TelegramSender ts = new TelegramSender();
                ts.send(answer_order);
            }else{
                try{
                    Random r = new Random(System.currentTimeMillis());
                    Thread.sleep(1+r.nextInt(3000));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

    }
    
    private String instanceIsCategory(String instance, String category){
        String answer = "";
        
        if(instance.length()>1 && category.length()>1){
        
        JSONArray jcategories = Zenodotus.searchCategory(category);
        
        if(!jcategories.isEmpty()){
            JSONObject jcategory = (JSONObject) jcategories.get(0);
            String category_predicate = (String) jcategory.get("category_name");
            
            JSONArray jInstances = Zenodotus.searchCategoryInstance(category_predicate, instance);
            if(!jInstances.isEmpty()){
                answer = "Yes! "+instance+" is "+category+"!";
            }else{
                answer = "No, I don't belive that "+instance+" is "+category+".";
            } 
        } else{
            answer = "Sorry, I don't know what is "+category+".";
        }
        }else{
            answer = "I'm sorry, I could not understand some terms you used in the question.\nCan you ask in other words?"; 
        }

        return answer;
    }
    
    
    private String knowAboutSomething(String keyword){
        String answer = "";
            JSONArray jCategory_instances = Zenodotus.searchCategoryInstance(keyword);
            JSONObject jCategory_instance = new JSONObject();
            
            if(jCategory_instances.size()>0){
                //Primeira categoria
                
                JSONObject jCategory = new JSONObject();
                
                //for(int i =0;i<jCategory_instances.size();i++){
                    //jCategory_instance = (JSONObject) jCategory_instances.get(i);
                    jCategory_instance = (JSONObject) jCategory_instances.get(0);
                
                if(jCategory_instance.containsKey("instance_category")){
                jCategory = (JSONObject) jCategory_instance.get("instance_category");
                //Nome da categoria
                String category_name = (String) jCategory.get("category_name");
                
                
                String begin = category_name.substring(0, 1);
                if(begin.equalsIgnoreCase("a") ||
                        begin.equalsIgnoreCase("e") ||
                        begin.equalsIgnoreCase("i") ||
                        begin.equalsIgnoreCase("o") ||
                        begin.equalsIgnoreCase("u")){
                    answer = keyword +" is an "+category_name+"!";
                }else{
                    answer = keyword +" is a "+category_name+"!";
                }
                
                }
                //}

                //Prologo da categoria
                if(jCategory.containsKey("category_prologue")){
                    answer = answer+"\n"+(String) jCategory.get("category_prologue");
                }
                
                //Link da categoria
                if(jCategory_instance.containsKey("instance_literal_strings")){
                    JSONArray jInstance_literal_strings = (JSONArray) jCategory_instance.get("instance_literal_strings");
                    if(jInstance_literal_strings.size()>0){
                        JSONObject jInstance_literal_string = (JSONObject)jInstance_literal_strings.get(0);
                        answer = answer+"\n"+(String) jInstance_literal_string.get("link");
                    }
                }
                
            }else{
                answer = "Sorry, "+answer_order.getUser_name()+". For now I don't know "+keyword +".";
            }
            return answer;
    }

}

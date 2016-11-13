/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author wesley
 */
public class User {
    private String name;
    private String id;
    private MessageInterface message_interface;
    
    public User(String name, String id, MessageInterface message_interface){
        this.name = name;
        this.id = id;
        this.message_interface = message_interface;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MessageInterface getMessage_interface() {
        return message_interface;
    }

    public void setMessage_interface(MessageInterface message_interface) {
        this.message_interface = message_interface;
    }
    
    
}

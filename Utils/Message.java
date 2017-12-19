/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author royetju
 */
public class Message{
    private MessageType type;
    
    public Message(MessageType t){
        type = t;
    }
    public MessageType getType(){
        return type;
    }
    
}





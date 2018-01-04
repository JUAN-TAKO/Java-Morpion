/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author JUAN
 */

//Cette classe permet de contenir les infos concernant les index (tableaux etc) d'un message
public class MessageIndex extends Message{
    
    private int index;
    public MessageIndex(MessageType t, int i) {
        super(t);
        index = i;
    }
    public int getIndex(){
        return index;
    }
    
}

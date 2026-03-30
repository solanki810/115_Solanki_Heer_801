/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import jakarta.ejb.Stateless;
import jakarta.ejb.LocalBean;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

/**
 *
 * @author riya vesuwala
 */
@Stateless
@LocalBean
public class SubscriberMDB3 implements MessageListener{

    @Override
    public void onMessage(Message message) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        try {
            TextMessage txt = (TextMessage) message;
            System.out.println("MDB2 Received: " + txt.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

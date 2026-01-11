package com.architecture.mdb;

import com.architecture.model.AuditMessage;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "AuditTopic")
})
public class EmailNotifierMdb implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                AuditMessage msg = (AuditMessage) ((ObjectMessage) message).getObject();
                
                
                if ("Manufacturer".equals(msg.getEntityType())) { //отправка писем только при изменении конкретной сущности
                    sendEmail("admin@example.com", "Data Change!", 
                        "Manufacturer details: " + msg.getDetails());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendEmail(String to, String subject, String body) {
        System.out.println(">>> EMAIL SENT to " + to + ": [" + subject + "] " + body);
    }
}

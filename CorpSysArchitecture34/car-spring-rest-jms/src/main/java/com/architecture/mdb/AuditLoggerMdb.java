package com.architecture.mdb;

import com.architecture.model.ActionLog;
import com.architecture.model.AuditMessage;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "AuditTopic")
})
public class AuditLoggerMdb implements MessageListener {

    @PersistenceContext(unitName = "CarPU")
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                AuditMessage msg = (AuditMessage) ((ObjectMessage) message).getObject();
                
                ActionLog log = new ActionLog(
                    msg.getActionType(), 
                    msg.getEntityType(), 
                    msg.getEntityId(), 
                    msg.getDetails()
                );
                
                em.persist(log);
                System.out.println("MDB LOG: " + msg.getActionType() + " type " + msg.getEntityType());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

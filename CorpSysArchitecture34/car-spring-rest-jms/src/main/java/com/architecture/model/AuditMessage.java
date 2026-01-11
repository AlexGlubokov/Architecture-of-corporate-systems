package com.architecture.model;
import java.io.Serializable;

// Структура сообщений JMS 
public class AuditMessage implements Serializable {
    private static final long serialVersionUID = 1L;
	private String actionType; 
    private String entityType; 
    private Integer entityId;
    private String details;

    public AuditMessage() {}
    public AuditMessage(String action, String entity, Integer id, String details) {
        this.actionType = action;
        this.entityType = entity;
        this.entityId = id;
        this.details = details;
    }

    public String getActionType() { return actionType; }
    public String getEntityType() { return entityType; }
    public Integer getEntityId() { return entityId; }
    public String getDetails() { return details; }
}

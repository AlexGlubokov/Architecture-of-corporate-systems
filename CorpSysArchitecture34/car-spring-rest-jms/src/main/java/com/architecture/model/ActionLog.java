package com.architecture.model;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Action_Log")
public class ActionLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="action_type") private String actionType;
    @Column(name="entity_type") private String entityType;
    @Column(name="entity_id") private Integer entityId;
    private String details;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", insertable=false, updatable=false)
    private Date createdAt;

    public ActionLog() {}
    public ActionLog(String action, String entity, Integer id, String det) {
        this.actionType = action;
        this.entityType = entity;
        this.entityId = id;
        this.details = det;
    }
}

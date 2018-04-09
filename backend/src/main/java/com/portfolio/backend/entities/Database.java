package com.portfolio.backend.entities;

import javax.persistence.*;
import java.sql.Blob;


@Entity
@Table(name = "spring_session_attributes")
public class Database {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = (long) -1;

    private String sessionId;
    private String attributeName;
    @Column()
    private Blob attributeBytes;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public Blob getAttributeBytes() {
        return attributeBytes;
    }

    public void setAttributeBytes(Blob attributeBytes) {
        this.attributeBytes = attributeBytes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.work.fb.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FbMessage.
 */
@Entity
@Table(name = "fb_message")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FbMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "reply_message")
    private String replyMessage;

    @Column(name = "reply_status")
    private String replyStatus;

    @Column(name = "created_on")
    private String createdOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public FbMessage userid(String userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessageId() {
        return messageId;
    }

    public FbMessage messageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public FbMessage replyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
        return this;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    public String getReplyStatus() {
        return replyStatus;
    }

    public FbMessage replyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
        return this;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public FbMessage createdOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FbMessage fbMessage = (FbMessage) o;
        if (fbMessage.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, fbMessage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FbMessage{" +
            "id=" + id +
            ", userid='" + userid + "'" +
            ", messageId='" + messageId + "'" +
            ", replyMessage='" + replyMessage + "'" +
            ", replyStatus='" + replyStatus + "'" +
            ", createdOn='" + createdOn + "'" +
            '}';
    }
}

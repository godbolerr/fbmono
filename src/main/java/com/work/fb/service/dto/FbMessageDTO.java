package com.work.fb.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the FbMessage entity.
 */
public class FbMessageDTO implements Serializable {

    private Long id;

    private String userid;

    private String messageId;

    private String replyMessage;

    private String replyStatus;

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

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }
    public String getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(String replyStatus) {
        this.replyStatus = replyStatus;
    }
    public String getCreatedOn() {
        return createdOn;
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

        FbMessageDTO fbMessageDTO = (FbMessageDTO) o;

        if ( ! Objects.equals(id, fbMessageDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FbMessageDTO{" +
            "id=" + id +
            ", userid='" + userid + "'" +
            ", messageId='" + messageId + "'" +
            ", replyMessage='" + replyMessage + "'" +
            ", replyStatus='" + replyStatus + "'" +
            ", createdOn='" + createdOn + "'" +
            '}';
    }
}

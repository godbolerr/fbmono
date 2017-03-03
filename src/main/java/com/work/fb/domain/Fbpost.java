package com.work.fb.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Fbpost.
 */
@Entity
@Table(name = "fbpost")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Fbpost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "caption")
    private String caption;

    @Column(name = "place")
    private String place;

    @Column(name = "created_on")
    private String createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "privacy")
    private String privacy;

    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public Fbpost userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public Fbpost url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public Fbpost description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Fbpost name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public Fbpost caption(String caption) {
        this.caption = caption;
        return this;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPlace() {
        return place;
    }

    public Fbpost place(String place) {
        this.place = place;
        return this;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public Fbpost createdOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Fbpost createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getObjectId() {
        return objectId;
    }

    public Fbpost objectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getPrivacy() {
        return privacy;
    }

    public Fbpost privacy(String privacy) {
        this.privacy = privacy;
        return this;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getStatus() {
        return status;
    }

    public Fbpost status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fbpost fbpost = (Fbpost) o;
        if (fbpost.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, fbpost.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Fbpost{" +
            "id=" + id +
            ", userId='" + userId + "'" +
            ", url='" + url + "'" +
            ", description='" + description + "'" +
            ", name='" + name + "'" +
            ", caption='" + caption + "'" +
            ", place='" + place + "'" +
            ", createdOn='" + createdOn + "'" +
            ", createdBy='" + createdBy + "'" +
            ", objectId='" + objectId + "'" +
            ", privacy='" + privacy + "'" +
            ", status='" + status + "'" +
            '}';
    }
}

package com.aeserver.model;

import com.aeserver.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ExternalEvent extends AEvent {

  @ManyToOne
  @JsonView(Views.Summary.class)
  @JsonSerialize(using = Views.ShallowSerializer.class)
  private User user;

  @JsonView(Views.Unrestricted.class)
  private Boolean visible;

  public ExternalEvent() {
  }

  public ExternalEvent(Boolean visible) {
    this.visible = visible;
  }

  public ExternalEvent(long id, String title, Date start, Date end, String description, AEventStatus status, boolean isTicketed, double participationFee, int maxParticipants, Boolean visible) {
    super(id, title, start, end, description, status, isTicketed, participationFee, maxParticipants);
    this.visible = visible;
  }

  public Boolean getVisible() {
    return visible;
  }

  public void setVisible(Boolean visible) {
    this.visible = visible;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}

package com.aeserver.model;


import com.aeserver.view.Views;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;

public class AEvent {
  enum AEventStatus {
    DRAFT,
    PUBLISHED,
    CANCELED
  }

  public static int idCounter = 20001;

  @JsonView(Views.Summary.class)
  private int id;

  @JsonView(Views.Summary.class)
  private String title;
  private Date start;
  private Date end;
  private String description;

  @JsonView(Views.Summary.class)
  private AEventStatus status;

  private boolean isTicketed;
  private double participationFee;
  private int maxParticipants;

  public AEvent() {

  }

  public AEvent(int id, String title, Date start, Date end, String description, AEventStatus status, boolean isTicketed, double participationFee, int maxParticipants) {
    this.id = id;
    this.title = title;
    this.start = start;
    this.end = end;
    this.description = description;
    this.status = status;
    this.isTicketed = isTicketed;
    this.participationFee = participationFee;
    this.maxParticipants = maxParticipants;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AEventStatus getStatus() {
    return status;
  }

  public void setStatus(AEventStatus status) {
    this.status = status;
  }

  public boolean isTicketed() {
    return isTicketed;
  }

  public void setTicketed(boolean ticketed) {
    isTicketed = ticketed;
  }

  public double getParticipationFee() {
    return participationFee;
  }

  public void setParticipationFee(double participationFee) {
    this.participationFee = participationFee;
  }

  public int getMaxParticipants() {
    return maxParticipants;
  }

  public void setMaxParticipants(int maxParticipants) {
    this.maxParticipants = maxParticipants;
  }

  public static AEvent createRandomAEvent() {

     int id = AEvent.idCounter++;
     String title = "test-event";
     Date start = new Date();
     Date end = new Date();
     String description = "test";
     AEventStatus status = AEventStatus.PUBLISHED;
     boolean isTicketed = true;
     double participationFee = 20.34;
     int maxParticipants = 40;

    return new AEvent(id, title, start, end, description, status, isTicketed, participationFee, maxParticipants);
  }
}

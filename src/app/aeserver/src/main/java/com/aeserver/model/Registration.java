package com.aeserver.model;

import com.aeserver.repository.interfaces.Identifiable;
import com.aeserver.view.Views;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@SequenceGenerator(name = "registration_id_seq", initialValue = 100000, allocationSize = 100)
public class Registration implements Identifiable {

  @Id
  @JsonView(Views.Shallow.class)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "registration_id_seq")
  private long id;

  @JsonView(Views.Unrestricted.class)
  private String ticketCode;

  @JsonView(Views.Unrestricted.class)
  private boolean paid;

  @JsonView(Views.Shallow.class)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime submissionDate;

  @ManyToOne
  @JsonView({Views.Summary.class})
  @JsonSerialize(using = Views.ShallowSerializer.class)
  private AEvent aEvent;

  public Registration() {
  }

  public Registration(String ticketCode, boolean paid, LocalDateTime submissionDate, AEvent aEvent) {
    this.ticketCode = ticketCode;
    this.paid = paid;
    this.submissionDate = submissionDate;
    this.aEvent = aEvent;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTicketCode() {
    return ticketCode;
  }

  public void setTicketCode(String ticketCode) {
    this.ticketCode = ticketCode;
  }

  public boolean isPaid() {
    return paid;
  }

  public void setPaid(boolean paid) {
    this.paid = paid;
  }

  public LocalDateTime getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(LocalDateTime submissionDate) {
    this.submissionDate = submissionDate;
  }

  public AEvent getAEvent() {
    return aEvent;
  }

  public void setAEvent(AEvent aEvent) {
    this.aEvent = aEvent;
  }

  public static Registration createRandomRegistration(AEvent assocAEvent) {
    String ticketCode = "XCSDFW2FASA34ASF";
    boolean paid = true;
    LocalDateTime submissionDate = LocalDateTime.now();

    return new Registration(ticketCode, paid, submissionDate, assocAEvent);
  }
}

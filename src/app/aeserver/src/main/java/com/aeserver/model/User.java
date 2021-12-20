package com.aeserver.model;

import com.aeserver.repository.interfaces.Identifiable;
import com.aeserver.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "user_id_seq", initialValue = 90001, allocationSize = 100)
public class User implements Identifiable {

  public static long idCounter = 90002;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
  @JsonView(Views.Shallow.class)
  private long id;

  @JsonView(Views.Shallow.class)
  private String name;

  @JsonView(Views.Summary.class)
  private String eMail;

  @JsonView(Views.Summary.class)
  private String hashedPassWord;

  @JsonView(Views.Unrestricted.class)
  private boolean admin;

  @OneToMany(mappedBy = "user")
  @JsonView(Views.Summary.class)
  @JsonSerialize(using = Views.UnrestrictedSerializer.class)
  private List<ExternalEvent> events = new ArrayList<>();

  public User() {
  }

  public User(String name, String eMail, String hashedPassWord, boolean admin) {
    this.id = idCounter++;
    this.name = name;
    this.eMail = eMail;
    this.hashedPassWord = hashedPassWord;
    this.admin = admin;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String geteMail() {
    return eMail;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  public String getHashedPassWord() {
    return hashedPassWord;
  }

  public void setHashedPassWord(String hashedPassWord) {
    this.hashedPassWord = hashedPassWord;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public List<ExternalEvent> getEvents() {
    return events;
  }

  public void addEvents(ExternalEvent event) {
    this.events.add(event);
  }
}

package com.aeserver.model;

public class User {

  public static long idCounter = 90002;

  private long id;
  private String name;
  private String eMail;
  private String hashedPassWord;
  private boolean admin;

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
}

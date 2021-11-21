package com.aeserver.model;

import com.aeserver.repository.interfaces.Identifiable;
import com.aeserver.view.Views;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity()
@NamedQuery(name = "find_all_events", query = "select e from AEvent e")
@SequenceGenerator(name = "event_id_seq", initialValue = 20001, allocationSize = 100)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AEvent implements Identifiable {
  public static enum AEventStatus {
    DRAFT,
    PUBLISHED,
    CANCELED
  }

  public static long idCounter = 20001;

  @JsonView(Views.Summary.class)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_seq")
  private long id;

  @JsonView(Views.Summary.class)
  private String title;
  private Date start;
  private Date end;

  @Column(length = 1000)
  private String description;

  @JsonView(Views.Summary.class)
  @Enumerated(EnumType.STRING)
  private AEventStatus status;

  private boolean isTicketed;
  private double participationFee;
  private int maxParticipants;

  @JsonManagedReference
  @OneToMany(mappedBy = "aEvent")
  private List<Registration> registrations = new ArrayList<>();

  public AEvent() {
  }

  public AEvent(long id, String title, Date start, Date end, String description, AEventStatus status, boolean isTicketed, double participationFee, int maxParticipants) {
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public void setTicketed(boolean isTicketed) {
    this.isTicketed = isTicketed;
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

  public List<Registration> getRegistrations() {
    return registrations;
  }

  public void addRegistration(Registration registration) {
    this.registrations.add(registration);
  }

  public static AEvent createRandomAEvent() {
    long id = AEvent.idCounter++;
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

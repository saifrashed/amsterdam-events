package com.aeserver.controller;

import com.aeserver.exception.PreConditionalFailedException;
import com.aeserver.exception.ResourceNotFoundException;
import com.aeserver.exception.UnAuthorizedException;
import com.aeserver.model.AEvent;
import com.aeserver.model.Registration;
import com.aeserver.model.User;
import com.aeserver.repository.AEventsRepositoryJpa;
import com.aeserver.security.JWToken;
import com.aeserver.view.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Authenticate Controller
 */
@CrossOrigin(origins = "*") //this line
@RestController
@RequestMapping("/api/authenticate")
public class AuthenticateController {

  /**
   * JPA repository
   */
  @Autowired
  AEventsRepositoryJpa eventRepo;


  @Value("${jwt.issuer}")
  private String issuer;

  @Value("${jwt.pass-phrase}")
  private String passPhrase;

  @Value("${jwt.duration-of-validity}")
  private int tokenDurationOfValidity;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody ObjectNode body) {

    String email = body.get("eMail").asText();
    String name = email.substring(0, email.indexOf("@"));
    String password = body.get("passWord").asText();
    boolean admin = false;

    if (!name.equals(password))
      throw new UnAuthorizedException("Cannot authenticate user by email=" + email + " and password=" + password);

    User userObj = new User(name, email, password, admin);

    JWToken jwt = new JWToken(email, userObj.getId(), admin);

    return ResponseEntity.accepted().header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt.encode(issuer, passPhrase, tokenDurationOfValidity)).body(userObj);
  }

}

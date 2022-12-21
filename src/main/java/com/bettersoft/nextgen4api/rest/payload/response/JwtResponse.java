package com.bettersoft.nextgen4api.rest.payload.response;

import com.bettersoft.nextgen4api.model.Rota;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class JwtResponse {
  private String accessToken;
  private String tokenType = "Bearer";
  private Long id;
  private String username;
  private String email;
  private List<String> roles;
  private Set<Rota> routes;

  public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, Set<Rota> routes) {
    this.accessToken = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
    this.routes = routes;
  }
}
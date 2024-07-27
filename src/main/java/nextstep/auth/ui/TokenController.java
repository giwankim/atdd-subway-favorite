package nextstep.auth.ui;

import lombok.RequiredArgsConstructor;
import nextstep.auth.application.TokenService;
import nextstep.auth.application.dto.GithubLoginRequest;
import nextstep.auth.application.dto.TokenRequest;
import nextstep.auth.application.dto.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TokenController {
  private final TokenService tokenService;

  @PostMapping("/login/token")
  public ResponseEntity<TokenResponse> createToken(@RequestBody TokenRequest request) {
    TokenResponse response = tokenService.createToken(request.getEmail(), request.getPassword());
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login/github")
  public ResponseEntity<TokenResponse> loginWithGithub(@RequestBody GithubLoginRequest request) {
    TokenResponse response = tokenService.createTokenFromGithubCode(request.getCode());
    return ResponseEntity.ok(response);
  }
}

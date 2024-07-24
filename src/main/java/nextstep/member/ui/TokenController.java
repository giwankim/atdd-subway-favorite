package nextstep.member.ui;

import lombok.RequiredArgsConstructor;
import nextstep.member.application.TokenService;
import nextstep.member.application.dto.TokenRequest;
import nextstep.member.application.dto.TokenResponse;
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
}

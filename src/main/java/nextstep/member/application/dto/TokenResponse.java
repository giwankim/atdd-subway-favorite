package nextstep.member.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenResponse {
    private String accessToken;

    public TokenResponse() {
    }

    @Builder
    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}

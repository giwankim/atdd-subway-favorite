package nextstep.utils.mock;

import nextstep.auth.AuthenticationException;
import nextstep.auth.token.oauth2.github.GithubAccessTokenResponse;
import nextstep.auth.token.oauth2.github.GithubProfileResponse;

import java.util.Arrays;

public enum  GithubResponses {

    사용자1("aofijeowifjaoief", "access_token_1", "email1@email.com", 26),
    사용자2("fau3nfin93dmn", "access_token_2", "email2@email.com", 27),
    사용자3("afnm93fmdodf", "access_token_3", "email3@email.com", 28),
    사용자4("fm04fndkaladmd", "access_token_4", "email4@email.com", 29);

    private String code;
    private String accessToken;
    private String email;
    private Integer age;

    GithubResponses(String code, String accessToken, String email, Integer age) {
        this.code = code;
        this.accessToken = accessToken;
        this.email = email;
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public static GithubResponses findByCode(String code) {
        return Arrays.stream(values())
                .filter(GithubResponses -> GithubResponses.code.equals(code))
                .findAny()
                .orElseThrow(AuthenticationException::new);
    }

    public static GithubResponses findByAccessToken(String accessToken) {
        return Arrays.stream(values())
                .filter(GithubResponses -> GithubResponses.accessToken.equals(accessToken))
                .findAny()
                .orElseThrow(AuthenticationException::new);
    }

    public GithubAccessTokenResponse toGithubAccessTokenResponse() {
        return new GithubAccessTokenResponse(
                accessToken,
                null,
                null,
                null
        );
    }

    public GithubProfileResponse toGithubProfileResponse() {
        return new GithubProfileResponse(
                email,
                age
        );
    }
}

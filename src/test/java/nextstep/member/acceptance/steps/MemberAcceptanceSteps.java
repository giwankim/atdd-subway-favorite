package nextstep.member.acceptance.steps;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import nextstep.member.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SuppressWarnings("NonAsciiCharacters")
public class MemberAcceptanceSteps {
  private MemberAcceptanceSteps() {}

  public static ExtractableResponse<Response> 회원_생성_요청(String email, String password, Integer age) {
    Map<String, String> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);
    params.put("age", age + "");

    return RestAssured.given()
        .log()
        .all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(params)
        .when()
        .post("/members")
        .then()
        .log()
        .all()
        .extract();
  }

  public static ExtractableResponse<Response> 회원_정보_조회_요청(ExtractableResponse<Response> response) {
    String uri = response.header("Location");

    return RestAssured.given()
        .log()
        .all()
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .when()
        .get(uri)
        .then()
        .log()
        .all()
        .extract();
  }

  public static ExtractableResponse<Response> 회원_정보_수정_요청(
      ExtractableResponse<Response> response, String email, String password, Integer age) {
    String uri = response.header("Location");

    Map<String, String> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);
    params.put("age", age + "");

    return RestAssured.given()
        .log()
        .all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(params)
        .when()
        .put(uri)
        .then()
        .log()
        .all()
        .extract();
  }

  public static ExtractableResponse<Response> 내_정보_조회_요청(String accessToken) {
    return RestAssured.given()
        .log()
        .all()
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .auth()
        .oauth2(accessToken)
        .when()
        .get("/members/me")
        .then()
        .log()
        .all()
        .extract();
  }

  public static ExtractableResponse<Response> 회원_삭제_요청(ExtractableResponse<Response> response) {
    String uri = response.header("Location");
    return RestAssured.given().log().all().when().delete(uri).then().log().all().extract();
  }

  public static void 회원_정보_조회됨(ExtractableResponse<Response> response, String email, int age) {
    assertThat(response.jsonPath().getString("id")).isNotNull();
    assertThat(response.jsonPath().getString("email")).isEqualTo(email);
    assertThat(response.jsonPath().getInt("age")).isEqualTo(age);
  }

  public static void 내_정보_조회됨(ExtractableResponse<Response> response, Member member) {
    assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.jsonPath().getString("email")).isEqualTo(member.getEmail());
  }
}

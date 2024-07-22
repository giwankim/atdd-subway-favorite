package nextstep.favorite.ui;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import nextstep.favorite.application.FavoriteService;
import nextstep.favorite.application.dto.FavoriteRequest;
import nextstep.favorite.application.dto.FavoriteResponse;
import nextstep.member.domain.LoginMember;
import nextstep.member.ui.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FavoriteController {
  private final FavoriteService favoriteService;

  @PostMapping("/favorites")
  public ResponseEntity<Void> createFavorite(
      @RequestBody FavoriteRequest request, @AuthenticationPrincipal LoginMember loginMember) {
    FavoriteResponse favorite = favoriteService.createFavorite(request, loginMember);
    return ResponseEntity.created(URI.create("/favorites/" + favorite.getId())).build();
  }

  @GetMapping("/favorites")
  public ResponseEntity<List<FavoriteResponse>> getFavorites(@AuthenticationPrincipal LoginMember loginMember) {
    List<FavoriteResponse> favorites = favoriteService.findFavorites();
    return ResponseEntity.ok().body(favorites);
  }

  @DeleteMapping("/favorites/{id}")
  public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
    favoriteService.deleteFavorite(id);
    return ResponseEntity.noContent().build();
  }
}

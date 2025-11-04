package scoresense.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import scoresense.app.model.Favorite;
import scoresense.app.repository.FavoriteRepository;

@RestController
@RequestMapping("/favorites")


public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;


    @GetMapping
    @Operation(summary = "Get all favorites", description = "Get all favorites from the DB")
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public Favorite getFavoriteById(@PathVariable Long id) {
//        return favoriteRepository.findById(id).orElse(null);
//    }

    @PostMapping
    @Operation(summary = "Create a favorite", description = "Create a favorite by ID")
    public Favorite createFavorite(@RequestBody Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

//    @PutMapping("/{id}")
//    public Favorite updateFavorite(@PathVariable Long id, @RequestBody Favorite favoriteDetails) {
//        return favoriteRepository.findById(id).map(fav -> {
//            fav.setEntity_type(favoriteDetails.getEntity_type());
//            fav.setEntity_id(favoriteDetails.getEntity_id());
//            fav.setUser(favoriteDetails.getUser());
//            return favoriteRepository.save(fav);
//        }).orElse(null);
//    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a favorite", description = "Delete a favorite by ID")
    public void deleteFavorite(@PathVariable Long id) {
        favoriteRepository.deleteById(id);
    }
}

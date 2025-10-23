package scoresense.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import scoresense.app.model.Favorite;
import scoresense.app.repository.FavoriteRepository;

import java.util.List;

@RestController
@RequestMapping("/favorites")


public class FavoriteController {

    @Autowired
    private FavoriteRepository favoriteRepository;


    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public Favorite getFavoriteById(@PathVariable Long id) {
//        return favoriteRepository.findById(id).orElse(null);
//    }

    @PostMapping
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
    public void deleteFavorite(@PathVariable Long id) {
        favoriteRepository.deleteById(id);
    }
}

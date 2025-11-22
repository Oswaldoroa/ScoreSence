package scoresense.app.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.PageRequest;

import scoresense.app.dto.MerchandiseRequest;
import scoresense.app.dto.MerchandiseResponse;
import scoresense.app.service.MerchandiseService;

import java.util.List;

@Controller
public class MerchandiseGraphQLController {

    private final MerchandiseService merchandiseService;

    public MerchandiseGraphQLController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    // --- QUERIES ---
    @QueryMapping
    public List<MerchandiseResponse> merchandise() {
        return merchandiseService.getAll();
    }

    @QueryMapping
    public MerchandiseResponse merchandiseById(@Argument Long id) {
        return merchandiseService.getById(id);
    }

    @QueryMapping
    public List<MerchandiseResponse> merchandiseByTeam(@Argument Long teamId) {
        return merchandiseService.findByTeam(teamId);
    }

    @QueryMapping
    public List<MerchandiseResponse> merchandiseByName(@Argument String name) {
        return merchandiseService.findByName(name);
    }

    @QueryMapping
    public List<MerchandiseResponse> merchandiseByCategory(@Argument String category) {
        return merchandiseService.findByCategory(category);
    }

    @QueryMapping
    public List<MerchandiseResponse> pagedMerchandise(@Argument int page, @Argument int size) {
        return merchandiseService.getAllPaged(PageRequest.of(page, size)).getContent();
    }

    // --- MUTATIONS ---
    @MutationMapping
    public MerchandiseResponse createMerchandise(
            @Argument Long teamId,
            @Argument String name,
            @Argument String category,
            @Argument Double price,
            @Argument Integer stock,
            @Argument String imageUrl,
            @Argument String type
    ) {
        MerchandiseRequest req = new MerchandiseRequest();
        req.setTeamId(teamId);
        req.setName(name);
        req.setCategory(category);
        req.setPrice(price);
        req.setStock(stock);
        req.setImageUrl(imageUrl);
        req.setType(type);
        return merchandiseService.create(req);
    }

    @MutationMapping
    public MerchandiseResponse updateMerchandise(
            @Argument Long merchandiseId,
            @Argument Long teamId,
            @Argument String name,
            @Argument String category,
            @Argument Double price,
            @Argument Integer stock,
            @Argument String imageUrl,
            @Argument String type
    ) {
        MerchandiseRequest req = new MerchandiseRequest();
        req.setTeamId(teamId);
        req.setName(name);
        req.setCategory(category);
        req.setPrice(price);
        req.setStock(stock);
        req.setImageUrl(imageUrl);
        req.setType(type);
        return merchandiseService.update(merchandiseId, req);
    }

    @MutationMapping
    public String deleteMerchandise(@Argument Long merchandiseId) {
        merchandiseService.delete(merchandiseId);
        return "Merchandise deleted successfully";
    }
}
package pl.coderslab.finalprojectcoffeewebsite.brewrecipe;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class BrewRecipeController {
    private final BrewRecipeService brewRecipeService;

    public BrewRecipeController(BrewRecipeService brewRecipeService) {
        this.brewRecipeService = brewRecipeService;
    }

// create recipe
    @PostMapping
    public ResponseEntity<BrewRecipeDTO> createBrewRecipe(@RequestBody BrewRecipeCreateDTO brewRecipeCreateDTO) {
        return ResponseEntity.ok(brewRecipeService.createBrewRecipe(brewRecipeCreateDTO));
    }

//    get all recipes
    @GetMapping
    public ResponseEntity<List<BrewRecipeDTO>> getAllBrewRecipe() {
        return ResponseEntity.ok(brewRecipeService.getAllBrewRecipes());
    }

//    get all recipes by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BrewRecipeDTO>> getBrewRecipeByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(brewRecipeService.getAllBrewRecipesByUserId(userId));
    }

//    get single review by review id
    @GetMapping("/{id}")
    public ResponseEntity<BrewRecipeDTO> getBrewRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(brewRecipeService.getBrewRecipeById(id));
    }

//    get recipes for single coffee
    @GetMapping("/coffee/{coffeeId}")
    public ResponseEntity<List<BrewRecipeDTO>> getBrewRecipeByCoffeeId(@PathVariable Long coffeeId) {
        return ResponseEntity.ok(brewRecipeService.getAllBrewRecipesForCoffee(coffeeId));
    }

//    update
    @PutMapping("/{id}")
    public ResponseEntity<BrewRecipeDTO> updateBrewRecipe(@PathVariable Long id,
                                                      @RequestBody BrewRecipeUpdateDTO brewRecipeUpdateDTO) {
        return ResponseEntity.ok(brewRecipeService.updateBrewRecipe(id, brewRecipeUpdateDTO));
    }

//    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrewRecipe(@PathVariable Long id) {
        brewRecipeService.deleteBrewRecipe(id);
        return ResponseEntity.ok("Recipe deleted successfully");
    }
}

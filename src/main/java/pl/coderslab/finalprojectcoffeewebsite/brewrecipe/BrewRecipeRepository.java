package pl.coderslab.finalprojectcoffeewebsite.brewrecipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrewRecipeRepository extends JpaRepository<BrewRecipe, Long> {
    void deleteByUserId(Long userId);
    List<BrewRecipe> findByCoffeeId(Long coffeeId);


}

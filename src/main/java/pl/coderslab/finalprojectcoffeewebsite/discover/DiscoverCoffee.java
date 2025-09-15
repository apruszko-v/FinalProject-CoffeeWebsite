package pl.coderslab.finalprojectcoffeewebsite.discover;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.finalprojectcoffeewebsite.brewrecipe.BrewRecipeDTO;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class DiscoverCoffee {
    private Long coffeeId;
    private String nameCoffee;
    private String description;
    private String roastery;
    private String origin;
    private String roastLevel;
    private Set<String> flavourNotes;
    private Set<String> recommendedMethods;
    private String imageUrl;
    private List<BrewRecipeDTO> brewRecipes;
    private Double rating;
}

package pl.coderslab.finalprojectcoffeewebsite.discover;

import org.springframework.stereotype.Service;
import pl.coderslab.finalprojectcoffeewebsite.brewingmethod.BrewingMethod;
import pl.coderslab.finalprojectcoffeewebsite.brewrecipe.BrewRecipe;
import pl.coderslab.finalprojectcoffeewebsite.brewrecipe.BrewRecipeDTO;
import pl.coderslab.finalprojectcoffeewebsite.brewrecipe.BrewRecipeRepository;
import pl.coderslab.finalprojectcoffeewebsite.coffee.Coffee;
import pl.coderslab.finalprojectcoffeewebsite.coffee.CoffeeRepository;
import pl.coderslab.finalprojectcoffeewebsite.model.FlavourNote;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscoverService {
    private final CoffeeRepository coffeeRepository;
    private final BrewRecipeRepository brewRecipeRepository;

    public DiscoverService(CoffeeRepository coffeeRepository,
                           BrewRecipeRepository brewRecipeRepository) {
        this.coffeeRepository = coffeeRepository;
        this.brewRecipeRepository = brewRecipeRepository;
    }

    public DiscoverCoffee getCoffeeWithRecipes(String coffeeName, String roasteryName) {
        Coffee coffee = coffeeRepository.findByNameAndRoastery(coffeeName, roasteryName)
                .orElseThrow(() -> new RuntimeException("Coffee not found"));

        List<BrewRecipeDTO> recipes = brewRecipeRepository.findByCoffeeId(coffee.getId())
                .stream()
                .map(this::convertRecipeToDTO)
                .collect(Collectors.toList());

        DiscoverCoffee dto = new DiscoverCoffee();
        dto.setCoffeeId(coffee.getId());
        dto.setNameCoffee(coffee.getNameCoffee());
        dto.setDescription(coffee.getDescription());
        dto.setRoastery(coffee.getRoastery().getNameRoastery());
        dto.setOrigin(coffee.getOrigin() != null ? coffee.getOrigin().getCountryOrigin() : null);
        dto.setRoastLevel(coffee.getRoastLevel() != null ? coffee.getRoastLevel().getNameLevel() : null);
        dto.setFlavourNotes(coffee.getFlavourNotes().stream()
                .map(FlavourNote::getNameFlavour).collect(Collectors.toSet()));
        dto.setRecommendedMethods(coffee.getRecommendedMethods().stream()
                .map(BrewingMethod::getNameMethod).collect(Collectors.toSet()));
        dto.setImageUrl(coffee.getImageUrl());
        dto.setBrewRecipes(recipes);

        return dto;
    }

    private BrewRecipeDTO convertRecipeToDTO(BrewRecipe recipe) {
        return BrewRecipeDTO.builder()
                .id(recipe.getId())
                .grindSetting(recipe.getGrindSetting())
                .coffeeDoseGrams(recipe.getCoffeeDoseGrams())
                .waterVolumeMl(recipe.getWaterVolumeMl())
                .waterTemperatureCelsius(recipe.getWaterTemperatureCelsius())
                .brewingTimeSeconds(recipe.getBrewingTimeSeconds())
                .notes(recipe.getNotes())
                .username(recipe.getUser().getUsername())
                .coffeeName(recipe.getCoffee().getNameCoffee())
                .grinderName(recipe.getGrinder() != null ? recipe.getGrinder().getNameGrinder() : null)
                .brewingMethodName(recipe.getBrewingMethod() != null ? recipe.getBrewingMethod().getNameMethod() : null)
                .build();
    }
}

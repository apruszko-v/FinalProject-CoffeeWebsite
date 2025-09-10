package pl.coderslab.finalprojectcoffeewebsite.brewrecipe;

import org.springframework.stereotype.Service;
import pl.coderslab.finalprojectcoffeewebsite.brewingmethod.BrewingMethod;
import pl.coderslab.finalprojectcoffeewebsite.brewingmethod.BrewingMethodRepository;
import pl.coderslab.finalprojectcoffeewebsite.coffee.Coffee;
import pl.coderslab.finalprojectcoffeewebsite.coffee.CoffeeRepository;
import pl.coderslab.finalprojectcoffeewebsite.grinder.Grinder;
import pl.coderslab.finalprojectcoffeewebsite.grinder.GrinderRepository;
import pl.coderslab.finalprojectcoffeewebsite.user.User;
import pl.coderslab.finalprojectcoffeewebsite.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrewRecipeService {
    private final BrewRecipeRepository brewRecipeRepository;
    private final UserRepository userRepository;
    private final CoffeeRepository coffeeRepository;
    private final GrinderRepository grinderRepository;
    private final BrewingMethodRepository brewingMethodRepository;

    public BrewRecipeService(BrewRecipeRepository brewRecipeRepository, UserRepository userRepository, CoffeeRepository coffeeRepository, GrinderRepository grinderRepository, BrewingMethodRepository brewingMethodRepository) {
        this.brewRecipeRepository = brewRecipeRepository;
        this.userRepository = userRepository;
        this.coffeeRepository = coffeeRepository;
        this.grinderRepository = grinderRepository;
        this.brewingMethodRepository = brewingMethodRepository;
    }

//    convert to dto
    private BrewRecipeDTO convertToDTO (BrewRecipe brewRecipe) {
        return BrewRecipeDTO.builder()
                .id(brewRecipe.getId())
                .grindSetting(brewRecipe.getGrindSetting())
                .coffeeDoseGrams((brewRecipe.getCoffeeDoseGrams()))
                .waterVolumeMl(brewRecipe.getWaterVolumeMl())
                .waterTemperatureCelsius(brewRecipe.getWaterTemperatureCelsius())
                .brewingTimeSeconds(brewRecipe.getBrewingTimeSeconds())
                .notes(brewRecipe.getNotes())

                .username(brewRecipe.getUser().getUsername())
                .coffeeName(brewRecipe.getCoffee().getNameCoffee())
                .brewingMethodName(brewRecipe.getBrewingMethod() != null ? brewRecipe.getBrewingMethod().getNameMethod() : null)
                .grinderName(brewRecipe.getGrinder() != null ? brewRecipe.getGrinder().getNameGrinder() : null)
                .build();
    }

    //    create
    public BrewRecipeDTO createBrewRecipe(BrewRecipeCreateDTO brewRecipeDTO) {
        User user = userRepository.findById(brewRecipeDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Coffee coffee = coffeeRepository.findById(brewRecipeDTO.getCoffeeId())
                .orElseThrow(() -> new RuntimeException("Coffee not found"));

        Grinder grinder = brewRecipeDTO.getGrinderId() != null ? grinderRepository.findById(brewRecipeDTO.getGrinderId()).orElse(null) : null;

        BrewingMethod brewingMethod = brewRecipeDTO.getBrewingMethodId() != null ? brewingMethodRepository.findById(brewRecipeDTO.getBrewingMethodId()).orElse(null) : null;

        BrewRecipe brewRecipe = new BrewRecipe();
        brewRecipe.setUser(user);
        brewRecipe.setCoffee(coffee);
        brewRecipe.setGrinder(grinder);
        brewRecipe.setBrewingMethod(brewingMethod);
        brewRecipe.setGrindSetting(brewRecipeDTO.getGrindSetting());
        brewRecipe.setCoffeeDoseGrams(brewRecipeDTO.getCoffeeDoseGrams());
        brewRecipe.setWaterVolumeMl(brewRecipeDTO.getWaterVolumeMl());
        brewRecipe.setWaterTemperatureCelsius(brewRecipeDTO.getWaterTemperatureCelsius());
        brewRecipe.setBrewingTimeSeconds(brewRecipeDTO.getBrewingTimeSeconds());
        brewRecipe.setNotes(brewRecipeDTO.getNotes());

        return convertToDTO(brewRecipeRepository.save(brewRecipe));
    }

//    read all recipes
    public List<BrewRecipeDTO> getAllBrewRecipes() {
        return brewRecipeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    read all recipes by user id
    public List<BrewRecipeDTO> getAllBrewRecipesByUserId(Long userId) {
        return brewRecipeRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    read single recipe
    public BrewRecipeDTO getBrewRecipeById(Long id) {
        BrewRecipe brewRecipe = brewRecipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Brew recipe not found"));
        return convertToDTO(brewRecipe);
    }

//    read all recipes for single coffee
    public List<BrewRecipeDTO> getAllBrewRecipesForCoffee(Long coffeeId) {
        return brewRecipeRepository.findByCoffeeId(coffeeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    update
    public BrewRecipeDTO updateBrewRecipe(Long id, BrewRecipeUpdateDTO brewRecipeUpdateDTO) {
        BrewRecipe brewRecipe = brewRecipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brew recipe not found"));

        brewRecipe.setGrindSetting(brewRecipeUpdateDTO.getGrindSetting());
        brewRecipe.setCoffeeDoseGrams(brewRecipeUpdateDTO.getCoffeeDoseGrams());
        brewRecipe.setWaterVolumeMl(brewRecipeUpdateDTO.getWaterVolumeMl());
        brewRecipe.setWaterTemperatureCelsius(brewRecipeUpdateDTO.getWaterTemperatureCelsius());
        brewRecipe.setBrewingTimeSeconds(brewRecipeUpdateDTO.getBrewingTimeSeconds());
        brewRecipe.setNotes(brewRecipeUpdateDTO.getNotes());

        return convertToDTO(brewRecipeRepository.save(brewRecipe));
    }

    //    delete
    public void deleteBrewRecipe(Long id) {
        if (!brewRecipeRepository.existsById(id)) {
            throw new RuntimeException("Brew recipe with id " + id + " does not exist");
        }
        brewRecipeRepository.deleteById(id);
    }
}

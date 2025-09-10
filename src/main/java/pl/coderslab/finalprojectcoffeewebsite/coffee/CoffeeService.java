package pl.coderslab.finalprojectcoffeewebsite.coffee;

import org.springframework.stereotype.Service;
import pl.coderslab.finalprojectcoffeewebsite.brewingmethod.BrewingMethod;
import pl.coderslab.finalprojectcoffeewebsite.model.FlavourNote;
import pl.coderslab.finalprojectcoffeewebsite.review.Review;
import pl.coderslab.finalprojectcoffeewebsite.review.ReviewRepository;
import pl.coderslab.finalprojectcoffeewebsite.user.User;
import pl.coderslab.finalprojectcoffeewebsite.user.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;
    private final ReviewRepository reviewRepository;

    public CoffeeService(CoffeeRepository coffeeRepository, ReviewRepository reviewRepository) {
        this.coffeeRepository = coffeeRepository;
        this.reviewRepository = reviewRepository;
    }

    private CoffeeDTO convertToDTO(Coffee coffee) {
        List<Review> reviews = reviewRepository.findByCoffeeId(coffee.getId());

        Double averageRating = null;
        if (!reviews.isEmpty()) {
            averageRating = reviews.stream()
                    .mapToDouble(review -> Double.parseDouble(review.getRatingReview()))
                    .average()
                    .orElse(0.0);
        }


        return CoffeeDTO.builder()
                .id(coffee.getId())
                .nameCoffee(coffee.getNameCoffee())
                .description(coffee.getDescription())
                .ratingCoffee(averageRating)
                .imageUrl(coffee.getImageUrl())
                .roastery(coffee.getRoastery() != null ? coffee.getRoastery().getNameRoastery() : null)
                .origin(coffee.getOrigin() != null ? coffee.getOrigin().getCountryOrigin() : null)
                .roastLevel(coffee.getRoastLevel() != null ? coffee.getRoastLevel().getNameLevel() : null)

                .flavourNotes(coffee.getFlavourNotes().stream()
                        .map(FlavourNote::getNameFlavour)
                        .collect(Collectors.toSet()))

                .recommendedMethods(coffee.getRecommendedMethods()
                        .stream()
                        .map(BrewingMethod::getNameMethod)
                        .collect(Collectors.toSet()))

                .build();
    }

    public List<CoffeeDTO> getAllCoffees() {
        return coffeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CoffeeDTO getCoffeeById(Long id) {
        Coffee coffee = coffeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(coffee);
    }

    public List<String> getAllOrigins() {
        return coffeeRepository.findDistinctOrigins();
    }

    public List<String> getAllBrewingMethods() {
        return coffeeRepository.findDistinctBrewingMethods();
    }

    public List<String> getAllFlavourNotes() {
        return coffeeRepository.findDistinctFlavourNotes();
    }

    public List<String> getAllRoastLevels() {
        return coffeeRepository.findDistinctRoastLevels();
    }

    public List<CoffeeDTO> filterCoffees(List<String> origins,
                                         List<String> roastLevels,
                                         List<String> flavourNotes,
                                         List<String> brewingMethods) {

        List<String> originsLower = toLowerList(origins);
        List<String> roastLevelsLower = toLowerList(roastLevels);
        List<String> flavourNotesLower = toLowerList(flavourNotes);
        List<String> brewingMethodsLower = toLowerList(brewingMethods);

        return coffeeRepository.filterCoffees(
                        originsLower.isEmpty() ? null : originsLower,
                        roastLevelsLower.isEmpty() ? null : roastLevelsLower,
                        flavourNotesLower.isEmpty() ? null : flavourNotesLower,
                        brewingMethodsLower.isEmpty() ? null : brewingMethodsLower
                )
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private List<String> toLowerList(List<String> list) {
        return list == null ? List.of() :
                list.stream()
                        .filter(s -> s != null && !s.isBlank())
                        .map(String::toLowerCase)
                        .toList();
    }
}

package pl.coderslab.finalprojectcoffeewebsite.coffee;

import org.springframework.stereotype.Service;
import pl.coderslab.finalprojectcoffeewebsite.brewingmethod.BrewingMethod;
import pl.coderslab.finalprojectcoffeewebsite.model.FlavourNote;
import pl.coderslab.finalprojectcoffeewebsite.review.Review;
import pl.coderslab.finalprojectcoffeewebsite.review.ReviewRepository;

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
        if(!reviews.isEmpty()) {
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

//    method: all coffees
    public List<CoffeeDTO> getAllCoffees() {
        return coffeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    method: filter coffee by roast level ?
    public List<CoffeeDTO> findCoffeeByRoastLevel(String roastLevel) {
        return coffeeRepository.findByRoastLevel(roastLevel)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    method: filter by flavour note ?
    public List<CoffeeDTO> findCoffeeByFlavour(String flavourName) {
        return coffeeRepository.findByNameFlavour(flavourName)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    method: filter coffees
    public List<CoffeeDTO> filterCoffees(String origin, String roastLevel, String flavourNote, String brewingMethod) {
        return coffeeRepository.filterCoffees(origin, roastLevel, flavourNote, brewingMethod)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


}

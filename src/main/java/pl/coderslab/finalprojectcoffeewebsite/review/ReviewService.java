package pl.coderslab.finalprojectcoffeewebsite.review;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.coderslab.finalprojectcoffeewebsite.coffee.Coffee;
import pl.coderslab.finalprojectcoffeewebsite.coffee.CoffeeRepository;
import pl.coderslab.finalprojectcoffeewebsite.user.User;
import pl.coderslab.finalprojectcoffeewebsite.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CoffeeRepository coffeeRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, CoffeeRepository coffeeRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.coffeeRepository = coffeeRepository;
    }

//    convertToDTO
    private ReviewDTO convertToDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .rating(Integer.parseInt(review.getRatingReview()))
                .comment(review.getComment())
                .username(review.getUser().getUsername())
                .coffeeName(review.getCoffee().getNameCoffee())
                .createdAt(review.getCreatedAt())
                .build();
    }

//    create
    public ReviewDTO createReview(ReviewCreateDTO reviewCreateDTO) {
        User user = userRepository.findById(reviewCreateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Coffee coffee = coffeeRepository.findById(reviewCreateDTO.getCoffeeId())
                .orElseThrow(() -> new RuntimeException("Coffee not found"));

        Review review = new Review();
        review.setRatingReview(String.valueOf(reviewCreateDTO.getRating()));
        review.setUser(user);
        review.setCoffee(coffee);
        review.setComment(reviewCreateDTO.getComment());

        Review savedReview = reviewRepository.save(review);

//        update rating
        updateCoffeeAverageRating(coffee.getId());

        return convertToDTO(savedReview);
    }

    private void updateCoffeeAverageRating(Long id) {
        Coffee coffee = coffeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coffee not found"));

        List<Review> reviews = reviewRepository.findByCoffeeId(id);

        Double averageRating = null;
        if(!reviews.isEmpty()) {
            averageRating = reviews.stream()
                    .mapToDouble(review -> Double.parseDouble(review.getRatingReview()))
                    .average()
                    .orElse(0.0);
        }
        coffee.setRatingCoffee(averageRating);
        coffeeRepository.save(coffee);
    }

    //    read single review
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        return convertToDTO(review);
    }

//    read all reviews for single coffee
    public List<ReviewDTO> getAllReviewsForCoffee(Long coffeeId) {
        return reviewRepository.findByCoffeeId(coffeeId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    read all reviews by user id
    public List<ReviewDTO> getAllReviewsForUser(Long userId) {
        return reviewRepository.findAllByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

//    update
    public ReviewDTO updateReview(Long id, ReviewUpdateDTO reviewUpdateDTO) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setRatingReview(String.valueOf(reviewUpdateDTO.getRating()));
        review.setComment(reviewUpdateDTO.getComment());

        Review updated = reviewRepository.save(review);

//        update rating
        updateCoffeeAverageRating(updated.getCoffee().getId());

        return convertToDTO(updated);
    }

//    delete
    public void deleteReview(Long id) {
        if(!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);

        updateCoffeeAverageRating(id);
    }
}

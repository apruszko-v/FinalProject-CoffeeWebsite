package pl.coderslab.finalprojectcoffeewebsite.review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

//    create
    @PostMapping
    public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewCreateDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.createReview(reviewDTO));
    }

//    read single review
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

//    read all review for single coffee
    @GetMapping("/coffee/{coffeeId}")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsByCoffee(@PathVariable Long coffeeId) {
        return ResponseEntity.ok(reviewService.getAllReviewsForCoffee(coffeeId));
    }

//    read all reviews by user id
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getAllReviewsForUser(userId));
    }

//    update
    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody ReviewUpdateDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(id, reviewDTO));
    }

//    delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}

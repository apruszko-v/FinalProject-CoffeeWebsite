package pl.coderslab.finalprojectcoffeewebsite.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCoffeeId(Long coffeeId);
    void deleteByUserId(Long userId);
    List<Review> findAllByUserId(Long userId);

}
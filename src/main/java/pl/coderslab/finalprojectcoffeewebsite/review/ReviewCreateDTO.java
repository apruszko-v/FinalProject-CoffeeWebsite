package pl.coderslab.finalprojectcoffeewebsite.review;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewCreateDTO {
    private int rating;
    private String comment;
    private Long userId;
    private Long coffeeId;
}

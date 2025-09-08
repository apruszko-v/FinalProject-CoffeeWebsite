package pl.coderslab.finalprojectcoffeewebsite.review;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewUpdateDTO {
    private int rating;
    private String comment;
}

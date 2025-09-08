package pl.coderslab.finalprojectcoffeewebsite.coffee;

import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoffeeDTO {
    private Long id;
    private String nameCoffee;
    private String description;
    private Double ratingCoffee;
    private String roastery;
    private String origin;
    private String roastLevel;
    private Set<String> flavourNotes;
    private Set<String> recommendedMethods;
}

package pl.coderslab.finalprojectcoffeewebsite.brewrecipe;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrewRecipeCreateDTO {
    private Integer grindSetting;
    private double coffeeDoseGrams;
    private Integer waterVolumeMl;
    private Integer waterTemperatureCelsius;
    private Integer brewingTimeSeconds;
    private String notes;

    private Long userId;
    private Long coffeeId;
    private Long grinderId;
    private Long brewingMethodId;
}

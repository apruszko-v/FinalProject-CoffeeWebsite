package pl.coderslab.finalprojectcoffeewebsite.brewrecipe;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrewRecipeUpdateDTO {
    private Integer grindSetting;
    private double coffeeDoseGrams;
    private Integer waterVolumeMl;
    private Integer waterTemperatureCelsius;
    private Integer brewingTimeSeconds;
    private String notes;
}

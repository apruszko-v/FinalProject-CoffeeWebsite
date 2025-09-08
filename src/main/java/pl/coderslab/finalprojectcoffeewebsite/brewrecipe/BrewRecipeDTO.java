package pl.coderslab.finalprojectcoffeewebsite.brewrecipe;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrewRecipeDTO {
    private Long id;
    private Integer grindSetting;
    private double coffeeDoseGrams;
    private Integer waterVolumeMl;
    private Integer waterTemperatureCelsius;
    private Integer brewingTimeSeconds;
    private String notes;

    private String username;
    private String coffeeName;
    private String brewingMethodName;
    private String grinderName;
}

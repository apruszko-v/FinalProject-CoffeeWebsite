package pl.coderslab.finalprojectcoffeewebsite.discover;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discover")
public class DiscoverController {
    private final DiscoverService discoverService;

    public DiscoverController(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @GetMapping
    public ResponseEntity<DiscoverCoffee> getCoffee(
            @RequestParam String coffeeName,
            @RequestParam String roasteryName) {
        return ResponseEntity.ok(discoverService.getCoffeeWithRecipes(coffeeName, roasteryName));
    }
}

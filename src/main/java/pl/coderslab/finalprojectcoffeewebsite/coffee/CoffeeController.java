package pl.coderslab.finalprojectcoffeewebsite.coffee;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {
    private final CoffeeService coffeeService;
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

//    all coffees
    @GetMapping
    public List<CoffeeDTO> getAllCoffees() {
        return coffeeService.getAllCoffees();
    }

//    by roast level ?
    @GetMapping("/roast-level/{roastLevel}")
    public List<CoffeeDTO> getByRoastLevel(@PathVariable("roastLevel") String roastLevel) {
        return coffeeService.findCoffeeByRoastLevel(roastLevel);
    }

//    by flavour name ?
    @GetMapping("/flavour-note/{flavourName}")
    public List<CoffeeDTO> getByFlavourNote(@PathVariable("flavourName") String flavourName) {
        return coffeeService.findCoffeeByFlavour(flavourName);
    }

//    filter coffees by origin, roastLevel, flavourNote, brewingMethod
    @GetMapping("/filter")
    public List<CoffeeDTO> getByFilter(
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String roastLevel,
            @RequestParam(required = false) String flavourNote,
            @RequestParam(required = false) String brewingMethod
    ) {
        return coffeeService.filterCoffees(origin, roastLevel, flavourNote, brewingMethod);
    }
}

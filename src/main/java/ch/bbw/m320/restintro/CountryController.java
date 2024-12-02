package ch.bbw.m320.restintro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Arrays.stream;

@CrossOrigin
@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final List<Country> list = new ArrayList<>();

    public CountryController() {
        // we fill the "database" with initial data.
        list.add(new Country(1, "USA", "United States of America", 4800000, 30000, 30008834, 20000));
    }

    @GetMapping("/list")
    public List<Country> getCountries() {
        return list;
    }

    @GetMapping("/id/{id}")
    public Country getCountryWithID(@PathVariable int id) {
        return list.stream()
                .filter(country -> country.id == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + id));
    }

    @GetMapping("/name/{name}")
    public Country getCountryWithName(@PathVariable String name) {
        return list.stream()
                .filter(country -> name.equals(country.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + name));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateCountry(@RequestBody Country newCountry, @PathVariable int id) {
        list.get(id).name = newCountry.name;
        return ResponseEntity.status(HttpStatus.CREATED).header("Update", "Bar").body("totally worked");
    }

    @PostMapping
    public ResponseEntity<String> newCountry(@RequestBody Country newCountry) {
        list.add(newCountry);
        return ResponseEntity.status(HttpStatus.CREATED).header("Create", "Bar").body("totally worked");
    }
}

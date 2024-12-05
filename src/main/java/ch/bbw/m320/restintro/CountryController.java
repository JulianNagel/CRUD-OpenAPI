package ch.bbw.m320.restintro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final List<Country> list = new ArrayList<>();

    public CountryController() {
        list.add(new Country(1, "USA", "United States of America", 4800000, 30000, 30008834, 20000));
        list.add(new Country(2, "Germany", "Repuplic of Germany", 400000, 30000, 30008834, 20000));
    }

    @GetMapping("/list")
    public List<Country> getCountries() {
        return list;
    }

    @GetMapping("/{id}")
    public Country getCountryWithID(@PathVariable int id) {
        return list.stream()
                .filter(country -> country.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + id));
    }

    @GetMapping("/name/{name}")
    public Country getCountryWithName(@PathVariable String name) {
        return list.stream()
                .filter(country -> name.equals(country.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCountry(@RequestBody Country newCountry, @PathVariable int id) {
        int index = IntStream.range(0, list.size())
                .filter(i -> list.get(i).getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + id));
        list.set(index, newCountry);
        return ResponseEntity.ok("Update successful");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable int id) {
        int index = IntStream.range(0, list.size())
                .filter(i -> list.get(i).getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + id));
        list.remove(index);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<String> newCountry(@RequestBody Country newCountry) {
        list.add(newCountry);
        return ResponseEntity.status(HttpStatus.CREATED).body("Country created successfully");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(IllegalArgumentException e) {
        return e.getMessage();
    }
}

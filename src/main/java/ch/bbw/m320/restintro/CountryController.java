package ch.bbw.m320.restintro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final List<Country> list = new ArrayList<>();

    public CountryController() {
        // Paar daten zugefügt
        list.add(new Country(1, "United States of America", 331002651, 9833520, 21137518, 79));
        list.add(new Country(2, "Peopels Republic of China", 1439323776, 9596961, 14140163, 77));
        list.add(new Country(3, "Republic of India", 1380004385, 3287263, 2875142, 69));
        list.add(new Country(4, "Federal Republic of Germany", 83783942, 357022, 3845630, 81));
        list.add(new Country(5, "Federative Republic of Brazil", 212559417, 8515767, 2236152, 75));
        list.add(new Country(6, "Commonwealth of Australia", 25499884, 7692024, 1392687, 83));
        list.add(new Country(7, "State of Japan", 126476461, 377975, 5064870, 85));
        list.add(new Country(8, "Canada", 37742154, 9984670, 1647120, 82));
    }

    //hier kann man alles holen
    @GetMapping
    public List<Country> getCountries() {
        return list;
    }

    // via id kann man ein land hinzufügen
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable int id) {
        Optional<Country> country = list.stream().filter(countryy -> countryy.getId() == id).findFirst();
        return country.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //
    @PostMapping
    public ResponseEntity<String> newCountry(@RequestBody Country newCountry) {
        if (list.stream().anyMatch(country -> country.getId() == newCountry.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("id occupied.");
        }
        list.add(newCountry);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-Fuu", "Bar")
                .body("country added.");
    }

    // Mit updat kann man ein
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCountry(@PathVariable int id, @RequestBody Country updatedCountry) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.set(i, updatedCountry);
                return ResponseEntity.ok("Country updaed.");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found.");
    }

    //Via ID kann man ein land löschen
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable int id) {
        boolean removed = list.removeIf(country -> country.getId() == id);
        if (removed) {
            return ResponseEntity.ok("Country deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found.");
    }
}

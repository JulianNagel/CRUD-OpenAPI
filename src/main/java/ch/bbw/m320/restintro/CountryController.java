package ch.bbw.m320.restintro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/country")
public class CountryController {

	private final List<Country> list = new ArrayList<>();

	public CountryController() {
		// we fill the "database" with initial data.
		list.add(new Country(1, "USA", 4800000,  30000, 30008834, 20000 ));
	}

	@GetMapping
	public List<Country> getCountries() {
		return list;
	}

	@PostMapping
	public ResponseEntity<String> newCountry(@RequestBody Country newCountry) {
		list.add(newCountry);
		return ResponseEntity.status(HttpStatus.CREATED).header("X-Fuu", "Bar").body("totally worked");
	}
}

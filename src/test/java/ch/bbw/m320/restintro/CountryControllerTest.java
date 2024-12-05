package ch.bbw.m320.restintro;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllCountries() throws Exception {
        mockMvc.perform(get("/api/country"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").isNumber()); // Verifies it returns a list
    }

    @Test
    void shouldAddNewCountry() throws Exception {
        Country newCountry = new Country(9, "France", 65273511, 551695, 2938274, 82);
        String countryJson = objectMapper.writeValueAsString(newCountry);

        mockMvc.perform(post("/api/country")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(countryJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateExistingCountry() throws Exception {
        Country updatedCountry = new Country(1, "USA Updated", 350000000, 9833520, 22000000, 80);
        String countryJson = objectMapper.writeValueAsString(updatedCountry);

        mockMvc.perform(put("/api/country/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(countryJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteCountry() throws Exception {
        mockMvc.perform(delete("/api/country/1"))
                .andExpect(status().isOk());
    }
}


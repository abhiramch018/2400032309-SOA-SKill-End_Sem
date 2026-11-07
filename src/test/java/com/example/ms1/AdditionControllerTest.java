package com.example.ms1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdditionController.class)
class AdditionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void negativeAndPositiveNumbersCancelOut() throws Exception {
        mockMvc.perform(get("/ms1/add").param("a", "-5").param("b", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("MS 1.1 - Additon = 0"));
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
        "0, 0, 0", "5, 7, 12", "-5, -7, -12", "-10, 3, -7",
        "2147483647, 1, 2147483648", "-2147483648, -1, -2147483649",
        "-2147483648, 2147483647, -1"
    })
    void returnsExactTextForDifferentInputs(String a, String b, String sum) throws Exception {
        mockMvc.perform(get("/ms1/add").param("a", a).param("b", b))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("MS 1.1 - Additon = " + sum));
    }
}

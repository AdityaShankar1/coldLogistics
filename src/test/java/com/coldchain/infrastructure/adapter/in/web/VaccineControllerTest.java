package com.coldchain.infrastructure.adapter.in.web;

import com.coldchain.application.port.in.IVaccineService;
import com.coldchain.domain.model.VaccineBatch;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VaccineController.class)
class VaccineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IVaccineService vaccineService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnOkWhenRegisteringBatch() throws Exception {
        VaccineBatch batch = new VaccineBatch("B1", "mRNA", LocalDate.now(), null);
        when(vaccineService.registerBatch(anyString(), anyString(), anyString())).thenReturn(batch);

        String jsonRequest = "{\"batchNumber\":\"B1\",\"vaccineType\":\"mRNA\",\"expiryDate\":\"2026-12-31\"}";

        mockMvc.perform(post("/api/vaccines")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }
}

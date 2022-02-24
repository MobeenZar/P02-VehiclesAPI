package com.udacity.pricing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.udacity.pricing.domain.price.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@Autowired
	PriceRepository priceRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getAll() throws Exception {
		mockMvc.perform(get("/prices/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("_embedded").exists())
				.andExpect(jsonPath("_embedded.prices").exists())
				.andExpect(jsonPath("_embedded.prices", hasSize(20)));
	}

	@Test
	public void getPriceById() throws Exception {
		mockMvc.perform(get("/prices/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("currency").exists())
				.andExpect(jsonPath("currency").value("USD"));
	}

}

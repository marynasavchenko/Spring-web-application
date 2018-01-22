package pro.abacus.webRestProject.Configurations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
// will auto-configure the Spring MVC infrastructure
//limited to a single controller in this case
//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
public class MockTests {

	@Autowired
	private MockMvc mockMvc;

	// define a Mockito mock for a bean inside ApplicationContext
	/*@MockBean
	private UserService userService;*/

	@Test
	public void shouldPostREgistrationDetails() throws Exception{
		mockMvc.perform(post("/registration")
				.param("userID", "123")
				.param("name", "test1")
				.param("email", "test1@gmail.com")
				.param("password", "password1")
				)
				.andExpect(status().isOk());
	}
		
}

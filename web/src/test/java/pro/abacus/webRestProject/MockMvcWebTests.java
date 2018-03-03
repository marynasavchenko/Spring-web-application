package pro.abacus.webRestProject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

//set the Runner to run tests
@RunWith(SpringRunner.class)
//Creates ApplicationContext
@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcWebTests {
	
	@Autowired
	private WebApplicationContext context;
	
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void homePage() throws Exception {
		 mockMvc.perform(get("/"))
				//asserts an HTTP 200 response code
				.andExpect(status().isOk())
				.andExpect(view().name("home"));
		
		}
	@Test
	public void shoulShowRegistrationForm() throws Exception{
		 mockMvc.perform(get("/registration"))
				.andExpect(status().isOk())
				.andExpect(view().name("registration"));
				//.andExpect(model().attributeExists("user"));
	}
	
	@Test
	public void shoulShowLoginForm() throws Exception{
	     mockMvc.perform(get("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}
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
	
	@Test
	public void shouldPostLoginDetails() throws Exception{
		mockMvc.perform(post("/registration")
				.param("userID", "123")
				.param("name", "test1")
				.param("email", "test1@gmail.com")
				.param("password", "password1")
				)
		        .andExpect(status().isOk());
		
		
		mockMvc.perform(post("/login")
				.param("name", "test9")
				.param("password", "test9"))
		 		//.andExpect(redirectedUrl("/home"));
		.andExpect(status().isOk());
		 
		 
	}
	
	
	
}

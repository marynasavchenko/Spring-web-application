package pro.abacus.webRestProject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import pro.abacus.webRestProject.Controllers.HomeController;
import pro.abacus.webRestProject.Services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
//Web environment is required, instantiate only one controller
@WebMvcTest(HomeController.class)
@WithMockUser(roles = "USER")
public class HomeControllerTest {
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldShowHomePage() throws Exception {
		 mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("home"));
		}
	
	@Test
	public void shoulShowRegistrationForm() throws Exception{
		 mockMvc.perform(get("/registration"))
				.andExpect(status().isOk())
				.andExpect(view().name("registration"))
				.andExpect(model().attributeExists("user"));
	}
	
	@Test
	public void shoulShowLoginForm() throws Exception{
	     mockMvc.perform(get("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}
	@Test
	public void shouldPostRegistrationDetails() throws Exception{
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
				.andExpect(status().isOk()); 
	}
	
}

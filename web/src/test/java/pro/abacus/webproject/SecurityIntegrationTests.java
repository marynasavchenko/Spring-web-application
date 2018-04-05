package pro.abacus.webproject;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class SecurityIntegrationTests {
//test does not work
	@Autowired
	private WebApplicationContext context;
	
	/*@Autowired
	FilterChainProxy springSecurityFilterChain;*/

	
	private MockMvc mockMvc;
	

	@Before
	public void setup() {
		MockMvc mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				//.apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
				.build();

	}

	@Test
	@WithMockUser
	public void requestProtectedUrlWithUser() throws Exception {
		mockMvc.perform(get("/webService").with(httpBasic("user", "password")));
	}

}

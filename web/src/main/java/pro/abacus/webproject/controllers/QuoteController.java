package pro.abacus.webproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import pro.abacus.webproject.restclient.*;

@Controller
@RequestMapping(path = "/")
public class QuoteController {

	
	private QuoteService quoteService;
	
	
	@Autowired
	public QuoteController(QuoteService quoteService){
		this.quoteService=quoteService;
	}

	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/webService")
	public String showWebServiceForm(Model model, String logout) {
		return "webService";
	}

	@GetMapping("/quote")
	public ResponseEntity<String> showDailyQuote() {

		Quote quote = quoteService.getDailyQuote(QuoteService.CATEGORY_INSPIRATIONAL);
		if (quote == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(quoteService.showQuote(quote));
	}

}

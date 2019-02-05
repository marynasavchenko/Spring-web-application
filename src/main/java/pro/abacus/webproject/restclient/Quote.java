package pro.abacus.webproject.restclient;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

	private String id;
	private String title;
	private String quote;
	private String author;
	private String length;
	private Date date;
	private String background;
	private String category;
	private ArrayList<String> tags;

	public Quote() {

	}

	public Quote(String quote, String author) {
		this.quote = quote;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}


	@Override
	public String toString() {
		return "Quote [id=" + id + ", title=" + title + ", quote=" + quote + ", author=" + author + ", length=" + length
				+ ", date=" + date + ", background=" + background + ", category=" + category + ", tags=" + tags + "]";
	}

	public Quote getDefaultQuote() {
		return new Quote("Once you stop learning, you start dying", "Albert Einstein");
	}


}

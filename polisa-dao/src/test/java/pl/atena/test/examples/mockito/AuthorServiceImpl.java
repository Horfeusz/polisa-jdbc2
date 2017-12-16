package pl.atena.test.examples.mockito;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorServiceImpl implements AuthorService {

	/** Serwis książek */
	private BookService bookService;

	/** walidator książek */
	private BookValidatorService bookValidatorService;

	@Override
	public int getTotalBooks(String author) {

		List<Book> books = bookService.findBookByAuthor(author);

		// filters some bot writers
		List<Book> filtered = books.stream().filter(x -> bookValidatorService.isValid(x)).collect(Collectors.toList());

		return filtered.size();
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public BookValidatorService getBookValidatorService() {
		return bookValidatorService;
	}

	public void setBookValidatorService(BookValidatorService bookValidatorService) {
		this.bookValidatorService = bookValidatorService;
	}

}

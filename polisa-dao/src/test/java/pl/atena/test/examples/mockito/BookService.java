package pl.atena.test.examples.mockito;

import java.util.List;

/**
 * Serwis wyszukujący książki
 *
 */
public interface BookService {

	/**
	 * Znajdż książkę po autorze
	 * 
	 * @param author
	 * @return
	 */
	List<Book> findBookByAuthor(String author);
}

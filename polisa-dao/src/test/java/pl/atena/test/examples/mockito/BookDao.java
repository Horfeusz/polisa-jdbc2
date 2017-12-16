package pl.atena.test.examples.mockito;

import java.util.List;

/**
 * DAO obsługi książek
 *
 */
public interface BookDao {

	/**
	 * Wyszukiwanie książki po autorze
	 * 
	 * @param author
	 * @return
	 */
	List<Book> findBookByAuthor(String author);

}

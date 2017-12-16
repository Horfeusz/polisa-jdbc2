package pl.atena.test.examples.mockito;

public interface AuthorService {

	/**
	 * Ile jest książek tego autora
	 * 
	 * @param author
	 * @return
	 */
	int getTotalBooks(String author);
}

package pl.atena.domain.polisa;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import pl.atena.domain.base.Entity;

/**
 * Klasa reprezentująca polise
 * 
 * @author michalh
 *
 */
public class Polisa extends Entity {

	private String nrPolisy;

	private LocalDate dPodpisania;

	private LocalDateTime dRozpoczecia;

	private LocalDateTime dKonca;

	private BigDecimal skladka;

	public String getNrPolisy() {
		return nrPolisy;
	}

	public void setNrPolisy(String nrPolisy) {
		this.nrPolisy = nrPolisy;
	}

	public LocalDate getdPodpisania() {
		return dPodpisania;
	}

	public void setdPodpisania(LocalDate dPodpisania) {
		this.dPodpisania = dPodpisania;
	}

	public LocalDateTime getdRozpoczecia() {
		return dRozpoczecia;
	}

	public void setdRozpoczecia(LocalDateTime dRozpoczecia) {
		this.dRozpoczecia = dRozpoczecia;
	}

	public LocalDateTime getdKonca() {
		return dKonca;
	}

	public void setdKonca(LocalDateTime dKonca) {
		this.dKonca = dKonca;
	}

	public BigDecimal getSkladka() {
		return skladka;
	}

	public void setSkladka(BigDecimal skladka) {
		this.skladka = skladka;
	}

	@Override
	public String toString() {
		return String.format("Polisa [id=%s, nrPolisy=%s, dPodpisania=%s, dRozpoczecia=%s, dKonca=%s, skladka=%s]",
				getId(), nrPolisy, dPodpisania, dRozpoczecia, dKonca, skladka);
	}

}

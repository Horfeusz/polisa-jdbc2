package pl.atena.test.examples.collections;

import java.util.Objects;

public class Fruit {

	private String name;

	private int qty;

	public Fruit(String name, int qty) {
		this.name = name;
		this.qty = qty;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, qty);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Fruit fruit = (Fruit) o;
		return qty == fruit.qty && Objects.equals(name, fruit.name);
	}

}

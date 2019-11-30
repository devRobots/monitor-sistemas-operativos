package modelo.productora;

import java.util.Random;

public class Producto implements Comparable<Producto> {
	private int id;
	private int productor;
	private int tiempo;
	
	public Producto(int id, int productor) {
		this.id = id;
		this.productor = productor;

		Random rand = new Random();
		tiempo = rand.nextInt(200) + 100;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductor() {
		return productor;
	}

	public void setProductor(int productor) {
		this.productor = productor;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public int compareTo(Producto o) {
		return 0;
	}
}

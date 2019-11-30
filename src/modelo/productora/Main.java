package modelo.productora;

import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Monitor monitor = new Monitor();
		Random rand = new Random();

		System.out.println("COMIENZA LA PRODUCCION");

		int cantProductores = rand.nextInt(5) + 5;
		ArrayList<Productor> productores = new ArrayList<>();
		for (int i = 0; i < cantProductores; i++) {
			int cantProductos = rand.nextInt(5) + 20;
			Productor p = new Productor(i + 1, monitor, cantProductos);
			productores.add(p);
			p.start();
		}

		int cantConsumidores = rand.nextInt(10) + 10;
		ArrayList<Consumidor> consumidores = new ArrayList<>();
		for (int i = 0; i < cantConsumidores; i++) {
			Consumidor c = new Consumidor(i + 1, monitor);
			consumidores.add(c);
			c.start();
		}

		for (Productor productor : productores) {
			try {
				productor.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("FINALIZA LA PRODUCCION");

		for (Consumidor consumidor : consumidores) {
			try {
				consumidor.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("FINALIZA EL CONSUMO DE PRODUCTOS");
	}

}

package modelo.productora;

public class Consumidor extends Thread {
	private int idConsumidor;
	private Monitor monitor;
	
	/**
	 * Constructor del hilo Consumidor
	 * @param idConsumidor
	 * @param monitor
	 */
	public Consumidor(int idConsumidor, Monitor monitor) {
		this.idConsumidor = idConsumidor;
		this.monitor = monitor;
	}

	@Override
	public void run() {
		while (monitor.hayProductosPendientes()) {
			try {
				Producto p = monitor.liberarProducto();
				sleep(p.getTiempo() * 2);
				System.out.println("El consumidor " + idConsumidor + "B compro el producto " + p.getId()
						+ "P del productor " + p.getProductor() + "A");
			} catch (Exception ex) {
				break;
			}
		}
	}

}

package modelo.productora;

public class Productor extends Thread{
	private int idProductor;
	private Monitor monitor;

	private int cantProductos;

	public Productor(int idProductor, Monitor monitor) {
		this.idProductor = idProductor;
		this.monitor = monitor;
		cantProductos = 1;
	}

	public Productor(int idProductor, Monitor monitor, int cantProductos) {
		this.idProductor = idProductor;
		this.monitor = monitor;
		this.cantProductos = cantProductos;
	}

	@Override
	public void run() {
		for (int i = 0; i < cantProductos; i++) {
			try {
				Producto p = new Producto((idProductor * 100) + i, idProductor);
				monitor.agregarProducto(p);

				sleep(p.getTiempo());
				System.out.println("El productor " + idProductor + "A creo el producto " + p.getId() + "P");
			} catch (Exception e) {
				break;
			}
		}
	}

	public int getIdProductor() {
		return idProductor;
	}

	public void setIdProductor(int idProductor) {
		this.idProductor = idProductor;
	}

	public int getCantProductos() {
		return cantProductos;
	}

	public void setCantProductos(int cantProductos) {
		this.cantProductos = cantProductos;
	}
}

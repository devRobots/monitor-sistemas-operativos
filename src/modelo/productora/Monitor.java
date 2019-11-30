package modelo.productora;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Monitor {
    private Semaphore semaforo;
    private PriorityQueue<Producto> colaProductos;

    public Monitor() {
        semaforo = new Semaphore(1);
        colaProductos = new PriorityQueue<>();
    }

    public void agregarProducto(Producto p) {
        try {
            semaforo.acquire();
            colaProductos.add(p);
            semaforo.release();
        } catch (Exception ex) {
            System.err.println("ERROR: No se pudo agregar el producto");
        }
    }

    public Producto liberarProducto() {
        try {
            semaforo.acquire();
            Producto p = colaProductos.poll();
            semaforo.release();
            return p;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean hayProductosPendientes() {
        return colaProductos.size() > 0;
    }
}
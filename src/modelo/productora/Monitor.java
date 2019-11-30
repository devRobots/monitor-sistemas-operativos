package modelo.productora;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Monitor {
    private Semaphore semaforo;
    private PriorityQueue<Producto> colaProductos;
    private int tiempoTotal;

    public Monitor() {
        semaforo = new Semaphore(1);
        colaProductos = new PriorityQueue<>();
        tiempoTotal = 0;
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
            tiempoTotal += p.getTiempo();
            return p;
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean hayProductosPendientes() {
        return colaProductos.size() > 0;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }
}
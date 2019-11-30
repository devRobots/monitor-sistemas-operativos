package modelo.editorial;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Monitor {
    private Semaphore semaforo;
    private PriorityQueue<Lector> colaLectores;
    private Escritor escritor;
    private Libro libro;

    public Monitor(Libro libro) {
        semaforo = new Semaphore(1);
        colaLectores = new PriorityQueue<>();
        this.libro = libro;
    }

    public void empezarLeer(Lector l) {
        try {
            semaforo.acquire();
            colaLectores.add(l);
            semaforo.release();
        } catch (Exception ex) {
            System.err.println("ERROR: No se pudo leer el libro\n" + ex.getMessage());
        }
    }

    public String terminarLeer() {
        try {
            semaforo.acquire();
            colaLectores.poll();
            semaforo.release();
            return libro.getTexto().toString();
        } catch (Exception ex) {
            System.err.println("ERROR: No se pudo finalizar la lectura");
            return null;
        }
    }

    public void empezarEscribir(Escritor e) {
        this.escritor = e;
    }

    public char dejarEscribir() {
        escritor = null;
        return libro.escribir();
    }

    public boolean estaEsperando(Lector l) {
        try {
            semaforo.acquire();
            boolean flag = !colaLectores.peek().equals(l);
            semaforo.release();
            return flag;
        } catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }
    }

    public boolean tieneLectores() {
        return colaLectores.size() > 0;
    }

    public boolean tieneEscritor() {
        return escritor != null;
    }

    public boolean estaTerminado() {
        return libro.estaTerminado();
    }

    public Libro getLibro() {
        return libro;
    }
}
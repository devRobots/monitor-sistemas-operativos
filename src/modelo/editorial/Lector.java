package modelo.editorial;

public class Lector extends Thread implements Comparable<Lector> {
    private int idLector;
    private Monitor monitor;

    public Lector(int idLector, Monitor monitor) {
        this.idLector = idLector;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        // Intenta leer hasta que haya leido el libro terminado
        boolean leido = false;
        while (!leido) {
            // Verifica que no esten escribiendo
            if (!monitor.tieneEscritor()) {
                try {
                    // Se pone en la cola de lectura
                    monitor.empezarLeer(this);
                    // Espera hasta que sea su turno para leer
                    while (monitor.estaEsperando(this)) {
                    }
                    // Verifica una vez mas si la version que esta leyendo es la definitiva
                    if(monitor.estaTerminado()) leido = true;
                    // Termina su sesion de lectura
                    String lectura = monitor.terminarLeer();
                    // Imprime el texto que leyo
                    System.out.println("El lector " + idLector + " leyo el texto: \"" + lectura + "\"");
                    // Retardo por lectura
                    sleep(lectura.length() * 10);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    break;
                }
            }
        }
    }

    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }

    @Override
    public int compareTo(Lector o) {
        return 0;
    }
}
package modelo.editorial;

public class Escritor extends Thread {
    private int idEscritor;
    private Monitor monitor;

    public Escritor(int idEscritor, Monitor monitor) {
        this.idEscritor = idEscritor;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        // Intenta escribir hasta que el libro este terminado
        while (!monitor.estaTerminado()) {
            // Verifica que no esten escribiendo ni leyendo
            if (!monitor.tieneLectores() || !monitor.tieneEscritor()) {
                try {
                    // Empieza a escribir
                    monitor.empezarEscribir(this);
                    // Termina de escribir
                    char escrito = monitor.dejarEscribir();
                    // Imprime lo que escribio
                    System.out.println("El escritor " + idEscritor + " escribio: \"" + escrito + "\"");
                    // Retardo por escritura
					sleep(20);
				} catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    break;
				}
            }
        }
    }

    public int getIdEscritor() {
        return idEscritor;
    }

    public void setIdEscritor(int idEscritor) {
        this.idEscritor = idEscritor;
    }
    
}
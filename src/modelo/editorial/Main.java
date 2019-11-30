package modelo.editorial;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Libro libro = new Libro(0);
        Monitor monitor = new Monitor(libro);

        ArrayList<Escritor> escritores = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Escritor e = new Escritor(i + 1, monitor);
            escritores.add(e);
            e.start();
        }

        ArrayList<Lector> lectores = new ArrayList<>();
        int cantLectores = (new Random().nextInt(5)) + 5;
        for (int i = 0; i < cantLectores; i++) {
            Lector l = new Lector(i + 1, monitor);
            lectores.add(l);
            l.start();
        }

        for (Escritor escritor : escritores) {
            try {
                escritor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Lector lector : lectores) {
            try {
                lector.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
package modelo.editorial;

public class Libro {
    private int idLibro;
    private String texto;

    public Libro(int idLibro) {
        this.setIdLibro(idLibro);
        texto = "";
    }

    public char escribir() {
        char c = (char) (((int)(Math.random() * 26)) + 65);

        if (!texto.endsWith(" ") && !texto.isEmpty()) {
            if ((Math.random()*4) < 1) {
                c = ' ';
            }
            else {
                c += 32;
            }
        }
        
        texto += c;
        return c;
    }

	public boolean estaTerminado() {
        return texto.length() == 100;
    }

    public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
    }
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
package org.example.polimorfismo;

interface InstrumentoMusical {
    void tocar();

    void setTipo(String tipo);

    String getTipo();
}

class Guitarra implements InstrumentoMusical {

    private int ncuerdas;
    private String tipo;
    public void tocar() {
        System.out.println("Tocando la guitarra.");
    }

    public Guitarra(String tipo){
        this.tipo = tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }
}

class Piano implements InstrumentoMusical {

    private int ncuerdas;
    private String tipo;

    public Piano(String tipo){
        this.tipo = tipo;
    }
    public void tocar() {
        System.out.println("Tocando el piano.");
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }
}

class Trompeta implements InstrumentoMusical{

    private String tamano;
    private String tipo;
    @Override
    public void tocar() {
        System.out.println("Tocando trompeta");
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    public Trompeta(String tipo) {
        this.tipo = tipo;
    }
}

public class Polimorfismo {
    public static void main(String[] args) {
        InstrumentoMusical[] instrumentos = new InstrumentoMusical[3];
        instrumentos[0] = new Guitarra("Cuerda");
        instrumentos[1] = new Piano("Cuerda");
        instrumentos[2] = new Trompeta("Aire");

        for (InstrumentoMusical instrumento : instrumentos) {
            instrumento.tocar();
            System.out.println(instrumento.getTipo());// Llama al método tocar() de cada objeto
        }
    }
}

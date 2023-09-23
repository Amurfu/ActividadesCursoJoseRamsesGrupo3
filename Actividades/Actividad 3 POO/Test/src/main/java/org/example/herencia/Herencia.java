package org.example.herencia;

class Animal {
    String nombre;

    Animal(String nombre) {
        this.nombre = nombre;
    }

    void emitirSonido() {
        System.out.println("El animal hace un sonido.");
    }
}

class Perro extends Animal {
    Perro(String nombre) {
        super(nombre);
    }

    @Override
    void emitirSonido() {
        System.out.println("El perro ladra.");
    }
}

public class Herencia {
    public static void main(String[] args) {
        Animal animal = new Animal("Animal");
        animal.emitirSonido(); // Salida: El animal hace un sonido.

        Perro perro = new Perro("Fido");
        perro.emitirSonido(); // Salida: El perro ladra.
    }
}

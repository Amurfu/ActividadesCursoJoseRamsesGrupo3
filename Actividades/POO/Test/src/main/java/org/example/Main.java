package org.example;


import org.example.obj.Perro;

public class Main {
    public static void main(String[] args) {

            Perro p = new Perro();
            p.setRaza("ssss");
            p.setColor("rojo");
            p.setEdad(12);
            System.out.printf(p.toString());




    }
}
package org.example.obj;

public class Perro {

    private  String raza;
    private String color;
    private float edad;

    public Perro(){

    }
    public Perro (String raza, String color){
        this.raza = raza;
        this.color = color;
        edad = 0;
    }

    public Perro (String raza, String color,float edad){
        this.raza = raza;
        this.color = color;
        this.edad = edad;
    }

    public void ladrar(){
        System.out.println("Emitiendo sonido");
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getEdad() {
        return edad;
    }

    public void setEdad(float edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Perro{" +
                "raza='" + raza + '\'' +
                ", color='" + color + '\'' +
                ", edad=" + edad +
                '}';
    }
}




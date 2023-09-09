package org.example.encapsulamiento;


class CuentaBancaria {
    private double saldo; // Atributo privado

    public void depositar(double cantidad) {
        saldo += cantidad;
    }

    public void retirar(double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public double obtenerSaldo() {
        return saldo;
    }
}

public class Encapsulamiento {

    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.depositar(1000);
        cuenta.retirar(500);
        System.out.println("Saldo actual: " + cuenta.obtenerSaldo());
    }

}
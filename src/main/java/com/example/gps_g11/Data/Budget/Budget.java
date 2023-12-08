package com.example.gps_g11.Data.Budget;

public class Budget {
    double saldoReal; //Saldo que existe mesmo
    private boolean excedeuSaldo;

    public Budget(double saldo) {
        this.saldoReal = saldo;
        this.excedeuSaldo = false;
    }

    public double getSaldoReal() {
        return saldoReal;
    }

    public void setSaldoReal(double saldoReal) {
        this.saldoReal = saldoReal;
    }

    public boolean isExcedeuSaldo() {
        return excedeuSaldo;
    }

    public void setExcedeuSaldo(boolean excedeuSaldo) {
        this.excedeuSaldo = excedeuSaldo;
    }
}

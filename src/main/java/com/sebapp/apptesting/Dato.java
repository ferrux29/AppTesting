package com.sebapp.apptesting;

import com.sebapp.apptesting.models.Banco;
import com.sebapp.apptesting.models.Cuenta;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

public class Dato {
   /* public static final Cuenta CUENTA_01 = new Cuenta(1L, "Ferru", new BigDecimal(1000));
    public static final Cuenta CUENTA_02 = new Cuenta(2L, "Seba",new BigDecimal(2000));
    public static final Banco BANCO = new Banco(1L, "BNB",0);*/
    public static Optional<Cuenta> crearCuenta01(){
        return Optional.of(new Cuenta(1L, "Ferru", new BigDecimal(1000)));
    }
    public static Optional<Cuenta> crearCuenta02(){
        return Optional.of(new Cuenta(2L, "Sebas", new BigDecimal(2000)));
    }
    public static Optional<Banco> crearBanco(){
        return Optional.of(new Banco(1L, "BNB", 0));
    }
}

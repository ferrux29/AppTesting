package com.sebapp.apptesting.models;

import com.sebapp.apptesting.exceptions.DineroInsuficienteException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String persona;
    @Column(name = "saldo_cuenta")
    private BigDecimal saldo;
    public void debito(BigDecimal monto){
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new DineroInsuficienteException("Dinero insuficiente en la cuenta");
        }
        this.saldo = nuevoSaldo;
    }
    public void credito(BigDecimal monto){
        this.saldo = saldo.add(monto);
    }


}

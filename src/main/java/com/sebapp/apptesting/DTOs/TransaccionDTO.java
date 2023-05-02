package com.sebapp.apptesting.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter @Setter
public class TransaccionDTO {
    private Long cuentaOrigen;
    private Long cuentaDestino;
    private BigDecimal monto;
    private Long BancoId;

}

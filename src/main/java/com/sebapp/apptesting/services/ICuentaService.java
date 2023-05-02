package com.sebapp.apptesting.services;

import com.sebapp.apptesting.models.Banco;
import com.sebapp.apptesting.models.Cuenta;

import java.math.BigDecimal;

public interface ICuentaService {
    Cuenta findById(Long id);
    int revisarTotalTransferencias(Long bancoId);
    BigDecimal revisarSaldoCuenta(Long cuentaId);
    void transferir(Long NroCuentaOrigen, Long NroCuentaDestino, BigDecimal monto, Long bancoId);

}

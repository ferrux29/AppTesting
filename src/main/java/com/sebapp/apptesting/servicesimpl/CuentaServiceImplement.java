package com.sebapp.apptesting.servicesimpl;

import com.sebapp.apptesting.models.Banco;
import com.sebapp.apptesting.models.Cuenta;
import com.sebapp.apptesting.repositories.IBancoRepository;
import com.sebapp.apptesting.repositories.ICuentaRepository;
import com.sebapp.apptesting.services.ICuentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CuentaServiceImplement implements ICuentaService {
    private IBancoRepository iBancoRepository;
    private ICuentaRepository iCuentaRepository;

    public CuentaServiceImplement(IBancoRepository iBancoRepository, ICuentaRepository iCuentaRepository) {
        this.iBancoRepository = iBancoRepository;
        this.iCuentaRepository = iCuentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Cuenta findById(Long id) {
        return iCuentaRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public int revisarTotalTransferencias(Long bancoId) {
        Banco banco = iBancoRepository.findById(bancoId).orElseThrow();
        return banco.getTotalTransferencias();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal revisarSaldoCuenta(Long cuentaId) {
        Cuenta cuenta = iCuentaRepository.findById(cuentaId).orElseThrow();
        return cuenta.getSaldo();
    }

    @Override
    @Transactional
    public void transferir(Long NroCuentaOrigen, Long NroCuentaDestino, BigDecimal monto, Long bancoId) {
        //Debitar de la cuenta Origen
        Cuenta cuenta = iCuentaRepository.findById(NroCuentaOrigen).orElseThrow();
        cuenta.debito(monto);
        iCuentaRepository.save(cuenta);
        //Acreditar la cuenta Destino
        Cuenta cuentaDestino = iCuentaRepository.findById(NroCuentaDestino).orElseThrow();
        cuentaDestino.credito(monto);
        iCuentaRepository.save(cuentaDestino);
        //Actualizar el total de transferencias de una Banco en particular
        Banco banco = iBancoRepository.findById(bancoId).orElseThrow();
        int totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        iBancoRepository.save(banco);
    }
}

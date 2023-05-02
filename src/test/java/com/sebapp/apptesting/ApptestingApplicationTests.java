package com.sebapp.apptesting;

import static com.sebapp.apptesting.Dato.*;
import com.sebapp.apptesting.models.Banco;
import com.sebapp.apptesting.models.Cuenta;
import com.sebapp.apptesting.repositories.IBancoRepository;
import com.sebapp.apptesting.repositories.ICuentaRepository;
import com.sebapp.apptesting.services.ICuentaService;
import com.sebapp.apptesting.servicesimpl.CuentaServiceImplement;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

@SpringBootTest
class ApptestingApplicationTests {
    @MockBean
    ICuentaRepository iCuentaRepository;
    @MockBean
    IBancoRepository iBancoRepository;
    @Autowired
    CuentaServiceImplement iCuentaService;


    @Test
    void contextLoads() {
        //Configurar el contexto para la prueba
        when(iCuentaRepository.findById(1L)).thenReturn(crearCuenta01());
        when(iCuentaRepository.findById(2L)).thenReturn(crearCuenta02());
        when(iBancoRepository.findById(1L)).thenReturn(crearBanco());
        //Realizar las pruebas
        BigDecimal saldoOrigen = iCuentaService.revisarSaldoCuenta(1L);
        BigDecimal saldoDestino = iCuentaService.revisarSaldoCuenta(2L);
        assertEquals("1000", saldoOrigen.toPlainString());
        assertEquals("2000", saldoDestino.toPlainString());
        //assertThrows(DineroInsuficienteException.class,()->{
            iCuentaService.transferir(1L, 2L, new BigDecimal("100"),1L);
        //});
        saldoOrigen = iCuentaService.revisarSaldoCuenta(1L);
        saldoDestino = iCuentaService.revisarSaldoCuenta(2L);
        assertEquals("900", saldoOrigen.toPlainString());
        assertEquals("2100", saldoDestino.toPlainString());
        //assertEquals(1, iBancoRepository.findById(1L).getTotalTransferencias());//sin clase de implement
        int totalTransferencias = iCuentaService.revisarTotalTransferencias(1L);
        assertEquals(1, totalTransferencias);//con implement
        verify(iCuentaRepository, times(3)).findById(1L);
        verify(iCuentaRepository, times(2)).save(any(Cuenta.class));
        verify(iBancoRepository).save(any(Banco.class));
    }
    @Test
    void contextLoads2() {
        //Crear contexto de prueba
        when(iCuentaRepository.findById(1L)).thenReturn(crearCuenta01());
        Cuenta cuenta1 = iCuentaService.findById(1L);
        Cuenta cuenta2 = iCuentaService.findById(1L);
        assertSame(cuenta1, cuenta2);
        assertSame("Ferru", cuenta1.getPersona());
        verify(iCuentaRepository, times(2)).findById(1L);
    }

}

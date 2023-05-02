package com.sebapp.apptesting;

import com.sebapp.apptesting.models.Cuenta;
import com.sebapp.apptesting.repositories.ICuentaRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class IntegracionJpaTest {
    @Autowired
    ICuentaRepository iCuentaRepository;
    @Test
    void testFindById() {
        Optional<Cuenta> cuenta = iCuentaRepository.findById(1L);
        assertTrue(cuenta.isPresent());
        assertEquals("Ferru", cuenta.orElseThrow().getPersona());
    }

    @Test
    void testFindByPersona() {
        Optional<Cuenta> cuenta = iCuentaRepository.findByPersona("Ferru");
        assertTrue(cuenta.isPresent());
        assertEquals("Ferru", cuenta.orElseThrow().getPersona());
        assertEquals("1000.00", cuenta.orElseThrow().getSaldo().toPlainString());
    }

    @Test
    void testFindAll() {
        List<Cuenta> cuentas = iCuentaRepository.findAll();
        assertFalse(cuentas.isEmpty());
        assertEquals(3, cuentas.size());
    }

    @Test
    void testSave() {
        Cuenta cuentaAna = new Cuenta(null, "Ana", new BigDecimal("4000"));
        iCuentaRepository.save(cuentaAna);
        Cuenta cuenta = iCuentaRepository.findByPersona("Ana").orElseThrow();
        assertEquals("Ana", cuenta.getPersona());
        assertEquals("4000", cuenta.getSaldo().toPlainString());
        assertEquals(4, cuenta.getId());
    }

    @Test
    void testUpdate() {
        Cuenta cuentaAna = new Cuenta(null, "Ana", new BigDecimal("3800"));
        iCuentaRepository.save(cuentaAna);
        Cuenta cuenta = iCuentaRepository.findByPersona("Ana").orElseThrow();
        assertEquals("Ana", cuenta.getPersona());
        assertEquals("3800", cuenta.getSaldo().toPlainString());
        cuenta.setSaldo(new BigDecimal("3900"));
        Cuenta cuentaAnaUpdate = iCuentaRepository.save(cuenta);
        assertEquals("3900", cuentaAnaUpdate.getSaldo().toPlainString());
    }

    @Test
    void testDelete() {
        Cuenta cuenta = iCuentaRepository.findById(3L).orElseThrow();
        assertEquals("Pedro", cuenta.getPersona());
        iCuentaRepository.delete(cuenta);
        assertEquals(2, iCuentaRepository.findAll().size());
        /*Cuenta cuentaDelete = iCuentaRepository.findByPersona("Pedro").orElseThrow();
        assertEquals("Pedro",cuentaDelete.getPersona());*/
    }
}

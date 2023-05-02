package com.sebapp.apptesting.repositories;

import com.sebapp.apptesting.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {
    @Query("SELECT c from Cuenta c where c.persona=?1") Optional<Cuenta> findByPersona(String persona);
}

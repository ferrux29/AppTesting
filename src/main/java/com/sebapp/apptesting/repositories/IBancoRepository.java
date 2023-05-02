package com.sebapp.apptesting.repositories;

import com.sebapp.apptesting.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBancoRepository extends JpaRepository<Banco, Long> {

}

package com.adjt.pacienteregistroservice.persistence;

import com.adjt.pacienteregistroservice.entity.RegistroMedicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistroMedicaoRepository extends JpaRepository<RegistroMedicao, UUID> {
}

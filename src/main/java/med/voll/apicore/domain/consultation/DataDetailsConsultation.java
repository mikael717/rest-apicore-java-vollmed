package med.voll.apicore.domain.consultation;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public record DataDetailsConsultation(Long id, Long idDoctor, Long idPatient, LocalDateTime dateTime) {
}

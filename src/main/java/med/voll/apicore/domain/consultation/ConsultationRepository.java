package med.voll.apicore.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    boolean existsByDoctorIdAndDateTime(Long idDoctor, @NotNull @Future LocalDateTime dateTime);

    boolean existsByPatientIdAndDateTimeBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);
}

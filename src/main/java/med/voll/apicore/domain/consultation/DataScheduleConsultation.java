package med.voll.apicore.domain.consultation;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.apicore.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record DataScheduleConsultation(

    @JsonAlias("doctor_id")
    Long idDoctor,

    @NotNull
    @JsonAlias({"patient_id", "id_patient"})
    Long idPatient,

    @NotNull
    @Future
    @JsonAlias("date")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")//default 2025-11-11T10:20
    LocalDateTime dateTime,

    Specialty specialty
    ) {

}

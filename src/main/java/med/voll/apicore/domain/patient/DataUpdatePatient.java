package med.voll.apicore.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.apicore.address.DataAddress;

public record DataUpdatePatient(
        @NotNull
        Long id,
        String name,
        String phone,
        @Valid
        DataAddress address) {
}

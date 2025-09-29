package med.voll.apicore.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.apicore.address.DataAddress;

public record DataUpdateDoctor(
        @NotNull
        Long id,
        String name,
        String phone,
        @Valid
        DataAddress address) {
}

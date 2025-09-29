package med.voll.apicore.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.apicore.domain.address.DataAddress;

public record DataRegisterDoctor(
        @NotBlank(message = "{mandatory.name}")
        String name,
        @NotBlank(message = "{mandatory.email}")
        @Email(message = "{invalid.name}")
        String email,
        @NotBlank(message = "{mandatory.phone}")
        String phone,
        @NotBlank(message = "{mandatory.crm}")
        @Pattern(regexp = "\\d{4,6}", message = "{invalid.crm}")
        String crm,
        @NotNull(message = "{mandatory.specialty}")
        Specialty specialty,
        @NotNull(message = "{mandatory.address}")
        @Valid
        DataAddress address) {
}

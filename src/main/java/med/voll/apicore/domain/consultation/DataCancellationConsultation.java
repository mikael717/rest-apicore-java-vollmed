package med.voll.apicore.domain.consultation;

import jakarta.validation.constraints.NotNull;

public record DataCancellationConsultation(
        @NotNull
        Long idConsultation,

        @NotNull
        CancellationReason reason) {
}

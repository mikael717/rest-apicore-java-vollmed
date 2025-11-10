package med.voll.apicore.domain.doctor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pagination);


    @Query("""
            select d from Doctor d
            where
                d.active = true
                and
                d.specialty = :specialty
                and
                d.id not in(
                    select c.doctor.id from Consultation c
                    where
                    c.dateTime = :dateTime
                )
            order by rand()
            limit 1
            """)
    Doctor chooseFreeRandomDoctorOnTheDate(@Param("specialty") Specialty specialty, @NotNull @Future LocalDateTime dateTime);

    @Query("""
            select d.active
            from Doctor d
            where
            d.id = :id
            """)
    Boolean findActiveById(Long idDoctor);
}

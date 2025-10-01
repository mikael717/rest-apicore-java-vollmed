package med.voll.apicore.controller;

import jakarta.validation.Valid;
import med.voll.apicore.domain.doctor.DataDetailsDoctor;
import med.voll.apicore.domain.doctor.DataListingDoctor;
import med.voll.apicore.domain.doctor.DataRegisterDoctor;
import med.voll.apicore.domain.doctor.DataUpdateDoctor;
import med.voll.apicore.domain.doctor.Doctor;
import med.voll.apicore.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterDoctor data, UriComponentsBuilder uriBuilder){
        var doctor = new Doctor(data);
        repository.save(doctor);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsDoctor(doctor));
    }

    @GetMapping
    public ResponseEntity <Page<DataListingDoctor>> listing(@PageableDefault(sort = {"name"}, size = 10) Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map(DataListingDoctor::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestBody @Valid DataUpdateDoctor data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInformation(data);

        return ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.logicalDelete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailsDoctor(doctor));
    }
}

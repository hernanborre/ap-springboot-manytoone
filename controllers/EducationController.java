package ar.com.yoprogramo.api.controllers;

import ar.com.yoprogramo.api.models.Education;
import ar.com.yoprogramo.api.services.EducationService;
import ar.com.yoprogramo.api.services.PersonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/education")
public class EducationController {

    @Autowired
    PersonService personService;

    @Autowired
    EducationService educationService;

    @GetMapping("/all")
    public ResponseEntity<List<Education>> getAllEducation() {
        List<Education> educationList = educationService.getAllEducation();
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable(value = "id") Long id) {
        Education education = educationService.getEducationByID(id);
        return new ResponseEntity<>(education, HttpStatus.OK);
    }

    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Education>> getAllEducationByPersonId(@PathVariable(value = "person_id") Long personId) {
        List<Education> educationList = new ArrayList<>();
        if (personService.getPersonByID(personId) != null) {
            educationList = educationService.getEducationByPersonId(personId);
            return new ResponseEntity<>(educationList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(educationList, HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/person/{person_id}/education")
//    public ResponseEntity<Education> createEducation(@PathVariable(value = "person_id") Long personId,
//            @RequestBody Education educationRequest) {
//        Education education = personService.findById(personId).map(person -> {
//            educationRequest.setPerson(person);
//            return educationService.save(educationRequest);
//        })
//        return new ResponseEntity<>(education, HttpStatus.CREATED);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Education> updateComment(@PathVariable("id") long id, @RequestBody Education educationRequest) {
        Education education = educationService.getEducationByID(id);
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
        education.setPerson(educationRequest.getPerson());
        return new ResponseEntity<>(educationService.save(education), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEducation(@PathVariable("id") long id) {
        educationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

package ar.com.yoprogramo.api.controllers;

import ar.com.yoprogramo.api.models.Person;
import ar.com.yoprogramo.api.repositories.PersonRepository;
import ar.com.yoprogramo.api.services.PersonService;
import java.util.ArrayList;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/all")
    public ArrayList<Person> getAllPersons() {
//        ArrayList<Person> listaAuxPersonas = 
//        FOR (unaPersona of listaAuxPersonas) {
//            IF unaPersona.EDAD < 18
//                listaAuxPersonas.REMOVE
//        } 
        return personService.getAllPersons();
    }

    @PostMapping()
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable("id") Long id, @RequestBody Person personTochange) {

        Person p = personService.findById(id);
        personTochange.setId(p.getId());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(personTochange, p);
 
        return personService.savePerson(p);
    }

    @GetMapping("/{id}")
    public Person getPersonByID(@PathVariable("id") Long id) {
        return personService.getPersonByID(id);
    }

    @GetMapping("/query")
    public ArrayList<Person> getPersonByApellido(@RequestParam("apellido") String apellido) {
        return personService.getPersonByApellido(apellido);
    }

    @DeleteMapping("/{id}")
    public String removePerson(@PathVariable("id") Long id) {
        if (personService.removePerson(id)) {
            return "Se elimin� a la persona de id " + id + " correctamente";
        } else {
            return "La persona no existe o no pudo ser eliminada";
        }
    }

}

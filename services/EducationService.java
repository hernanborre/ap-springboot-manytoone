
package ar.com.yoprogramo.api.services;

import ar.com.yoprogramo.api.models.Education;
import ar.com.yoprogramo.api.repositories.EducationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

    @Autowired
    EducationRepository educationRepository;

    public ArrayList<Education> getAllEducation() {
        return (ArrayList<Education>) educationRepository.findAll();
    }

    public Education save(Education education) {
        return educationRepository.save(education);
    }

    public Education getEducationByID(Long id) {
        return educationRepository.findById(id).get();
    }

    public boolean deleteById(Long id) {
        try {
            educationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Education> getEducationByPersonId(Long personId) {
           return educationRepository.findAllByPersonId(personId);
    }

}

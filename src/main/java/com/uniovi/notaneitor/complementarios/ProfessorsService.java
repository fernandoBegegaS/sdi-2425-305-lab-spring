package com.uniovi.notaneitor.complementarios;

import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {

    private List<Professor> professors = new ArrayList<>();
    Long contadorId = 1L;
    private final UsersRepository usersRepository;

    public ProfessorsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    // Obtener la lista de profesores
    public List<User> getProfessorsList() {

        return usersRepository.findByRole("ROLE_PROFESSOR");
    }

    // Obtener un profesor por ID
    public Professor getProfessor(Long id) {
        for (Professor professor : professors) {
            if (professor.getId().equals(id)) {
                return professor;
            }
        }
        return null;
    }

    public void addProfessor(Professor professor) {

        professor.setId(contadorId);
        professors.add(professor);
        contadorId++;
    }

    public void deleteProfessor(Long id) {
        for (int i = 0; i < professors.size(); i++) {
            if (professors.get(i).getId().equals(id)) {
                professors.remove(i);
                break;
            }
        }
    }

    public void editProfessor(Professor updatedProfessor) {
        for (int i = 0; i < professors.size(); i++) {
            if (professors.get(i).getId().equals(updatedProfessor.getId())) {
                professors.set(i, updatedProfessor);
                break;
            }
        }
    }
}

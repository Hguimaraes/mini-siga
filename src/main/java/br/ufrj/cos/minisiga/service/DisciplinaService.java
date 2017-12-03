package br.ufrj.cos.minisiga.service;

import br.ufrj.cos.minisiga.domain.Disciplina;
import br.ufrj.cos.minisiga.repository.DisciplinaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing Disciplina.
 */
@Service
@Transactional
public class DisciplinaService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    /**
     *
     * @param disciplina
     * @return state: True if all the parameters are consolidate with the system rules
     */
    static public Boolean validateParameters(Disciplina disciplina){
        Boolean state = true;

        if(disciplina.getCargaHoraria() > 6){
            state = false;
        }

        if(disciplina.getQtdCreditos() < 1 || disciplina.getQtdCreditos() > 6) {
            state = false;
        }

        return state;
    }
}

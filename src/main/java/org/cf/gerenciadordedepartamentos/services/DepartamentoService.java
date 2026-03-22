package org.cf.gerenciadordedepartamentos.services;

import org.cf.gerenciadordedepartamentos.models.DepartamentoModel;
import org.cf.gerenciadordedepartamentos.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public DepartamentoModel save(DepartamentoModel departamentoModel) {
        return departamentoRepository.save(departamentoModel);
    }

    public List<DepartamentoModel> findAll(){
        return departamentoRepository.findAll();
    }

    public void delete(Long id) {
        departamentoRepository.deleteById(id);
    }

    public DepartamentoModel update(Long id, DepartamentoModel departamentoModel) {
        Optional<DepartamentoModel> departamentoBuscado = departamentoRepository.findById(id);

        if(departamentoBuscado.isPresent()){
            DepartamentoModel departamentoExistente = departamentoBuscado.get();

            departamentoExistente.setLocalizacao(departamentoModel.getLocalizacao());
            departamentoExistente.setNome(departamentoModel.getNome());
            return departamentoRepository.save(departamentoExistente);
        }
        return null;
    }

    public DepartamentoModel findById(Long id) {
        return departamentoRepository.findById(id).orElse(null);
    }
}

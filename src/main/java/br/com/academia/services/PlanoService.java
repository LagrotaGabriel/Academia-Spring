package br.com.academia.services;

import br.com.academia.exceptions.NaoEncontrado;
import br.com.academia.models.entities.Plano;
import br.com.academia.repositories.PlanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoService {

    @Autowired
    PlanoRepository planoRepository;

    public Plano create(Plano plano){
        return planoRepository.save(plano);
    }

    public List<Plano> readAll(){
        return planoRepository.findAll();
    }

    public Plano byId(Long id){
        return planoRepository.findById(id).orElseThrow(() -> new NaoEncontrado("Plano não encontrado"));
    }

    public Plano update(Long id, Plano plano){
        try{
            Plano finded = byId(id);
            finded.setTipoPlano(plano.getTipoPlano());
            finded.setDataAssinatura(plano.getDataAssinatura());
            return create(finded);
        }
        catch (Exception e){
            return null;
        }
    }

    public Boolean delete(Long id){
        try{
            planoRepository.delete(byId(id));
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}

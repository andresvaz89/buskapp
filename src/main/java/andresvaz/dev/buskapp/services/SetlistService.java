package andresvaz.dev.buskapp.services;

import andresvaz.dev.buskapp.entities.Setlist;
import andresvaz.dev.buskapp.repositories.SetlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetlistService {

    @Autowired
    private SetlistRepository setlistRepository;

    // Crear un nuevo Setlist
    public Setlist createSetlist(Setlist setlist) {
        return setlistRepository.save(setlist);
    }

    // Obtener todos los Setlists
    public List<Setlist> getAllSetlists() {
        return setlistRepository.findAll();
    }

    // Obtener un Setlist por ID
    public Optional<Setlist> getSetlistById(Long id) {
        return setlistRepository.findById(id);
    }

    // Actualizar un Setlist
    public Setlist updateSetlist(Long id, Setlist setlist) {
        if (setlistRepository.existsById(id)) {
            setlist.setId(id);
            return setlistRepository.save(setlist);
        }
        return null;
    }

    // Eliminar un Setlist por ID
    public boolean deleteSetlist(Long id) {
        if (setlistRepository.existsById(id)) {
            setlistRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

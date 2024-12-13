package andresvaz.dev.buskapp.controllers;

import andresvaz.dev.buskapp.entities.Setlist;
import andresvaz.dev.buskapp.services.SetlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/setlist")
public class SetlistController {

    @Autowired
    private SetlistService setlistService;

   
    @PostMapping
    public ResponseEntity<Setlist> createSetlist(@RequestBody Setlist setlist) {
        Setlist createdSetlist = setlistService.createSetlist(setlist);
        return ResponseEntity.ok(createdSetlist);
    }

   
    @GetMapping
    public ResponseEntity<List<Setlist>> getAllSetlists() {
        return ResponseEntity.ok(setlistService.getAllSetlists());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Setlist> getSetlist(@PathVariable Long id) {
        Optional<Setlist> setlist = setlistService.getSetlistById(id);
        return setlist.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<Setlist> updateSetlist(@PathVariable Long id, @RequestBody Setlist setlist) {
        Setlist updatedSetlist = setlistService.updateSetlist(id, setlist);
        return updatedSetlist != null
            ? ResponseEntity.ok(updatedSetlist)
            : ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSetlist(@PathVariable Long id) {
        boolean deleted = setlistService.deleteSetlist(id);
        return deleted 
            ? ResponseEntity.ok("Setlist eliminado correctamente.")
            : ResponseEntity.notFound().build();
    }
}

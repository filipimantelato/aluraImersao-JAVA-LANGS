package br.com.alura.langsapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LanguageController {
    
    @Autowired
    private LangRepository repository;
    
    //juntos = CRUD
    @GetMapping("/languages") //nome qualquer
        public List<langs> obterLinguagem() { 
            List<langs> languages = repository.findByOrderByRanking();
            return languages;
        }

    @PostMapping("/languages")
        public ResponseEntity<langs> cadastrarLinguagem(@RequestBody langs languages) {
            langs saveLanguage = repository.save(languages);
            return new ResponseEntity<>(saveLanguage, HttpStatus.CREATED);
        }

    @GetMapping("/languages/{id}")
        public langs obterLinguagemPorId(@PathVariable String id) {
            return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

    @PutMapping("/languages/{id}")
        public langs editarLinguagem(@PathVariable String id, @RequestBody langs languages) {
            if(!repository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            languages.setId(id);
            langs saveLanguage = repository.save(languages);
            return saveLanguage;
        }

    @DeleteMapping("/languages/{id}")
        public void excluirLinguagem(@PathVariable String id) {
            repository.deleteById(id);
        }
}

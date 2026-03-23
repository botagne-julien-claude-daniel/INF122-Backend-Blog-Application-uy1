package com.inf222.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}

@Entity
class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Column(columnDefinition = "TEXT")
    private String contenu;
    private String auteur;
    private LocalDate date = LocalDate.now();
    private String categorie;
    private String tags;

    public Article() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public LocalDate getDate() { return date; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }
}

interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByCategorie(String categorie);
    List<Article> findByTitreContainingOrContenuContaining(String t, String c);
}

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")
class ArticleController {
    private final ArticleRepository repository;
    public ArticleController(ArticleRepository repository) { this.repository = repository; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article create(@RequestBody Article a) { return repository.save(a); }

    @GetMapping
    public List<Article> getAll(@RequestParam(required = false) String categorie) {
        return (categorie != null) ? repository.findByCategorie(categorie) : repository.findAll();
    }

    @GetMapping("/{id}")
    public Article getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public List<Article> search(@RequestParam String query) {
        return repository.findByTitreContainingOrContenuContaining(query, query);
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable Long id, @RequestBody Article art) {
        art.setId(id);
        return repository.save(art);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { repository.deleteById(id); }
}

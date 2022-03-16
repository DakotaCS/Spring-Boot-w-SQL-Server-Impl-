package com.example.springsqlwrest.controller;

import com.example.springsqlwrest.dto.tutorialDto;
import com.example.springsqlwrest.model.Tutorial;
import com.example.springsqlwrest.repository.tutorialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class tutorialController {

    @Autowired
    tutorialRepo tutorialRepository;
    @GetMapping("/tutorials/getAll")
    public ResponseEntity<Iterable<Tutorial>> getAllTutorials() {
            try {
                if (tutorialRepository.count() == 0)
                {
                    System.out.println("No content found");
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
                else
                    System.out.println("Tutorial list displayed");
                    return new ResponseEntity<>(tutorialRepository.findAll(), HttpStatus.OK);
            }
            catch (Exception e) {
                System.out.println("Internal server error");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/tutorials/getById-{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            System.out.println("Tutorial with id " + id + " found");
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        }
        else {
            System.out.println("Tutorial with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tutorials/getByTitle")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required=true) String title) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
            if (tutorials.isEmpty()) {
                System.out.println("No content found");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("Tutorial with title " + title + " found");
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Internal server error");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Does a similar operation as @GetMapping("/tutorials/getAll") but using POST rather than GET.
    //Also demonstrates the use of a simple DTO
    //utilize a simple DTO rather than accessing the model class directly
    @PostMapping("/tutorials/displayAll")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody tutorialDto tutorial) {
        try {
            Tutorial _tutorial = tutorialRepository.save(new Tutorial
                    (tutorial.title, tutorial.description, tutorial.published));
            System.out.println("Tutorial creation via DTO succeeded");
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        }
        catch (Exception e){
            System.out.println("Tutorial creation via DTO failed");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials/deleteById-{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialRepository.deleteById(id);
            System.out.println("Tutorial Id# " + id + " deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Tutorial deletion failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            System.out.println("Tutorial list deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            System.out.println("Tutorial list delete failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/tutorials/findByPublished")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        try {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                System.out.println("No content displayed");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("Published tutorials displayed");
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Internal server error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
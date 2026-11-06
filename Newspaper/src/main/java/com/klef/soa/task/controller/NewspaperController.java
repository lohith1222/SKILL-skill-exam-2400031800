package com.klef.soa.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.task.entity.Newspaper;
import com.klef.soa.task.service.NewspaperService;

@RestController
@RequestMapping("/newspaper")
public class NewspaperController
{
    @Autowired
    private NewspaperService service;

    @GetMapping("/")
    public String demo()
    {
        return "Spring Boot Project";
    }

    @PostMapping("/add")
    public ResponseEntity<Newspaper> addNewspaper(@RequestBody Newspaper newspaper)
    {
        Newspaper n = service.insert(newspaper);
        return ResponseEntity.status(HttpStatus.CREATED).body(n);
    }

    
    @GetMapping("/displayall")
    public ResponseEntity<List<Newspaper>> displayAllNewspapers()
    {
        List<Newspaper> newspapers = service.getAll();
        return ResponseEntity.ok(newspapers);
    }

    
    @GetMapping("/display")
    public ResponseEntity<?> displayNewspaperById(@RequestParam Long id)
    {
        Newspaper newspaper = service.getById(id);

        if (newspaper != null)
        {
            return ResponseEntity.ok(newspaper);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Newspaper ID Not Found");
        }
    }

  
    @PutMapping("/update")
    public ResponseEntity<?> updateNewspaper(@RequestBody Newspaper newspaper)
    {
        Newspaper n = service.update(newspaper.getNewspaperId(), newspaper);

        if (n != null)
        {
            return ResponseEntity.ok(n);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Newspaper ID Not Found");
        }
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNewspaper(@PathVariable Long id)
    {
        service.delete(id);
        return new ResponseEntity<>("Newspaper Deleted Successfully", HttpStatus.OK);
    }

  
    @GetMapping("/displaybyname/{name}")
    public ResponseEntity<List<Newspaper>> displayNewspaperByName(@PathVariable String name)
    {
        List<Newspaper> newspapers = service.findByName(name);
        return ResponseEntity.ok(newspapers);
    }

  
    @GetMapping("/displaybytype/{type}")
    public ResponseEntity<List<Newspaper>> displayNewspaperByType(@PathVariable String type)
    {
        List<Newspaper> newspapers = service.findByType(type);
        return ResponseEntity.ok(newspapers);
    }
}
package ru.rfma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rfma.services.CoreService;


@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CoreService coreService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> create(@RequestParam final String name, @RequestParam(required = false) final Float limit){
        try {
            return new ResponseEntity<>(this.coreService.createCategory(name, limit), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byid")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> get(@RequestParam final Integer id) {
        try {
            return new ResponseEntity<>(this.coreService.getCategoryById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byname")
    public ResponseEntity<?> get(@RequestParam final String name) {
        try {
            return new ResponseEntity<>(this.coreService.getCategoryByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updatelimit")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> update(@RequestParam final Integer id, @RequestParam final Float limit) {
        try {
            return new ResponseEntity<>(this.coreService.updateCategoryLimit(id, limit), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> delete(@RequestParam final Integer id) {
        try {
            this.coreService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

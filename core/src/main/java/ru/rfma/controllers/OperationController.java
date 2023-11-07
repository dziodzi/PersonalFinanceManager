package ru.rfma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rfma.dto.OperationDto;
import ru.rfma.services.CoreService;

@Controller("/operation")
public class OperationController {

    @Autowired
    private CoreService coreService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody OperationDto operationDto) {
        try {
            return new ResponseEntity<>(coreService.createOperation(operationDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getById(@RequestParam final Integer id) {
        try {
            return new ResponseEntity<>(this.coreService.getOperationById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<>(this.coreService.getAllOperations(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam final Integer id) {
        try {
            this.coreService.deleteOperationById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

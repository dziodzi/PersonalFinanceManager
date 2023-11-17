package ru.rfma.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rfma.core.dto.OperationDto;
import ru.rfma.core.services.CoreServiceImpl;

@Controller
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private CoreServiceImpl coreServiceImpl;

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> create(@RequestBody OperationDto operationDto) {
        try {
            return new ResponseEntity<>(coreServiceImpl.createOperation(operationDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> getById(@RequestParam final Integer id) {
        try {
            return new ResponseEntity<>(this.coreServiceImpl.getOperationById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> getAll(){
        try {
            return new ResponseEntity<>(this.coreServiceImpl.getAllOperations(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> delete(@RequestParam final Integer id) {
        try {
            this.coreServiceImpl.deleteOperationById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

package com.example.ResponseEntityExceptionHandling.Controller;

import com.example.ResponseEntityExceptionHandling.Model.Pet;
import com.example.ResponseEntityExceptionHandling.Service.IPetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {

    private final IPetService petService;

    public PetController(IPetService petService) {
        this.petService = petService;
    }

    @GetMapping(value = "pets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pet>> getPets() throws Exception {
        List<Pet> pets = petService.getPets();
        return new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
    }

    @GetMapping(value = "pets/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> getPet(@PathVariable(name = "name") String name) throws Exception {
        return new ResponseEntity<Pet>(petService.getPet(name), HttpStatus.OK);
    }

    @PostMapping(value = "pets"
            , produces = MediaType.APPLICATION_JSON_VALUE
            , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPet(@RequestBody Pet pet) throws Exception{
        if(petService.addPet(pet)){
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else{
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "pets/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable(name = "id") int id) throws Exception{
        petService.deletePet(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping(value = "pets", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePet(@RequestBody Pet pet) throws Exception{
        petService.updatePet(pet);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}

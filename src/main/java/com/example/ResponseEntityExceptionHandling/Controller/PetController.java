package com.example.ResponseEntityExceptionHandling.Controller;

import com.example.ResponseEntityExceptionHandling.Model.Pet;
import com.example.ResponseEntityExceptionHandling.Service.IPetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pet controller")
@RestController
public class PetController {

    private final IPetService petService;

    public PetController(IPetService petService) {
        this.petService = petService;
    }

    @Operation(summary = "Pets", description = "Return the list of pets")
    @GetMapping(value = "pets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pet>> getPets() throws Exception {
        List<Pet> pets = petService.getPets();
        return new ResponseEntity<List<Pet>>(pets, HttpStatus.OK);
    }

    @Operation(summary = "Pet by name", description = "Return a pet by name")
    @GetMapping(value = "pets/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pet> getPet(
            @Parameter(description = "Pet name") @PathVariable(name = "name") String name) throws Exception {
        return new ResponseEntity<Pet>(petService.getPet(name), HttpStatus.OK);
    }

    @Operation(summary = "Add pet", description = "Add a pet received in request body")
    @PostMapping(value = "pets"
            , produces = MediaType.APPLICATION_JSON_VALUE
            , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPet(
            @Parameter(description = "JSON object to add") @RequestBody Pet pet) throws Exception {

        if (petService.addPet(pet)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Delete pet", description = "Delete pet by id")
    @DeleteMapping(value = "pets/{id}")
    public ResponseEntity<Void> deletePet(
            @Parameter(description = "Pet id to delete") @PathVariable(name = "id") int id) throws Exception {
        petService.deletePet(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @Operation(summary = "Update pet", description = "Update pet received in request body")
    @PutMapping(value = "pets", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePet(
            @Parameter(description = "JSON object to update") @RequestBody Pet pet) throws Exception {
        petService.updatePet(pet);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}

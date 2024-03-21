package com.example.ResponseEntityExceptionHandling.Service;

import com.example.ResponseEntityExceptionHandling.Model.Pet;

import java.util.List;

public interface IPetService {

    List<Pet> getPets();
    Pet getPet(String name);
    boolean addPet(Pet pet);
    void deletePet(int id);
    void updatePet(Pet pet);

}

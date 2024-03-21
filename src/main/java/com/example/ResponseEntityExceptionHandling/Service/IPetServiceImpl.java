package com.example.ResponseEntityExceptionHandling.Service;

import com.example.ResponseEntityExceptionHandling.Model.Pet;
import com.example.ResponseEntityExceptionHandling.Repository.IPetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPetServiceImpl implements IPetService{

    private final IPetRepository petRepository;

    public IPetServiceImpl(IPetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPet(String name) {
        return (Pet) getPets().stream()
                .filter(pet -> pet.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean addPet(Pet pet) {

        if(petRepository.findById(pet.getId()).isEmpty()){
            petRepository.save(pet);
            return true;
        }
            return false;
    }

    @Override
    public void deletePet(int id) {
        petRepository.deleteById(id);
    }

    @Override
    public void updatePet(Pet pet) {
        if(petRepository.findById(pet.getId()).isPresent()){
            petRepository.save(pet);
        }
    }
}

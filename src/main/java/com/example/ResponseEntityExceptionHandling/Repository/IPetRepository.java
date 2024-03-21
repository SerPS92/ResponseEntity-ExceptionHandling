package com.example.ResponseEntityExceptionHandling.Repository;

import com.example.ResponseEntityExceptionHandling.Model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPetRepository extends JpaRepository<Pet, Integer> {
}

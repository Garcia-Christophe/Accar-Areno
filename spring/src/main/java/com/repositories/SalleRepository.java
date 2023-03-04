package com.repositories;

import com.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
    Interface de repository pour la classe Salle.
    Cette interface étend JpaRepository qui fournit des méthodes CRUD pour l'entité Salle.
	*/
@Repository
public interface SalleRepository extends JpaRepository<Salle, Integer> {
}

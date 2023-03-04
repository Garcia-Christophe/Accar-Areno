package com.repositories;

import com.entities.Soiree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

	/**
    Interface de repository pour la classe Soiree.
    Cette interface étend JpaRepository qui fournit des méthodes CRUD pour l'entité Soiree.
	*/
@Repository
public interface SoireeRepository extends JpaRepository<Soiree, Integer> {
}

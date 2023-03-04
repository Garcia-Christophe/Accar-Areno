package com.repositories;

import com.entities.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 Interface de repository pour la classe Concert.
 Cette interface étend JpaRepository qui fournit des méthodes CRUD pour l'entité Concert.
 */
@Repository
public interface ConcertRepository extends JpaRepository<Concert, Integer> {
}

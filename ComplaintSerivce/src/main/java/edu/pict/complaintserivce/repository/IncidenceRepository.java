package edu.pict.complaintserivce.repository;

import edu.pict.complaintserivce.model.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncidenceRepository extends JpaRepository<Incidence, UUID> {
}

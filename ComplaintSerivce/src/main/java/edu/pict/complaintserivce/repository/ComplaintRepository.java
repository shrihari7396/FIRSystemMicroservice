package edu.pict.complaintserivce.repository;

import edu.pict.complaintserivce.model.Complaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, UUID> {
    void deleteByVictim(UUID victim);
}

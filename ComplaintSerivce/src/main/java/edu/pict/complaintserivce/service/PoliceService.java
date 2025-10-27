package edu.pict.complaintserivce.service;

import edu.pict.complaintserivce.model.Complaint;
import edu.pict.complaintserivce.model.enums.ComplaintStatus;
import edu.pict.complaintserivce.repository.ComplaintRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PoliceService {

    private final ComplaintRepository complaintRepository;

    // Police Tasks
    public Boolean updateComplaintStatus(UUID complaintId, ComplaintStatus status) {
        Optional<Complaint> complaint = complaintRepository.findById(complaintId);
        if(complaint.isEmpty()) {
            throw new NotFoundException("Complaint not found");
        }
        complaint.get().setStatus(status);
        complaintRepository.save(complaint.get());
        return true;
    }

    public void deleteComplaintByVictum(UUID id) {
        complaintRepository.deleteByVictim(id);
    }
}

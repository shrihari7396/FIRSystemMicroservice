package edu.pict.complaintserivce.service;

import edu.pict.complaintserivce.model.Complaint;
import edu.pict.complaintserivce.repository.ComplaintRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VictimService {

    private final ComplaintRepository complaintRepository;

    // Victim Tasks
    public Complaint addComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(Complaint complaint) {
        Optional<Complaint> newComplaint = complaintRepository.findById(complaint.getComplaintId());
        if (newComplaint.isEmpty()) {
            throw new NotFoundException("Complaint not found");
        }
        return complaintRepository.save(newComplaint.get());
    }


}


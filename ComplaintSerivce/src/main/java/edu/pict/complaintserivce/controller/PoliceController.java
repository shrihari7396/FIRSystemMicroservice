package edu.pict.complaintserivce.controller;

import edu.pict.complaintserivce.model.enums.ComplaintStatus;
import edu.pict.complaintserivce.service.PoliceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/police")
public class PoliceController {
    private PoliceService policeService;

    @PutMapping("/updateComplaintStatus")
    public ResponseEntity<?> updateComplaintStatus(
            @RequestParam UUID complaintId,
            @RequestParam ComplaintStatus status) {
        boolean check = policeService.updateComplaintStatus(complaintId, status);
        if (!check) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Complaint not found");
        }
        return ResponseEntity.ok("Complaint status updated successfully");
    }

    @DeleteMapping("/deleteByVictumId/{victimId}")
    public ResponseEntity<?> deleteByVictimId(@PathVariable UUID victimId) {
        policeService.deleteComplaintByVictum(victimId);
        return ResponseEntity.ok("Victim id deleted successfully");
    }
}

package edu.pict.complaintserivce.controller;

import edu.pict.complaintserivce.model.Complaint;
import edu.pict.complaintserivce.service.VictimService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/victim")
@RequiredArgsConstructor
public class VictimController {
    private final VictimService victimService;

    @PostMapping("/addComplaint")
    public ResponseEntity<?> addComplaint(Complaint complaint) {
        Complaint newComplaint = null;
        try {
          newComplaint = victimService.addComplaint(complaint);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(newComplaint);
    }

    @PutMapping("/updateComplaint")
    public ResponseEntity<?> updateComplaint(Complaint complaint) {
        Complaint newComplaint = null;
        try {
            newComplaint = victimService.updateComplaint(complaint);
        }  catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(newComplaint);
    }
}

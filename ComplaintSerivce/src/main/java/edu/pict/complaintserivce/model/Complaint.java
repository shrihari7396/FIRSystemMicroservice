package edu.pict.complaintserivce.model;

import edu.pict.complaintserivce.model.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID complaintId;

    private UUID victim;

    @Column(nullable = true)
    private String evidenceLink;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;


}


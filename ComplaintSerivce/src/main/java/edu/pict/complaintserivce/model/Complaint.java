package edu.pict.complaintserivce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "accused_id")
//    private Person accused;

    @JoinColumn(name = "victim_id")
    private UUID victim;

    @Column(nullable = true)
    private String evidenceLink;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "incidence_id")
    private Incidence incidence;


//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "user_id", nullable = true)  // Add nullable = true here
//    private AppUser user;
}


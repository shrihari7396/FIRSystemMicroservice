package edu.pict.complaintserivce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidence {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String time;
    private String  date;
    @Embedded
    private Address address;
    @ManyToOne
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;
    @Column(length = 2000)
    private String description;
    @Column(nullable = true)
    private String crimetype;
}



package com.project.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class DSorular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "aciklama")
    private String aciklama;

    @Column(name = "baslik")
    private String baslik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
@JsonBackReference
    private DSorular parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
   // @JsonBackReference
    private List<DSorular> altBasliklar;


}

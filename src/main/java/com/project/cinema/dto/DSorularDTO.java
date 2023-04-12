package com.project.cinema.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.cinema.model.DSorular;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class DSorularDTO {


    private Long id;

    private String aciklama;

    private String baslik;

    private Long parentId;


    private List<DSorularDTOWithoutAltBaslik> altBasliklar;


}

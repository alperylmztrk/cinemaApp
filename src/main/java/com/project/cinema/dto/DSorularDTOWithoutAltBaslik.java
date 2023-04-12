package com.project.cinema.dto;

import com.project.cinema.model.DSorular;
import lombok.Data;

import java.util.List;

@Data
public class DSorularDTOWithoutAltBaslik {


    private Long id;

    private String aciklama;

    private String baslik;

    private Long parentId;


}

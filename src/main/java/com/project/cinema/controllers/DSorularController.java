package com.project.cinema.controllers;

import com.project.cinema.dto.DSorularDTO;
import com.project.cinema.dto.DSorularDTOWithoutAltBaslik;
import com.project.cinema.model.DSorular;
import com.project.cinema.repos.DSorularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("dSorular")
public class DSorularController {

    DSorularRepository dSorularRepository;

    @Autowired
    public DSorularController(DSorularRepository dSorularRepository) {
        this.dSorularRepository = dSorularRepository;
    }

    @GetMapping
    public List<DSorularDTO> getAllDsorularDto() {
        List<DSorular> dSorularList = dSorularRepository.findAll();
        List<DSorularDTO> dSorularDTOList = new ArrayList<>();

        dSorularList.forEach(soru -> {
            List<DSorularDTOWithoutAltBaslik> dSorularDTOWithoutAltBaslikList = new ArrayList<>();
            DSorularDTO dSorularDTO = new DSorularDTO();

            if (soru.getParent() == null) {
                dSorularDTO.setAciklama(soru.getAciklama());
                dSorularDTO.setBaslik(soru.getBaslik());
                dSorularDTO.setId(soru.getId());
                dSorularDTO.setParentId(null);
                System.out.println(soru.getBaslik());
                soru.getAltBasliklar().forEach(altBaslik -> {

                    DSorularDTOWithoutAltBaslik dSorularDTOWithoutAltBaslik = new DSorularDTOWithoutAltBaslik();
                    dSorularDTOWithoutAltBaslik.setBaslik(altBaslik.getBaslik());
                    dSorularDTOWithoutAltBaslik.setAciklama(altBaslik.getAciklama());
                    dSorularDTOWithoutAltBaslik.setId(altBaslik.getId());
                    dSorularDTOWithoutAltBaslik.setParentId(soru.getId());

                    dSorularDTOWithoutAltBaslikList.add(dSorularDTOWithoutAltBaslik);

                });

                dSorularDTO.setAltBasliklar(dSorularDTOWithoutAltBaslikList);
                dSorularDTOList.add(dSorularDTO);

            } else {
                dSorularDTO.setAciklama(soru.getAciklama());
                dSorularDTO.setBaslik(soru.getBaslik());
                dSorularDTO.setId(soru.getId());
                dSorularDTO.setParentId(soru.getParent().getId());
                dSorularDTO.setAltBasliklar(dSorularDTOWithoutAltBaslikList);
                dSorularDTOList.add(dSorularDTO);
            }
        });
        return dSorularDTOList;
    }

    @GetMapping("a")
    public List<DSorular> getAllDsorular() {


        return dSorularRepository.findAll();
    }

}

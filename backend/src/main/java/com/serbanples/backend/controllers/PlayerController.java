package com.serbanples.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.serbanples.backend.domain.dto.PlayerDto;
import com.serbanples.backend.domain.entities.PlayerEntity;
import com.serbanples.backend.mappers.Mapper;
import com.serbanples.backend.services.PlayerService;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private Mapper<PlayerEntity, PlayerDto> playerMapper;

}

package com.serbanples.backend.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serbanples.backend.domain.dto.PlayerDto;
import com.serbanples.backend.domain.entities.PlayerEntity;
import com.serbanples.backend.mappers.Mapper;
import com.serbanples.backend.services.PlayerService;

@RestController
@RequestMapping(path = "/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private Mapper<PlayerEntity, PlayerDto> playerMapper;

    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto) {
        PlayerEntity playerEntity = playerMapper.mapFrom(playerDto);
        PlayerEntity savedPlayerEntity = playerService.save(playerEntity);
        return new ResponseEntity<>(playerMapper.mapTo(savedPlayerEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PlayerDto> listPlayers() {
        List<PlayerEntity> authors = playerService.findall();
        return authors.stream()
            .map(playerMapper::mapTo)
            .collect(Collectors.toList());
    }

}

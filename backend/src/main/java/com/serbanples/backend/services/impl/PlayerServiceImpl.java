package com.serbanples.backend.services.impl;

import java.util.List;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serbanples.backend.domain.entities.PlayerEntity;
import com.serbanples.backend.repositories.PlayerRepository;
import com.serbanples.backend.services.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public PlayerEntity save(PlayerEntity playerEntity) {
        return playerRepository.save(playerEntity);
    }

    @Override
    public List<PlayerEntity> findall() {
        return StreamSupport.stream(playerRepository
            .findAll()
            .spliterator(),
            false)
        .collect(Collectors.toList());
    }

}

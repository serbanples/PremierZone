package com.serbanples.backend.services;

import java.util.List;

import com.serbanples.backend.domain.entities.PlayerEntity;

public interface PlayerService {

    PlayerEntity save(PlayerEntity playerEntity);

    List<PlayerEntity> findall();

}

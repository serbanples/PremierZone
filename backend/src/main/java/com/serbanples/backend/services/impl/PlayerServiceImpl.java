package com.serbanples.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serbanples.backend.repositories.PlayerRepository;
import com.serbanples.backend.services.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

}

package com.serbanples.backend.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.serbanples.backend.domain.dto.PlayerDto;
import com.serbanples.backend.domain.entities.PlayerEntity;
import com.serbanples.backend.mappers.Mapper;

public class PlayerMapperImpl implements Mapper<PlayerEntity, PlayerDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlayerDto mapTo(PlayerEntity playerEntity) {
        return modelMapper.map(playerEntity, PlayerDto.class);
    }

    @Override
    public PlayerEntity mapFrom(PlayerDto playerDto) {
        return modelMapper.map(playerDto, PlayerEntity.class);
    }

}

package com.edumoca.soma.entities.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserScheduleDto {
    private UserDto userDTO;
    private List<ScheduleDto> scheduleDTOSet;
}

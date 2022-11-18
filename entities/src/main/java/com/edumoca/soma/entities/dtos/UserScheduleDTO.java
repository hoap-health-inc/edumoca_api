package com.edumoca.soma.entities.dtos;

import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
public class UserScheduleDTO {
    private UserDTO userDTO;
    private List<ScheduleDTO> scheduleDTOSet;
}

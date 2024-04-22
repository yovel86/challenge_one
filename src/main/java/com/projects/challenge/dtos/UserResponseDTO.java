package com.projects.challenge.dtos;

import com.projects.challenge.models.User;
import lombok.Data;

@Data
public class UserResponseDTO {
    private User user;
    private ResponseStatus responseStatus;
}

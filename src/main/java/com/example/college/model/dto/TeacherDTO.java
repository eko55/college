package com.example.college.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TeacherDTO(
        @NotBlank(message = "personalNumber should not be blank") @Pattern(regexp = "[0-9]{10}", message = "personalNumber should consist of exactly 10 digits") String personalNumber,
        @NotBlank(message = "firstName should not be blank") String firstName,
        @NotBlank(message = "lastName should not be blank") String lastName,
        @NotNull(message = "departmentId should not be null") Long departmentId) {
}

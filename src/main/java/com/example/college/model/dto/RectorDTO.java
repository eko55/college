package com.example.college.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RectorDTO (
        @NotBlank(message = "personalNumber should not be blank") @Pattern(regexp = "[0-9]{10}", message = "personalNumber should consist of exactly 10 digits") String personalNumber,
        @NotBlank(message = "firstName should not be blank") String firstName,
        @NotBlank(message = "lastName should not be blank") String lastName) {
}

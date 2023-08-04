package com.example.fakebook.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordForm {
   @NotBlank
   @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,50}$",
          message = "Password must contain at least one letter and one digit")
   private String password;

   private String confirmPassword;
}

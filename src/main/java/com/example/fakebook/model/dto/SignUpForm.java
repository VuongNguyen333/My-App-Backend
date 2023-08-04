package com.example.fakebook.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
   private String username;

   private PasswordForm passwordForm;

   public SignUpForm(PasswordForm passwordForm) {
      this.passwordForm = passwordForm;
   }
}

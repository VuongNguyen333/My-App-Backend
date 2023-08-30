package com.example.fakebook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

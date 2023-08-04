package com.example.fakebook.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ReqUpdatePass {
   private String oldPass;

   @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
          message = "Password must contain at least one capital letter, one special character, character and number!")
   private String newPass;

   private Long id;
}

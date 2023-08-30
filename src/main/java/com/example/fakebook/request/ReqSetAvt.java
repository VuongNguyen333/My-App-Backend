package com.example.fakebook.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReqSetAvt {
   private MultipartFile avatar;
}

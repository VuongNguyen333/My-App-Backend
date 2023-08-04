package com.example.fakebook.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resp {
   private Object data;

   private String statusCode;// 0: succ <>1: fail

   private String msg;

   public void setAll(Object data, String statusCode, String msg) {
      this.data = data;
      this.statusCode = statusCode;
      this.msg = msg;
   }
}

//   @ApiOperation(value = "Tạo mới đề xuất phương án giá", response = List.class)
//   @PostMapping(value=  PathConstants.URL_DX_PAG + PathConstants.URL_TAO_MOI, produces = MediaType.APPLICATION_JSON_VALUE)
//   public ResponseEntity<Resp> create(@Valid @RequestBody KhLtPhuongAnGiaReq req) {
//      Resp resp = new Resp();
//      try {
//         resp.setData(khLtPagService.create(req));
//         resp.setStatusCode(Constants.RESP_SUCC);
//         resp.setMsg("Thành công");
//      } catch (Exception e) {
//         e.printStackTrace();
//         resp.setStatusCode(Constants.RESP_FAIL);
//         resp.setMsg(e.getMessage());
//         log.error(e.getMessage());
//      }
//      return ResponseEntity.ok(resp);
//   }
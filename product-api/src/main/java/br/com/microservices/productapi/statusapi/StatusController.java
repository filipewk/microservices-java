package br.com.microservices.productapi.statusapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class StatusController {

    @GetMapping("status")
    public ResponseEntity<ResponseDto> getApiStatus() {
        var response = ResponseDto.builder()
                .service("product-api")
                .status("up")
                .httpStatus(200)
                .build();
        return ResponseEntity.ok().body(response);
    }
}

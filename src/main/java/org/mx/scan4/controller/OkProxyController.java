package org.mx.scan4.controller;

import lombok.extern.slf4j.Slf4j;
import org.mx.scan4.service.OkProxyService;
import org.mx.scan4.vo.request.AddressCheckRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("tracker")
@Slf4j
public class OkProxyController {

    @Autowired
    private OkProxyService okProxyService;

    @PostMapping("/address/check")
    public ResponseEntity<String> saveAppInfo(@RequestBody AddressCheckRequest addressCheckRequest) throws Exception {
        String responseBody = okProxyService.addressCheck(addressCheckRequest);

        // Print the response
        return ResponseEntity.ok(responseBody);
    }
}

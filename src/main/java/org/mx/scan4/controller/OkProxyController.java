package org.mx.scan4.controller;

import lombok.extern.slf4j.Slf4j;
import org.mx.scan4.service.OkProxyService;
import org.mx.scan4.vo.request.AddressCheckRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("tracker")
@Slf4j
public class OkProxyController {

    @Autowired
    private OkProxyService okProxyService;

    @PostMapping("/address/check")
    public ResponseEntity<String> addressCheck(@RequestBody AddressCheckRequest addressCheckRequest, @RequestParam(value = "t") Long t) throws Exception {
        String responseBody = okProxyService.addressCheck(addressCheckRequest, t);

        // Print the response
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/address/analysis/caseViewContent")
    public ResponseEntity<String> caseViewContent(@RequestParam(value = "viewId") String viewId, @RequestParam(value = "address") String address, @RequestParam(value = "t") Long t) throws Exception {
        String responseBody = okProxyService.caseViewContent(viewId, address, t);

        // Print the response
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/address/detail")
    public ResponseEntity<String> addressDetail(@RequestParam(value = "address") String address, @RequestParam(value = "chain") String chain, @RequestParam(value = "tokenContractAddress", required = false) String tokenContractAddress, @RequestParam(value = "t") Long t) throws Exception {
        String responseBody = okProxyService.addressDetail(address, chain, tokenContractAddress, t);

        // Print the response
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/healthy/scoreV3")
    public ResponseEntity<String> healthyScoreV3(@RequestParam(value = "chain") String chain, @RequestParam(value = "address") String address, @RequestParam(value = "t") Long t) throws Exception {
        String responseBody = okProxyService.healthyScoreV3(chain, address, t);

        // Print the response
        return ResponseEntity.ok(responseBody);
    }
}

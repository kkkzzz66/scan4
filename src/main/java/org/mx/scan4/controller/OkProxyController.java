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

    @GetMapping("/tx/addresses/counters")
    public ResponseEntity<String> txAddressesCounters(@RequestParam(value = "address") String address, @RequestParam(value = "chain") String chain, @RequestParam(value = "tokenContractAddress") String tokenContractAddress, @RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "counter", required = false) String counter, @RequestParam(value = "t") Long t) throws Exception {
        String responseBody = okProxyService.txAddressesCounters(address, chain, tokenContractAddress, limit, offset, counter, t);

        // Print the response
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/tx/cross/bridge/chain/list")
    public ResponseEntity<String> bridgeChainList(@RequestParam(value = "address") String address, @RequestParam(value = "t") Long t) throws Exception {
        String responseBody = okProxyService.bridgeChainList(address, t);

        // Print the response
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/tx/cross/bridge/list")
    public ResponseEntity<String> bridgeList(@RequestParam(value = "srcChain", required = false) String srcChain, @RequestParam(value = "destChain", required = false) String destChain, @RequestParam(value = "address") String address, @RequestParam(value = "limit", defaultValue = "10") Integer limit, @RequestParam(value = "offset", defaultValue = "0") Integer offset, @RequestParam(value = "t") Long t) throws Exception {
        String responseBody = okProxyService.bridgeList(srcChain, destChain, address, limit, offset, t);

        // Print the response
        return ResponseEntity.ok(responseBody);
    }
}

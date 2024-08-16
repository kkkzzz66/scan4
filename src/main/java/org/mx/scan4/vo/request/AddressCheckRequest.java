package org.mx.scan4.vo.request;

import lombok.Data;

@Data
public class AddressCheckRequest {
    private String symbol;
    private String address;
    private String chain;
    private String tokenContractAddress;

}

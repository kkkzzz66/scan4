package org.mx.scan4.service;

import org.mx.scan4.vo.request.AddressCheckRequest;

public interface OkProxyService {
    String addressCheck(AddressCheckRequest addressCheckRequest) throws Exception;
}

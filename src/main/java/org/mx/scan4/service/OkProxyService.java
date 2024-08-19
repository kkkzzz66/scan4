package org.mx.scan4.service;

import org.mx.scan4.vo.request.AddressCheckRequest;

public interface OkProxyService {
    String addressCheck(AddressCheckRequest addressCheckRequest, Long t) throws Exception;

    String caseViewContent(String viewId, String address, Long t) throws Exception;

    String addressDetail(String address, String chain, String tokenContractAddress, Long t) throws Exception;

    String healthyScoreV3(String chain, String address, Long t) throws Exception;
}

package org.mx.scan4.service.impl;

import cn.hutool.json.JSONUtil;
import org.mx.scan4.service.OkProxyService;
import org.mx.scan4.vo.request.AddressCheckRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OkProxyServiceImpl implements OkProxyService {
    @Value("${check.apiKey}")
    private String apiKey;

    private String sign(String n, String url, String body) throws Exception {
        // Generate SHA-256 hash of the token
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(n.getBytes(StandardCharsets.UTF_8));

        // Convert hash bytes to hex string
        String u = IntStream.range(0, hash.length)
                .mapToObj(i -> String.format("%02x", hash[i]))
                .collect(Collectors.joining());

        // Calculate time-based indices
        long s = Instant.now().getEpochSecond();
        int l = (int) (s / 600 % 32);
        int f = (int) (s / 3600 % 32);

        // Generate dynamic key
        StringBuilder i = new StringBuilder();
        for (int d = 0; d < 32; d++) {
            char p = u.charAt((l + (f + d) * d) % 32);
            i.append(p);
        }

        // Create HMAC key
        SecretKeySpec keySpec = new SecretKeySpec(i.toString().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(keySpec);

        // Generate LF value
        String lf_v = lf(url, body);

        // Sign the LF value
        byte[] sign_v = mac.doFinal(lf_v.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(sign_v);
    }

    private String lf(String url, String body) {
        String r = url.replace("?", "");
        if (body != null && !body.isEmpty()) {
            r = url.split("\\?")[0] + body;
        }
        return r;
    }

    private String encryptApiKey() {
        String e = apiKey;
        String t = e.substring(8) + e.substring(0, 8);
        return t;
    }

    private String encryptTime(long e) {
        String t = (e + 1111111111111L) + "";
        Random random = new Random();
        int n = random.nextInt(10);
        int a = random.nextInt(10);
        int r = random.nextInt(10);
        return t + n + a + r;
    }

    private String comb(String e, String t) {
        String n = e + "|" + t;
        return Base64.getEncoder().encodeToString(n.getBytes(StandardCharsets.UTF_8));
    }

    private String getApikey() {
        long e = System.currentTimeMillis();
        String t = encryptApiKey();
        e = Long.parseLong(encryptTime(e));
        return comb(t, String.valueOf(e));
    }

    @Override
    public String addressCheck(AddressCheckRequest addressCheckRequest, Long t) throws Exception {
        String token = UUID.randomUUID().toString();

        String url = "/api/tracker/c/v1/address/check/v1?t=" + t;

        String body = JSONUtil.toJsonStr(addressCheckRequest);

        String oksign = sign(token, url, body);
        String apikey = getApikey();

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.oklink.com" + url))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("ok-verify-sign", oksign)
                .header("ok-verify-token", token)
                .header("x-apikey", apikey)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    @Override
    public String caseViewContent(String viewId, String address, Long t) throws Exception {
        String token = UUID.randomUUID().toString();

        StringBuilder urlBuilder = new StringBuilder("/api/tracker/c/v1/address/analysis/caseViewContent?viewId=");
        urlBuilder.append(viewId).append("&address=").append(address).append("&t=").append(t);
        String url = urlBuilder.toString();
        String oksign = sign(token, url, null);
        String apikey = getApikey();

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.oklink.com" + url))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("ok-verify-sign", oksign)
                .header("ok-verify-token", token)
                .header("x-apikey", apikey)
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    @Override
    public String addressDetail(String address, String chain, String tokenContractAddress, Long t) throws Exception {
        String token = UUID.randomUUID().toString();

        StringBuilder urlBuilder = new StringBuilder("/api/tracker/c/v1/r1/address/detail?address=");
        urlBuilder.append(address).append("&chain=").append(chain).append("&tokenContractAddress=").append(tokenContractAddress).append("&t=").append(t);
        String url = urlBuilder.toString();
        String oksign = sign(token, url, null);
        String apikey = getApikey();

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.oklink.com" + url))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("ok-verify-sign", oksign)
                .header("ok-verify-token", token)
                .header("x-apikey", apikey)
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    @Override
    public String healthyScoreV3(String chain, String address, Long t) throws Exception {
        String token = UUID.randomUUID().toString();

        StringBuilder urlBuilder = new StringBuilder("/api/tracker/c/v1/r1/healthy/scoreV3?chain=");
        urlBuilder.append(chain).append("&address=").append(address).append("&t=").append(t);
        String url = urlBuilder.toString();
        String oksign = sign(token, url, null);
        String apikey = getApikey();

        // Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.oklink.com" + url))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("ok-verify-sign", oksign)
                .header("ok-verify-token", token)
                .header("x-apikey", apikey)
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

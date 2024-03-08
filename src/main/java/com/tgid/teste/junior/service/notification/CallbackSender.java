package com.tgid.teste.junior.service.notification;

import com.tgid.teste.junior.dto.company.TransactionDTO;
import com.tgid.teste.junior.model.Company;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

public class CallbackSender {
    private static final String webhookUrl = "https://webhook.site/bb1037a6-b65f-47eb-a9bf-144b9c04a49d";

    public static void CallbackData(Company company, TransactionDTO dto, String typeEnum){
        Map<String, Object> callbackData = new HashMap<>();
        callbackData.put("companyName", company.getName());
        callbackData.put("typeTax", typeEnum);
        callbackData.put("companyId", dto.getCompanyId());
        callbackData.put("clientId", dto.getClientId());
        callbackData.put("transactionAmount", dto.getAmount());
        callbackData.put("companyBalance", company.getBalance());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(callbackData, headers);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(webhookUrl, request, String.class);
        System.out.println("Callback sent successfully!");
        System.out.println("Resposta do Webhook.site: " + response);
    }
}

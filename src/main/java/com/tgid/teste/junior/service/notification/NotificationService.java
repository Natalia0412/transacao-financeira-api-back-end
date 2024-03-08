package com.tgid.teste.junior.service.notification;

import com.tgid.teste.junior.model.Client;
import com.tgid.teste.junior.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor

public class NotificationService {
    private  final EmailService emailService;

    public boolean test(Client client, Company company, AtomicReference<Double> feeAmount, double amount, String typeEnum) {
        String name = client.getName();
        String companyName = company.getName();
        String subject = "Transação concluída";
        String message = String.format("Olá, %s\n\n"
                        + "Sua transação %s na empresa %s "
                        + "foi concluída com sucesso\n"
                        + "O valor da taxa de serviço foi de %.2f "
                        + "e o valor da transação foi de %.2f",
                name, typeEnum, companyName, feeAmount.get(), amount);
        this.notificationClient(client.getEmail(), subject, message);
        return true;
    }

    public  void notificationClient(String email,String subject, String mensagem) {
        emailService.sendEmail(email, subject, mensagem);
    }

}

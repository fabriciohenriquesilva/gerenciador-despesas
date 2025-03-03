package dev.fabriciosilva.controlefinanceiro.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.reset-password-url}")
    private String resetPasswordUrl;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(String to, String token) throws MessagingException {
        String resetLink = resetPasswordUrl + "?token=" + token;

        StringBuilder body = new StringBuilder();
        body.append("<html>");
        body.append("<body>");
        body.append("<p>Olá, </p>");
        body.append("<p>Você solicitou a redefinição da sua senha </p>");
        body.append("<p>Por favor, clique no link abaixo para redefinir sua senha</p>");
        body.append("<p><a href=\"").append(resetLink).append("\">").append("Clique aqui").append("</a></p>");
        body.append("<p>Ignore este email se você se lembrou de sua senha ou se você não fez essa solicitação</p>");
        body.append("</body>");
        body.append("</html>");

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("Recuperação de Senha");
        mimeMessageHelper.setFrom("kofrinho.informa@gmail.com");
        mimeMessageHelper.setText(body.toString(), true);

        mailSender.send(mimeMessage);
    }

}

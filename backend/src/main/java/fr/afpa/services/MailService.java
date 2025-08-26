package fr.afpa.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

@ApplicationScoped
public class MailService {

    @Inject
    Mailer mailer;

    public void envoyerLienVote(String destinataire, String lienVote) {
        mailer.send(Mail.withText(destinataire, "Lien de vote", "Cliquez sur le lien pour voter : " + lienVote));
    }

    public void envoyerConfirmation(String destinataire) {
        mailer.send(Mail.withText(destinataire, "Confirmation de vote", "Votre vote a bien été enregistré."));
    }
}


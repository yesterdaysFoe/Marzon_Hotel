package utils;

import connection.DbConf;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author David Daniel Kurtz <daviddanielkurtz@gmail.com>
 */
public class EmailUtil {

    public Properties getEmailProperties() {
        Properties prop = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("resources/email.properties");
            prop.load(fileInputStream);
        } catch (IOException ex) {
            Logger.getLogger(DbConf.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(DbConf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return prop;
    }

    public static void sendEmail(String subject, String toAddress, String message) throws EmailException {
        EmailUtil eu = new EmailUtil();

        final String AUTH_USERNAME = eu.getEmailProperties().getProperty("AUTH_USERNAME");
        final String AUTH_PASSWORD = eu.getEmailProperties().getProperty("AUTH_PASSWORD");
        final String HOST_NAME = eu.getEmailProperties().getProperty("HOST_NAME");
        final String FROM_ADDRESS = eu.getEmailProperties().getProperty("FROM_ADDRESS");
        final int SMTP_PORT = Integer.parseInt(eu.getEmailProperties().getProperty("SMTP_PORT"));

        HtmlEmail emailHtml = new HtmlEmail();
        emailHtml.setSmtpPort(SMTP_PORT);
        emailHtml.setAuthentication(AUTH_USERNAME, AUTH_PASSWORD);
        emailHtml.setHostName(HOST_NAME);
        emailHtml.setFrom(FROM_ADDRESS);
        emailHtml.setStartTLSEnabled(true);

        emailHtml.setSubject(subject);
        emailHtml.addTo(toAddress);
        emailHtml.setHtmlMsg(message);
//            for (byte[] attach : attachments) {
//                emailHtml.attach(new ByteArrayDataSource(attach, "application/pdf"), "document.pdf", "Document description", EmailAttachment.ATTACHMENT);
//            }
        emailHtml.send();
    }
}

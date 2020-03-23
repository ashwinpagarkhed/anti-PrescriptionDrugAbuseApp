/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.UserAccount.UserAccount;
import com.google.zxing.common.BitMatrix;
import de.taimos.totp.TOTP;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author ashwin
 */
public class Helper {
   private static String dateTimeFormat = "dd-M-yyyy hh:mm:ss a"; 
    
   public static String getCurrentDateTime(){
       SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date = new Date();
        return formatter.format(date);
   }
   
   public static String getCurrentDateTimeFromDateObject(Date dateObj){
       SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
       //formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
       return formatter.format(dateObj);
   }
   
   public static Date getDateObject(String dateTime) throws ParseException {
       SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat);
       formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
       Date date = formatter.parse(dateTime);
       return date;
   }
   
    public enum WorkRequestStatus {
        RejectedByDoctor(true),
        WaitingForDecisionByDoctor(false),
        ApprovedByDoctor(false),
        WaitingForRoutingAtGoverningBody(false),
        RoutedToDoctor(false),
        RoutedBackToGov(false),
        RoutedToStore(false),
        ReadyForPickup(true);
        
        private Boolean hasWorkRequestEnded;

        public Boolean hasWorkRequestEnded() {
            return hasWorkRequestEnded;
        }
        
        WorkRequestStatus(Boolean hasEnded) {
            hasWorkRequestEnded = hasEnded;
        }
   }
    
    public static boolean isPasswordStrong(final String password)    {
        boolean result = false;
        try {
            if (password!=null) {
                final String MIN_LENGHT="6";
                final String MAX_LENGHT="20";
                final boolean SPECIAL_CHAR_NEEDED=true;

                final String ONE_DIGIT = "(?=.*[0-9])";  //(?=.*[0-9]) a digit must occur at least once
                final String LOWER_CASE = "(?=.*[a-z])";  //(?=.*[a-z]) a lower case letter must occur at least once
                final String UPPER_CASE = "(?=.*[A-Z])";  //(?=.*[A-Z]) an upper case letter must occur at least once
                final String NO_SPACE = "(?=\\S+$)";  //(?=\\S+$) no whitespace allowed in the entire string
                //final String MIN_CHAR = ".{" + MIN_LENGHT + ",}";  //.{8,} at least 8 characters
                final String MIN_MAX_CHAR = ".{" + MIN_LENGHT + "," + MAX_LENGHT + "}";  //.{5,10} represents minimum of 5 characters and maximum of 10 characters

                final String SPECIAL_CHAR;
                if (SPECIAL_CHAR_NEEDED==true) SPECIAL_CHAR= "(?=.*[@#$%^&+=])"; //(?=.*[@#$%^&+=]) a special character must occur at least once
                else SPECIAL_CHAR="";

                final String PATTERN = ONE_DIGIT + LOWER_CASE + UPPER_CASE + SPECIAL_CHAR + NO_SPACE + MIN_MAX_CHAR;

                result = password.matches(PATTERN);
            }    

        } catch (Exception ex) {
            result=false;
        }

        return result;
    }
    
    public static Boolean doesUsernameExist(EcoSystem system, String userName) {
        Boolean doesUserExistInUserAccount = system.checkIfUsernameExists(userName);
        if(doesUserExistInUserAccount == true) return true;
        
        Boolean doesUserExistInUserAccountDirectory = system.checkIfUserExistsInUserDirectory(userName);
        if(doesUserExistInUserAccountDirectory == true) return true;
        
        for(Network network : system.getNetworkList()){
            for(Enterprise enterprise:network.getEnterpriseDirectory().getEnterpriseList()){
                Boolean doesUserExisstInInterprise = enterprise.checkIfUserExists(userName);

                if(doesUserExisstInInterprise) return doesUserExisstInInterprise;
                
                for(Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()){
                    Boolean doesUserExistInOrganization = organization.checkIfUserExists(userName);
                    
                    if(doesUserExistInOrganization) return doesUserExistInOrganization;
                }
            }
        }
        
        return false;
    }
    
    
    public static void sendEmail(String to, String subject, String body) {
        // Sender's email ID needs to be mentioned
        String from = "nineninenortheastern";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";
        
        String password = "Northeastern@1";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session;
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        return base32.encodeToString(bytes);
    }

    public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }

    public static String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
        try {
            return "otpauth://totp/"
                    + URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20")
                    + "?secret=" + URLEncoder.encode(secretKey, "UTF-8").replace("+", "%20")
                    + "&issuer=" + URLEncoder.encode(issuer, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public static void createQRCode(String barCodeData, String filePath, int height, int width) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(barCodeData, BarcodeFormat.QR_CODE,
                width, height);
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            MatrixToImageWriter.writeToStream(matrix, "png", out);
        }
}
}

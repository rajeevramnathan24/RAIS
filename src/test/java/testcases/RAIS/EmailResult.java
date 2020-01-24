package testcases.RAIS;

import java.awt.Color;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.testng.annotations.Test;


import commonfunction.GenericMethods;





public class EmailResult {

	/*final String username = "rajeev.ramnathan";
	final String password = "p@ssw0rd12345678";*/
	
	//final String username = "test@e-zest.com";
	//final String password = "Nasa$2018" ;
	public static String TestString = "TEST CASE COUNT";

	@Test(priority = 1, enabled = true)
	public void sendMailReport(int chPass, int chFail, int ffPass, int ffFail) throws IOException {

		// new mail initialsing
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "outlook.office365.com");
		props.put("mail.smtp.ssl.trust", "outlook.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("test@e-zest.com", "Nasa$2018");
			}
		});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("test@e-zest.com", "RAIS QA Team"));

			System.out.println("RAIS QA Team");
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rajeev.ramnathan@e-zest.in"));
			//message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ProjConstants.ToMail));

			message.setSubject("IAEA || RIAS Test Automation Report for TESTING ONLY "  );
			//String htmlText2 = "Test Subject";
			
			
			String browserNameCh = "Chrome";
			String browserNameFF = "FireFox";
			
			int totalCHcount = chPass + chFail;
			int totalFFcount = ffPass + ffFail;
			
//			message.setText
//
//			("Hi," + "\n\n Automated test execution report for Beta2 environment is available in the below location :"
//
//			// Result link for Chrome
//					+ "\n\n Chrome Browser Results\n http://14.141.104.8:9090/" + "ENV NAME1"
//					+ "/" + "chrome" + "/Mobelisk_Test_Result.html" + "\n\n" +
//
//					// Result link for FF
//					"FireFox Browser Results\n http://14.141.104.8:9090/" + "ENV NAME2"
//					+ "/" + "FF" + "/Mobelisk_Test_Result.html" + "\n\n"
//
//					+ "Thanks," + "\n"+ "Mobelisk QA");
			
			String MailBody = "<p>Hi All,</p>\r\n" + 
					
					//Below code is for Chrome
					"<p>Summary of Tests Executed in " +
					browserNameCh +
					":</p>\r\n" + 
					"<table style=\"height: 83px; width: 193px;\">\r\n" + 
					"<tbody>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><span style=\"color: #000000; background-color: #00ff00;\"><strong>Total Pass Test cases</strong></span></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><strong><span style=\"background-color: #00ff00;\">" +
					chPass +
					"</span></strong></td>\r\n" + 
					"</tr>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><span style=\"background-color: #ff9900;\"><strong>Total Fail Test cases</strong></span></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><span style=\"background-color: #ff9900;\"><strong>" +
					chFail +
					"</strong></span></td>\r\n" + 
					"</tr>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><strong>Total Test cases</strong></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><strong>" +
					totalCHcount +
					"</strong></td>\r\n" + 
					"</tr>\r\n" + 
					"</tbody>\r\n" + 
					"</table>\r\n" + 
					
					//
					//Below code is for FF
					"<p>Summary of Tests Executed in " +
					browserNameFF +
					":</p>\r\n" + 
					"<table style=\"height: 83px; width: 193px;\">\r\n" + 
					"<tbody>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><span style=\"color: #000000; background-color: #00ff00;\"><strong>Total Pass Test cases</strong></span></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><strong><span style=\"background-color: #00ff00;\">" +
					ffPass +
					"</span></strong></td>\r\n" + 
					"</tr>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><span style=\"background-color: #ff9900;\"><strong>Total Fail Test cases</strong></span></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><span style=\"background-color: #ff9900;\"><strong>" +
					ffFail +
					"</strong></span></td>\r\n" + 
					"</tr>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><strong>Total Test cases</strong></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><strong>" +
					totalFFcount +
					"</strong></td>\r\n" + 
					"</tr>\r\n" + 
					"</tbody>\r\n" + 
					"</table>\r\n" +
					
					//Text of detailed reports link
					"<p>Detailed Test Results are available in&nbsp;the location:&nbsp;</p>\r\n" + 
					"<p><a href=\"https://Test1.com\">https://Test1.com</a>&nbsp;</p>\r\n" + 
					"<p><a href=\"https://Test1.com\">https://Test1.com</a></p>\r\n" + 
					"<p>&nbsp;Thanks,<br />QA Team</p>\r\n" + 
					"<p>&nbsp;</p>\r\n" + 
					"<p>&nbsp;</p>";
			
			message.setContent(MailBody ,"text/html" );
			
//			String htmlText2 = "<b><H1><font color=#93cff2>RAJEEV</font></H1></b>\n";
//			
//			message.setText(htmlText2);
			
					
				//addColor("\n Mobelisk QA", Color.BLUE);
			
			//+addColor(TestString, Color.BLUE);
			//message.setContent("<h1>This is actual message</h1>","text/html" );

			Transport.send(message);

			System.out.println("Mail sent");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	
}

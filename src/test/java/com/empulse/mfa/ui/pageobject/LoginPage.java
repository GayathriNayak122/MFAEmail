package com.empulse.mfa.ui.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;

import java.io.IOException;
import java.util.Properties;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import jakarta.mail.*;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.FlagTerm;
//import org.json.JSONObject;
//import org.junit.Assert;

public class LoginPage extends BasePage {
	private WebDriver driver;
	String otp;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String readOTPFromEmail(String emailAddress) {
		// String host = "outlook.office365.com";
		String host = "smtp.office365.com";
		String mailStoreType = "imaps";
		String username = "gnayak@1ffc.com";
		String password = "Gayathri@#123"; // Please replace with your actual password

		Properties properties = new Properties();
		// properties.put("mail.imaps.host", host);
		// properties.put("mail.imaps.port", "993");
		// properties.put("mail.imaps.port", "587");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		Session emailSession = Session.getDefaultInstance(properties);

		try {
			Store store = emailSession.getStore(mailStoreType);
			store.connect(host, username, password);

			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				if (message.getSubject().contains("OTP")) { // Replace with actual subject if needed
					String content = getTextFromMessage(message);
					// Extract OTP from content
					System.out.println("###########content " + content);
					// String otp = extractOTP(content); // Implement this method based on your OTP
					// format
					// return otp;
					return "some otp";
				}
			}

			emailFolder.close(false);
			store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getTextFromMessage(Message message) throws MessagingException, IOException {
		String result = "";
		if (message.isMimeType("text/*")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;
	}

	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

	public boolean LoginMethod() {
		boolean flag = false;
		try {
			driver.findElement(By.xpath("//input[@id='Mail']")).sendKeys("gnayak@1ffc.com");
			driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Gayathri@#123");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[contains(text(),'Forgot Password?')]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(),'Send')]")).click();
			flag = true;

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return flag;
	}

	public boolean EntersOTP() {
		boolean flag = false;
		try {
			driver.findElement(By.id("otp")).sendKeys(otp);
			driver.findElement(By.id("verify")).click();
			flag = true;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return flag;
	}
}
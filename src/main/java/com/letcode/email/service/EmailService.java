package com.letcode.email.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.letcode.email.auth.MyAuthenticator;
import com.letcode.email.bean.Email;
import com.letcode.exception.ErrorException;

/**
 * 邮件发送服务
 *
 * @author 帅军
 *
 */
public class EmailService {
	private Email	email;

	/**
	 * 发送邮件(默认html)
	 *
	 * @param toAddr
	 *            邮件接收者
	 * @param subJect
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @return
	 * @throws ErrorException
	 */
	public boolean sendEmail(String toAddr, String subJect, String content)
			throws ErrorException {
		email.setSubject(subJect);
		email.setContent(content);
		email.setToAddress(toAddr);
		return sendHtmlMail(email);
	}

	/**
	 *
	 * 发送邮件
	 *
	 * @param toAddr
	 *            邮件接收者
	 * @param subJect
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param type
	 *            发送邮件类型
	 * @return
	 * @throws ErrorException
	 */
	public boolean sendEmail(String toAddr, String subJect, String content,
			String type) throws ErrorException {
		email.setSubject(subJect);
		email.setContent(content);
		email.setToAddress(toAddr);
		if ("HTML".equals(type.toUpperCase()))
			return sendHtmlMail(email);
		else if ("TEXT".equals(type)) {
			return sendTextMail(email);
		} else {
			throw new ErrorException("email type is not defined!!");
		}
	}

	/**
	 *
	 * 发送带多个附件附件邮件
	 *
	 * @param toAddr
	 *            邮件接收者
	 * @param subJect
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param type
	 *            发送邮件类型
	 * @param files
	 *            需要发送的多个文件
	 * @return
	 * @throws ErrorException
	 */
	public boolean sendEmail(String toAddr, String subJect, String content,
			String type, File[] files) throws ErrorException {
		email.setSubject(subJect);
		email.setContent(content);
		email.setToAddress(toAddr);
		email.setAttachFiles(files);
		if ("HTML".equals(type.toUpperCase()))
			return sendHtmlMail(email);
		else if ("TEXT".equals(type.toUpperCase())) {
			return sendTextMail(email);
		} else {
			throw new ErrorException("email type is not defined!!");
		}
	}

	/**
	 *
	 * 发送单个文件的邮件
	 *
	 * @param toAddr
	 *            邮件接收者:陈帅军&lt;chensj@kayak.com.cn&gt;,陈帅军&lt;chensj_blue@qq.com
	 *            &gt;
	 * @param subJect
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @param type
	 *            发送邮件类型 (HTML/TEXT)
	 * @param file
	 *            需要发送的单个文件
	 * @return
	 * @throws ErrorException
	 */
	public boolean sendEmail(String toAddr, String subJect, String content,
			String type, File file) throws ErrorException {
		email.setSubject(subJect);
		email.setContent(content);
		email.setToAddress(toAddr);
		File[] s = new File[1];
		s[0] = file;
		email.setAttachFiles(s);
		if ("HTML".equals(type.toUpperCase()))
			return sendHtmlMail(email);
		else if ("TEXT".equals(type.toUpperCase())) {
			return sendTextMail(email);
		} else {
			throw new ErrorException("email type is not defined!!");
		}
	}

	/**
	 * 以文本格式发送邮件<步骤处理流程>
	 *
	 * @param mail
	 *            发送邮件的内容和信息
	 */
	public boolean sendTextMail(Email mail) {

		Session mSession = null;
		Multipart mainPart = null;
		Message mailMessage = null;
		BodyPart msgBodyPart = null;
		try {
			mSession = getMailSession(mail);
			mailMessage = getMimeMessage(mSession, mail);
			mainPart = new MimeMultipart();
			msgBodyPart = new MimeBodyPart();
			msgBodyPart.setText(mail.getContent());
			mainPart = getFileMultipart(mainPart, mail);
			mainPart.addBodyPart(msgBodyPart);
			mailMessage.setContent(mainPart);
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (ErrorException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 *
	 * @param mail
	 *            待发送的邮件信息
	 * @return boolean
	 */
	public boolean sendHtmlMail(Email mail) {
		Session mSession = null;
		Multipart mainPart = null;
		BodyPart html = null;
		Message mailMessage = null;
		try {
			mSession = getMailSession(mail);
			mailMessage = getMimeMessage(mSession, mail);
			html = new MimeBodyPart();
			html.setContent(mail.getContent(), "text/html; charset=utf-8");
			mainPart = new MimeMultipart();
			mainPart.addBodyPart(html);
			mainPart = getFileMultipart(mainPart, mail);
			mailMessage.setContent(mainPart);
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		} catch (ErrorException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取Email session <br>
	 * 如果需要身份认证，则创建一个密码验证器
	 *
	 * @param mail
	 *            发送邮件Bean
	 * @return
	 */
	public Session getMailSession(Email mail) {
		Authenticator authenticator = null;
		Properties pro = mail.getProperties();
		if (mail.isValidate()) {
			authenticator = new MyAuthenticator(mail.getUserName(),
					mail.getPassword());
		}
		return Session.getInstance(pro, authenticator);
	}

	/**
	 * 获取Email发送MimeMessage
	 * <ol>
	 * <li>初始化邮件信息和接收发送地址</li>
	 * <li>设置邮件接收者和发送者</li>
	 * <li>设置邮件的发送时间和主题</li>
	 * </ol>
	 *
	 * @param mSession
	 * @return
	 * @throws ErrorException
	 */
	public Message getMimeMessage(Session mSession, Email mail)
			throws ErrorException {
		Message msg = null;
		Address from = null;
		InternetAddress[] to = null;
		try {
			msg = new MimeMessage(mSession);
			from = new InternetAddress(MimeUtility.encodeText(mail
					.getFromUserName()) + '<' + mail.getFromAddress() + ">");
			to = new InternetAddress().parse(getToAddress(mail.getToAddress()));
			msg.setFrom(from);
			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setSubject(mail.getSubject());
			msg.setSentDate(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ErrorException(e.getMessage());
		}
		return msg;
	}

	/**
	 * 获取附件模块
	 *
	 * @param mainPart
	 * @param email
	 * @return
	 */
	public Multipart getFileMultipart(Multipart mainPart, Email email) {
		try {
			BodyPart fPart = null;
			DataSource source = null;
			if (email == null || email.getAttachFiles() == null)
				return mainPart;
			for (File f : email.getAttachFiles()) {
				if (f == null || !f.exists())
					continue;
				fPart = new MimeBodyPart();
				source = new FileDataSource(f);
				fPart.setDataHandler(new DataHandler(source));
				fPart.setFileName(MimeUtility.encodeWord(f.getName()));
				mainPart.addBodyPart(fPart);
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return mainPart;
	}

	/**
	 * 获取收件人地址 <br>
	 * <b>邮件地址规则 陈帅军<chensj@kayak.com.cn>,陈帅军<chensj_blue@qq.com> </b>
	 *
	 * @param mailArray
	 * @return
	 */
	public String getToAddress(String toAddress) {
		String addrs = "";
		try {
			if (toAddress.contains(",")) {
				String[] s = toAddress.split(",");
				for (String t : s) {
					if (t.contains("<"))
						addrs += getMimeEcodeAddr(t) + ",";
					else
						addrs += t + ", ";
				}
			} else {
				if (toAddress.contains("<"))
					addrs += getMimeEcodeAddr(toAddress);
				else
					addrs = toAddress;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return addrs;
	}

	/**
	 * 获取转码后的邮件地址
	 *
	 * @param addr
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getMimeEcodeAddr(String addr)
			throws UnsupportedEncodingException {
		return MimeUtility.encodeWord(addr.substring(0, addr.indexOf('<')))
				+ addr.substring(addr.indexOf('<'));
	}

	public void setEmail(Email email) {
		this.email = email;
	}
}

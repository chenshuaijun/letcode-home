package letcode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class VCardDeal {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		File f = new File("/Users/chenshuaijun/Documents/20160512.csv");
		if (f.exists()) {
			System.out.println(true);
		}
		System.out.println("开始处理文件！！");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String line = null;
			OutputStream os = new FileOutputStream(new File("/Users/chenshuaijun/Documents/result.vcf"));
			while (StringUtils.isNotBlank(line = br.readLine())) {
				String[] str = line.split(",", -1);
				// System.out.println(str[0]);
				getCardInfo(os, str[0].substring(0, 1), str[0].substring(1, str[0].length()), str[6], str[5], str[1]);
			}
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getCardInfo(OutputStream os, String fname, String lname, String email, String phone,
			String org) {
		try {
			os.write("BEGIN:VCARD\n".getBytes("utf-8"));
			os.write("VERSION:3.0\n".getBytes("utf-8"));
			os.write("item0.X-ABLabel:个人邮箱\n".getBytes("utf-8"));
			os.write(("item0.EMAIL;TYPE=INTERNET,pref:" + email + "\n").getBytes("utf-8"));
			os.write("item1.X-ABLabel:手机\n".getBytes("utf-8"));
			os.write(("item1.TEL;TYPE=VOICE,CELL,pref;VALUE=text:" + phone + "\n").getBytes("utf-8"));
			os.write(("FN:" + fname + " " + lname + "\n").getBytes("utf-8"));
			os.write(("N:" + fname + "; " + lname + ";;;\n").getBytes("utf-8"));
			os.write(("ORG:北京开科唯识技术有限公司;" + org + "\n").getBytes("utf-8"));
			os.write(("UID:" + UUID.randomUUID().toString().toUpperCase() + "\n").getBytes("utf-8"));
			os.write("END:VCARD".getBytes("utf-8"));
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

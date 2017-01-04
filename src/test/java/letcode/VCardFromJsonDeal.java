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
import org.json.JSONArray;
import org.json.JSONObject;

public class VCardFromJsonDeal {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		File f = new File("/Users/chenshuaijun/Downloads/findUsers.json");
		if (!f.exists()) {
			System.out.println(false + "文件不存在！");
			return;
		}
		System.out.println("开始处理文件！！");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String line = null;
			OutputStream os = new FileOutputStream(new File("/Users/chenshuaijun/Desktop/result.vcf"));
			if (StringUtils.isNotBlank(line = br.readLine())) {
				JSONObject object = new JSONObject(line);
				System.out.println(object.get("results"));
				JSONArray rows = (JSONArray) object.get("rows");
				for (int i = 0; i < rows.length(); i++) {
					JSONObject o = (JSONObject) rows.get(i);
					// System.out.println(o);
					String realname = o.getString("realname");
					System.out.println(o.get("realname") + "  " + o.get("kk_email") + "  " + o.get("phone") + " "
							+ o.get("department_name"));
					getCardInfo(os, "", realname, o.getString("kk_email"), o.getString("phone"),
							o.getString("department_name"));
				}
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

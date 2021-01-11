package org.link.push.job.controller;

import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author ginger
 * @date 2021/1/8 10:03
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/getOsInfo")
	public Map<String, String> getOsInfo() {
		Map<String, String> getenv = System.getenv();
		Properties properties = System.getProperties();
		System.out.println(JSON.toJSONString(properties));
		return getenv;
	}

	@RequestMapping("/execute")
	public String execute(String command) {
		InputStream in = null;
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			Charset charset = Charset.defaultCharset();
			Properties properties = System.getProperties();
			String property = System.getProperties().getProperty("file.encoding");
			System.out.println(charset.displayName());
			System.out.println(property);
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor(10, TimeUnit.SECONDS);
//			int isOk = process.exitValue();
//			// 0是成功
//			if (isOk == 0) {
//				in = process.getInputStream();
//				reader = new BufferedReader(new InputStreamReader(in, "GBK"));
//				String line = null;
//				while ((line = reader.readLine()) != null) {
//					sb.append(line).append("\n");
//				}
//			}
			in = process.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in, "GBK"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			process.destroy();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(in);
		}
		return sb.toString();
	}

	@RequestMapping("/ok")
	public String ok() {
		return "ok";
	}

}

package org.link.push.job.core;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ginger
 * @date 2021/1/11 10:24
 */
public class AppTest {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(1000);

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// 设置每个路由基础的连接
		cm.setDefaultMaxPerRoute(800);
		//设置最大连接数//cm.setMaxPerRoute(new HttpRoute(httpHost), maxRoute);// 设置目标主机的最大连接数
		cm.setMaxTotal(1000);

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

		for (int i = 1; i <= 1000; i++) {
			executorService.execute(() -> {
				CloseableHttpResponse response = null;
				try {
					HttpGet httpGet = new HttpGet("http://127.0.0.1:8070/test/ok");
					response = httpClient.execute(httpGet);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						System.out.println(Thread.currentThread().getName() + " >> success");
					} else {
						System.out.println(Thread.currentThread().getName() + " >> fail");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// 一定要释放response，连接才会被释放
					try {
						if (response != null) {
							response.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

}

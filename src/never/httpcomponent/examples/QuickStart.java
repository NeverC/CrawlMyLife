/**  
 * @Title: QuickStart.java
 * @Package never.httpcomponent.examples
 * @author "Never" xzllc2010#gmail.com  
 * @date Mar 30, 2014 6:41:10 PM
 * @Description: TODO
 */
package never.httpcomponent.examples;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class QuickStart {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet("http://www.baidu.com/");
			CloseableHttpResponse httpGetResponse = httpclient.execute(httpGet);
			/**
			 * The underlying HTTP connection is still held by the response
			 * object to allow the response content to be streamed directly from
			 * the network socket. In order to ensure correct deallocation of
			 * system resources the user MUST either fully consume the response
			 * content or abort request execution by calling
			 * CloseableHttpResponse#close().
			 */

			try {
				System.out.println(httpGetResponse.getStatusLine());
				HttpEntity httpGetResponseEntity = httpGetResponse.getEntity();
				// Output entity of response via String  
				System.out.println(EntityUtils.toString(httpGetResponseEntity));
			} finally {
				httpGetResponse.close();
			}

			HttpPost httpPost = new HttpPost("http://www.tuicool.com/");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("inputEmail", "xzllc2010@gmail.com"));
			nvps.add(new BasicNameValuePair("inputPassword", "LXZcyh%8"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse httpPostResponse = httpclient.execute(httpPost);

			try {
				System.out.println(httpPostResponse.getStatusLine());
				HttpEntity httpPostResponseEntity = httpPostResponse.getEntity();
				// Output entity of response via String  
				System.out.println(EntityUtils.toString(httpPostResponseEntity));
			} finally {
				httpPostResponse.close();
			}
		} finally {
			httpclient.close();
		}
	}

}

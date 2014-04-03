/**  
 * @Title: ClientFormLogin.java
 * @Package never.httpcomponent.examples
 * @author "Never" xzllc2010#gmail.com  
 * @date Mar 30, 2014 7:28:33 PM
 * @Description: A example that demonstrates how HttpClient APIs can be used to perform form-based logon.
 */
package never.httpcomponent.examples;

import java.net.URI;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClientFormLogin {

	public static void main(String[] args) throws Exception {
		BasicCookieStore cookieStore = new BasicCookieStore();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		try {
			HttpGet httpget = new HttpGet("http://www.tuicool.com/login");
			CloseableHttpResponse httpgetResponse = httpclient.execute(httpget);
			try {
				HttpEntity httpgetResponseEntity = httpgetResponse.getEntity();

				System.out.println("Login form get: " + httpgetResponse.getStatusLine());
				EntityUtils.consume(httpgetResponseEntity);

				System.out.println("Initial set of cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
			} finally {
				httpgetResponse.close();
			}

			HttpUriRequest login = RequestBuilder.post().setUri(new URI("http://www.tuicool.com/login")).addParameter("xlEmail", "xzllc2010@gmail.com").addParameter("xlPassword", "LXZcyh%8").build();
			CloseableHttpResponse response2 = httpclient.execute(login);
			try {
				HttpEntity entity = response2.getEntity();

				System.out.println("Login form get: " + response2.getStatusLine());

				// Output entity of response via String  
				System.out.println(EntityUtils.toString(entity));
				
				System.out.println("Post logon cookies:");
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}
	}
}

package org.lxb.framework.util;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * HTTP客户端工具类
 * 
 * @author linxinbin 2015年1月28日
 */
@SuppressWarnings({ "deprecation", "resource" })
public class ToolHttpClient
{
	private static Logger log = Logger.getLogger(ToolHttpClient.class);

	private ToolHttpClient()
	{
	}

	private static DefaultHttpClient getSecuredHttpClient(HttpClient httpClient)
	{
		final X509Certificate[] _AcceptedIssuers = new X509Certificate[] {};
		try
		{
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager()
			{
				@Override
				public X509Certificate[] getAcceptedIssuers()
				{
					return _AcceptedIssuers;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
				{
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
				{
				}
			};
			ctx.init(null, new TrustManager[] { tm }, new SecureRandom());
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
			return new DefaultHttpClient(ccm, httpClient.getParams());
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
		}
		return null;
	}

	/**
	 * DELETE请求
	 * 
	 * @param url
	 *          请求的url
	 * @param headers
	 *          请求头
	 * @return
	 */
	public static String delete(String url, Map<String, String> headers)
	{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httpdelete:" + url);
		HttpDelete delete = new HttpDelete(url);
		if (null != headers)
		{
			delete = initHeader(delete, headers);
		}
		httpclient = getSecuredHttpClient(httpclient);
		body = invoke(httpclient, delete);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 *          主机地址
	 * @param headers
	 *          请求头
	 * @param json
	 *          请求参数
	 * @return
	 */
	public static String post(String url, Map<String, String> headers, String json, ContentType contentType)
	{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost:" + url);
		HttpPost post = postForm(url, json, contentType);
		if (null != headers)
		{
			post = initHeader(post, headers);
		}
		httpclient = getSecuredHttpClient(httpclient);
		body = invoke(httpclient, post);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * PUT请求
	 * 
	 * @param url
	 *          主机地址
	 * @param headers
	 *          请求头
	 * @param json
	 *          请求参数
	 * @return
	 */
	public static String put(String url, Map<String, String> headers, String json, ContentType contentType)
	{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httpput:" + url);
		HttpPut put = putForm(url, json, contentType);
		if (null != headers)
		{
			put = initHeader(put, headers);
		}
		httpclient = getSecuredHttpClient(httpclient);
		body = invoke(httpclient, put);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	/**
	 * GET请求
	 * 
	 * @param url
	 *          主机地址（）
	 * @param headers
	 *          请求头
	 * @param params
	 *          请求参数（也可直接拼接在url后面）
	 * @return
	 */
	public static String get(String url, Map<String, String> headers, Map<String, Object> params)
	{
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String body = null;

		log.info("create httppost:" + url);
		HttpGet get = getForm(url, params);
		if (null != headers)
		{
			get = initHeader(get, headers);
		}
		httpclient = getSecuredHttpClient(httpclient);
		body = invoke(httpclient, get);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	private static HttpPut initHeader(HttpPut put, Map<String, String> headers)
	{
		Iterator<String> it = headers.keySet().iterator();
		while (it.hasNext())
		{
			String key = it.next();
			put.addHeader(key, headers.get(key));
		}
		return put;
	}

	private static HttpPost initHeader(HttpPost post, Map<String, String> headers)
	{
		Iterator<String> it = headers.keySet().iterator();
		while (it.hasNext())
		{
			String key = it.next();
			post.addHeader(key, headers.get(key));
		}
		return post;
	}

	private static HttpDelete initHeader(HttpDelete delete, Map<String, String> headers)
	{
		Iterator<String> it = headers.keySet().iterator();
		while (it.hasNext())
		{
			String key = it.next();
			delete.addHeader(key, headers.get(key));
		}
		return delete;
	}

	private static HttpGet initHeader(HttpGet get, Map<String, String> headers)
	{
		Iterator<String> it = headers.keySet().iterator();
		while (it.hasNext())
		{
			String key = it.next();
			get.addHeader(key, headers.get(key));
		}

		return get;
	}

	private static HttpGet getForm(String url, Map<String, Object> params)
	{
		HttpGet httpGet = new HttpGet(url);
		HttpParams h = new BasicHttpParams();
		if (null != params)
		{
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext())
			{
				String key = it.next();
				h.setParameter(key, params.get(key));
			}
		}
		httpGet.setParams(h);
		return httpGet;
	}

	private static HttpPost postForm(String url, String json, ContentType contentType)
	{

		HttpPost httpPost = new HttpPost(url);
		log.info("set utf-8 form entity to httppost");

		httpPost.setEntity(new StringEntity(json, contentType));

		return httpPost;
	}

	private static HttpPut putForm(String url, String json, ContentType contentType)
	{

		HttpPut httpPost = new HttpPut(url);
		log.info("set utf-8 form entity to httppost");

		httpPost.setEntity(new StringEntity(json, contentType));

		return httpPost;
	}

	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost)
	{

		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);

		return body;
	}

	private static String paseResponse(HttpResponse response)
	{
		log.info("get response from http server..");
		HttpEntity entity = response.getEntity();

		log.info("response status: " + response.getStatusLine());

		String body = null;
		try
		{
			body = EntityUtils.toString(entity, "UTF-8");
			log.info(body);
		}
		catch (ParseException e)
		{
			log.error(e.getMessage());
		}
		catch (IOException e)
		{
			log.error(e.getMessage());
		}

		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost)
	{
		log.info("execute post...");
		HttpResponse response = null;

		try
		{
			response = httpclient.execute(httpost);
		}
		catch (ClientProtocolException e)
		{
			log.error(e.getMessage());
		}
		catch (IOException e)
		{
			log.error(e.getMessage());
		}
		return response;
	}

}

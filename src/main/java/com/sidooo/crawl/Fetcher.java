package com.sidooo.crawl;

import java.io.OutputStream;
import java.net.URL;

import org.apache.hadoop.fs.FileSystem;

import com.sidooo.seed.Account;

abstract
public class Fetcher extends Thread{
	
	protected URL url;
	
	
	protected String username;
	
	protected String password;

	public Fetcher(URL url) {
		this.url = url;
	}
	
	public void setAccount(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public abstract FetchContent fetch() throws Exception;
	
	public static Fetcher getInstance(String urlPath) {
		
		URL url;
		try {
			url = new URL(urlPath);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

		if ("http".equals(url.getProtocol())) {
			return new HttpFetcher(url);
		} else {
			return null;
		}
	}
}
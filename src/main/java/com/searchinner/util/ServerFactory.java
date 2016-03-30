package com.searchinner.util;

import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class ServerFactory {
	public static final String SOLR_URL = "http://172.18.3.2:16477/solr";
	//public static final String SOLR_URL = "http://127.0.0.1:16477/solr";
	private static HttpSolrServer server; // 增删改
	private static HttpSolrServer client; // 查找

	private ServerFactory() {

	}

	public static HttpSolrServer getHttpSolrServer() {
		if (server == null) {
			synchronized (ServerFactory.class) {
				if (server == null) {
					server = new HttpSolrServer(SOLR_URL);
					server.setRequestWriter(new BinaryRequestWriter());
				}
			}
		}

		return server;
	}

	public static HttpSolrServer getHttpSolrClient() {
		if (client == null) {
			synchronized (ServerFactory.class) {
				if (client == null) {
					client = new HttpSolrServer(SOLR_URL);
					client.setMaxRetries(1);
					client.setConnectionTimeout(5000);
				}
			}
		}

		return client;
	}
}

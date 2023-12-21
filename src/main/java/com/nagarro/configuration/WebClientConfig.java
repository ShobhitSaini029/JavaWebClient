package com.nagarro.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

	@Value("${firstApi.url}")
	private String firstApiUrl;

	@Value("${secondApi.url}")
	private String secondApiUrl;

	@Value("${thirdApi.url}")
	private String thirdApiUrl;
	
	@Bean(name="webClientFirstApi")
	public WebClient.Builder webClientBuilderApi1(){
		return WebClient.builder()
				.baseUrl(firstApiUrl)
				.clientConnector(new ReactorClientHttpConnector(getHttpClient(2000)));
	}
	
	@Bean(name="webClienSecondApi")
	public WebClient.Builder webClientBuilderApi2(){
		return WebClient.builder()
				.baseUrl(secondApiUrl)
				.clientConnector(new ReactorClientHttpConnector(getHttpClient(1000)));
	}
	
	@Bean(name="webClientThirdApi")
	public WebClient.Builder webClientBuilderApi3(){
		return WebClient.builder()
				.baseUrl(thirdApiUrl)
				.clientConnector(new ReactorClientHttpConnector(getHttpClient(1000)));
	}
	
	private HttpClient getHttpClient(int timeoutMillis) {
		// TODO Auto-generated method stub
		return HttpClient
				.create()
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeoutMillis)
				.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(timeoutMillis, TimeUnit.MILLISECONDS))
						.addHandlerLast(new WriteTimeoutHandler(timeoutMillis, TimeUnit.MILLISECONDS)));
	}
	
}

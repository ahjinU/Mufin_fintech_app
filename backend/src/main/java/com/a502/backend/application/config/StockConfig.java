package com.a502.backend.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
//메시지 브로커가 지원하는 ‘WebSocket 메시지 처리’를 활성화
@EnableWebSocketMessageBroker
public class StockConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	// //클라이언트에서 websocket에 접속하는 endpoint를 등록
	public void registerStompEndpoints(StompEndpointRegistry registry){
		registry.addEndpoint("/ws-connection").setAllowedOriginPatterns("*")
			.withSockJS();
	}

	@Override
	//메모리 기반의 Simple Message Broker를 활성화
	//한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 할 때 사용하는 브로커를 구성
	public void configureMessageBroker(MessageBrokerRegistry registry){
		// 메세지 수신시 요청 URL
		registry.enableSimpleBroker("/sub");
		// 메세지 송신시 요청 URL
		registry.setApplicationDestinationPrefixes("/pub");
	}
}

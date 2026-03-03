package com.example.agent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AgentApplicationTests {

	@MockBean
	private com.example.agent.mcp.McpHttpClient mcpHttpClient;


	@MockBean
	private Object backlogAgent; // replace with real type

	@Test
	void contextLoads() {
	}
}
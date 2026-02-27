package com.example.agent.tools;

import com.example.agent.mcp.McpHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GitHubMcpToolsTest {
    @SpringBootTest
    @ActiveProfiles("test")
    class AgentControllerIT {
    }
    @Test
    void should_call_mcp_tool() {

        McpHttpClient mcp = mock(McpHttpClient.class);

        when(mcp.callTool(eq("create_issue"), anyMap()))
                .thenReturn(Mono.just(Map.of(
                        "number", 42,
                        "html_url", "https://github.com/x/y/issues/42"
                )));

        GitHubMcpTools tools =
                new GitHubMcpTools(mcp, "owner-test", "repo-test");

        String result = tools.createIssue("title", "body");

        assertTrue(result.contains("Issue created successfully"));
        verify(mcp, times(1)).callTool(eq("create_issue"), anyMap());
    }
}
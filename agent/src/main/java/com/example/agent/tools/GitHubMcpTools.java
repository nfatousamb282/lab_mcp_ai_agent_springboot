package com.example.agent.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import com.example.agent.mcp.McpHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Profile("!ci")
public class GitHubMcpTools implements AgentTool {

    private final McpHttpClient mcp;
    private final String owner;
    private final String repo;

    public GitHubMcpTools(
            McpHttpClient mcp,
            @Value("${github.owner}") String owner,
            @Value("${github.repo}") String repo
    ) {
        this.mcp = mcp;
        this.owner = owner;
        this.repo = repo;
    }

    @Tool("Create a GitHub issue in the configured repository.")
    public String createIssue(
            @P("title of the issue") String title,
            @P("body of the issue") String body
    ) {

        Map result = (Map) mcp.callTool("create_issue", Map.of(
                "owner", owner,
                "repo", repo,
                "title", title,
                "body", body
        )).block();

        return "Issue created successfully: " + result;
    }
}
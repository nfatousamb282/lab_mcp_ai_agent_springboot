package com.example.agent.web;

import com.example.agent.tools.GitHubMcpTools;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final GitHubMcpTools githubMcpTools;

    public TestController(GitHubMcpTools githubMcpTools) {
        this.githubMcpTools = githubMcpTools;
    }

    @GetMapping("/issue")
    public String testIssue() {
        String title = "Test MCP issue";
        String body = "Ceci est un test créé depuis Spring Boot";

        return githubMcpTools.createIssue(title, body);
    }
}
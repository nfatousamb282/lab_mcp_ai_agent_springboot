FROM node:20-alpine
WORKDIR /app
COPY package.json package-lock.json* ./
RUN npm install --omit=dev
COPY src ./src
EXPOSE 3333
ENV PORT=3333
ENV MCP_PATH=/mcp
CMD ["npm","start"]
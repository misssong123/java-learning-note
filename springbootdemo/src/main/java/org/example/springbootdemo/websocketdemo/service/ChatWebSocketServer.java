package org.example.springbootdemo.websocketdemo.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint("/websocket")
public class ChatWebSocketServer {

    // 用于存储所有在线连接的会话，线程安全集合
    private static final CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    // 文件保存目录（需提前创建或自动创建）
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + File.separator + "uploads";

    static {
        // 初始化上传目录
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        log.info("新连接加入，当前在线数：{}", sessions.size());
        // 可以给客户端发送欢迎消息
        sendMessage(session, "连接成功！当前在线人数：" + sessions.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        log.info("连接关闭，当前在线数：{}", sessions.size());
    }

    /**
     * 收到文本消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        // 尝试解析是否为 JSON 文件信息
        if (message.startsWith("{") && message.contains("fileName")) {
            // 简单解析（实际可使用 Jackson 等库）
            String fileName = extractFileNameFromJson(message); // 自定义解析方法
            session.getUserProperties().put("pendingFileName", fileName);
            log.info("收到文件元数据，文件名：{}", fileName);
            sendMessage(session, "准备接收文件：" + fileName);
        } else {
            // 普通聊天消息，广播
            broadcast("用户 " + session.getId() + " 说: " + message, session);
        }
    }

    /**
     * 收到二进制消息（文件上传）
     * 注意：必须设置 maxMessageSize，否则默认 8KB 会拒绝大文件
     */
    @OnMessage(maxMessageSize = 10 * 1024 * 1024) // 最大 10MB
    public void onBinaryMessage(ByteBuffer byteBuffer, Session session) throws IOException {
        log.info("收到二进制消息，大小：{} 字节，来自 {}", byteBuffer.remaining(), session.getId());

        // 从会话中获取之前存储的文件名
        String fileName = (String) session.getUserProperties().remove("pendingFileName");
        if (fileName == null) {
            // 没有元数据，使用默认名称
            fileName = "unnamed_" + System.currentTimeMillis() + ".bin";
        }
        // 安全处理文件名（防止路径遍历）
        fileName = new File(fileName).getName(); // 简单处理，仅保留文件名部分
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        // 保存文件
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(byteBuffer.array());
        }

        log.info("文件保存成功：{}", filePath);
        sendMessage(session, "文件上传完成：" + fileName + "，大小：" + byteBuffer.remaining() + " 字节");
    }
    // 辅助方法：从 JSON 中提取文件名（仅示例，正式推荐使用 JSON 库）
    private String extractFileNameFromJson(String json) {
        // 简单正则，生产环境建议用 Jackson/Gson
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\"fileName\"\\s*:\\s*\"([^\"]+)\"");
        java.util.regex.Matcher m = p.matcher(json);
        return m.find() ? m.group(1) : "unknown.bin";
    }
    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("WebSocket 错误，session id：{}", session.getId(), error);
        // 可关闭会话
        try {
            session.close();
        } catch (IOException e) {
            log.error("关闭会话失败", e);
        }
    }

    /**
     * 向指定会话发送文本消息
     */
    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("发送消息失败", e);
        }
    }

    /**
     * 广播文本消息给所有客户端（排除源会话）
     */
    private void broadcast(String message, Session sourceSession) {
        for (Session session : sessions) {
            if (session != sourceSession && session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    log.error("广播消息失败", e);
                }
            }
        }
    }

    /**
     * 广播给所有人（包含源）
     */
    private void broadcastAll(String message) {
        for (Session session : sessions) {
            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    log.error("广播消息失败", e);
                }
            }
        }
    }
}
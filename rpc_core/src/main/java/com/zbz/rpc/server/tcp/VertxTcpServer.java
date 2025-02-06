package com.zbz.rpc.server.tcp;

import com.zbz.rpc.server.HttpServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;

import java.net.Socket;

/**
 * Classname: VertxTcpServer
 * Package: com.zbz.rpc.server.tcp
 * Decription:tcp服务端
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 11:41
 * @Version: v1.0
 */
public class VertxTcpServer implements HttpServer {
    private byte[] handleRequest(byte[] requestData) {
        // 在这里编写处理请求的逻辑，根据 requestData 构造响应数据并返回
        // 这里只是一个示例，实际逻辑需要根据具体的业务需求来实现
        return "Hello, client!!!".getBytes();
    }
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
        NetServer server = vertx.createNetServer();
        server.connectHandler(new VetxTcpHandler());
//        server.connectHandler(socker->{
//            String testMessage = "Hello, client!!!";
//            int messageLength = testMessage.getBytes().length;
//            RecordParser parser = RecordParser.newFixed(messageLength);
//            parser.setOutput(new Handler<Buffer>() {
//                @Override
//                public void handle(Buffer buffer) {
//                    String s = new String(buffer.getBytes());
//                    System.out.println("s = " + s);
//                    if(testMessage.equals(s)){
//                        System.out.println("good");
//                    }
//                }
//            });
//            socker.handler(parser);
//        });
        server.listen(port, result->{
            if (result.succeeded()) {
                System.out.println("Server started on port " + port);
            } else {
                System.out.println("Failed to start server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        VertxTcpServer server = new VertxTcpServer();
        server.doStart(8080);
    }
}

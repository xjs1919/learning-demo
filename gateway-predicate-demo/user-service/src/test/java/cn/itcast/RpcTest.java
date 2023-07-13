package cn.itcast;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class RpcTest {

    public static void main(String[] args) throws Exception {
        // rpcUseHttp();
        rpcUseSocket();
    }

    public static void rpcUseHttp() throws Exception{
        String baidu = "http://www.baidu.com";
        // 构造url对象
        URL url = new URL(baidu);
        // 建立连接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        // 读取输入流
        InputStream in = conn.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte buff[] = new byte[1024*1024];
        int len = 0;
        while((len = in.read(buff)) > 0){
            // 写入输出流
            out.write(buff, 0, len);
        }
        out.close();
        in.close();
        // 得到响应
        String response = new String(out.toByteArray());
        System.out.println(response);
    }

    public static void rpcUseSocket() throws Exception{
        // 建立连接
        Socket socket = new Socket("www.baidu.com", 80);
        // 发送请求
        OutputStream out = socket.getOutputStream();
        out.write("GET / HTTP/1.1\n".getBytes());
        out.write("\n".getBytes());
        out.write("\n".getBytes());
        out.flush();
        // 读取输入流
        InputStream in = socket.getInputStream();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte buff[] = new byte[1024*1024];
        int len = 0;
        while((len = in.read(buff)) > 0){
            // 写入输出流
            bout.write(buff, 0, len);
        }
        out.close();
        in.close();
        bout.close();
        socket.close();
        // 得到响应
        String response = new String(bout.toByteArray());
        System.out.println(response);
    }

}

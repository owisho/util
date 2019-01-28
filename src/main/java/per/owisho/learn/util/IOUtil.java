package per.owisho.learn.util;

import lombok.extern.log4j.Log4j2;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Log4j2
public class IOUtil {

    /**
     * 关闭io的通用方法
     * @param ios io流列表
     */
    public static void closeIO(Closeable... ios) {

        if (ios == null)
            return;
        for (Closeable io : ios) {
            if (null != io) {
                try {
                    io.close();
                } catch (Exception e) {
                    log.error(e.getMessage(),e);
                }
            }
        }

    }

    /**
     * 使用缓冲区将数据从输入流输出到输出流
     * @param buffer 缓冲区大小
     * @param os 输出流
     * @param in 输入流
     * @throws IOException IO异常
     */
    public static void bufferedOutput(byte[] buffer, OutputStream os, InputStream in) throws IOException {

        int length = buffer.length;
        int len = -1;
        while ((len = in.read(buffer)) != -1) {
            if (len < length) {
                os.write(buffer, 0, len);
                break;
            }
            os.write(buffer);
        }
        os.flush();

    }

}

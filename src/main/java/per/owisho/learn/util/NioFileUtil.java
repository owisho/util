package per.owisho.learn.util;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 文件复制类，使用nio
 */
@Log4j2
public class NioFileUtil {

    /**
     * 使用channel将orgFile复制到targetFile
     * @param orgFile 原始文件
     * @param targetFile 目标文件
     */
    public static void copyByChannel(String orgFile,String targetFile) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try{
            inputChannel = FileChannel.open(Paths.get(orgFile), StandardOpenOption.READ);
            outputChannel = FileChannel.open(Paths.get(targetFile),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
            inputChannel.transferTo(0,inputChannel.size(),outputChannel);
        }catch(IOException e){
            log.error(e.getMessage(),e);
        }finally {
            IOUtil.closeIO(inputChannel,outputChannel);
        }
    }

    /**
     * 使用直接缓冲区将文件复制到targetFile
     * @param orgFile 原始文件
     * @param targetFile 目标文件
     */
    public static void copyByDirectBuf(String orgFile,String targetFile){
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try{
            inputChannel = FileChannel.open(Paths.get(orgFile),StandardOpenOption.READ);
            outputChannel = FileChannel.open(Paths.get(targetFile),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);
            MappedByteBuffer inputMapperedByteBuffer = inputChannel.map(FileChannel.MapMode.READ_ONLY,0,inputChannel.size());
            MappedByteBuffer outputMapperedByteBuffer = outputChannel.map(FileChannel.MapMode.READ_WRITE,0,inputChannel.size());
            byte[] bytes = new byte[inputMapperedByteBuffer.limit()];
            inputMapperedByteBuffer.get(bytes);
            outputMapperedByteBuffer.put(bytes);
        }catch (IOException e){
            log.error(e.getMessage(),e);
        }finally {
            IOUtil.closeIO(inputChannel,outputChannel);
        }


    }

    /**
     * 利用通道文件复制
     * 非直接缓冲区
     * @param orgFile 原始文件
     * @param targetFile 目标文件
     */
    public static void copy(String orgFile,String targetFile){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try{
            fileInputStream = new FileInputStream(orgFile);
            fileOutputStream = new FileOutputStream(targetFile);

            inputChannel =  fileInputStream.getChannel();
            outputChannel = fileOutputStream.getChannel();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while(inputChannel.read(byteBuffer)!=-1){
                byteBuffer.flip();
                outputChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeIO(fileInputStream,fileOutputStream,inputChannel,outputChannel);
        }
    }

}

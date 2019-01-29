package per.owisho.learn.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将json字符串转化成java对象
     * @param jsonString json字符串
     * @param cls java对象类型
     * @param <T> java对象类型
     * @return 对象
     */
    public static <T> T jsonToObject(String jsonString,Class<T> cls){
        try {
            return objectMapper.readValue(jsonString,cls);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 将byte数组转化成java对象
     * @param byteArr jsonbyte数组
     * @param cls java对象类型
     * @param <T> java对象类型
     * @return 对象
     */
    public static <T> T jsonToObject(byte[] byteArr,Class<T> cls){
        try {
            return objectMapper.readValue(byteArr,cls);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 将流数据转化成java对象
     * @param inputStream json格式流数据
     * @param cls java对象类型
     * @param <T> java对象类型
     * @return 对象
     */
    public static <T> T jsonToObject(InputStream inputStream, Class<T> cls){
        try {
            return objectMapper.readValue(inputStream,cls);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 将文件转化成java对象
     * @param file json文件数据
     * @param cls java对象类型
     * @param <T> java对象类型
     * @return 对象
     */
    public static <T> T jsonToObject(File file, Class<T> cls){
        try {
            return objectMapper.readValue(file,cls);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 将对象转化为json字符串，如果转化失败，返回空
     * @param obj 要输出为json字符串的对象
     * @return json字符串
     */
    public static String objectToJson(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 将对象转化为json数组，如果转化失败，返回空
     * @param obj 要输出为json数组的对象
     * @return json数组
     */
    public static byte[] objectToJsonArr(Object obj){
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }


}


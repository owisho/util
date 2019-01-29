package per.owisho.learn.util;

import lombok.Data;

public class JsonUtilTest {

    public static void main(String[] args) {
        MailBook mailBook = JsonUtil.jsonToObject("{\"id\":1125687077,\"text\":\"@stroughtonsmith You need to add a \\\"Favourites\\\" tab to TC/iPhone. Like what TwitterFon did. I can't WAIT for your Twitter App!! :) Any ETA?\",\"fromUserId\":855523, \"toUserId\":815309,\"languageCode\":\"en\"}",MailBook.class);
        System.out.println(mailBook);

        System.out.println(JsonUtil.objectToJson(mailBook));

        byte[] bytes = JsonUtil.objectToJsonArr(mailBook);
        String byteStr = new String(bytes);
        System.out.println(byteStr);
    }

}

@Data
class MailBook{
    private int id;
    private String text;
    private int fromUserId;
    private int toUserId;
    private String languageCode;
}

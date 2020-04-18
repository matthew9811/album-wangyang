import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.http.HttpUtil;
import com.shengxi.wangyang.common.util.DetectLabelUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class TestTiia {

    @Test
    public void testBase64() throws IOException {

        File file = new File("D:\\浏览器下载\\linux.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] image = new byte[fileInputStream.available()];
        int temp;
        int len = 0;
        while ((temp = fileInputStream.read()) != -1) {
            image[len++] = (byte) temp;
        }
        String encode = Base64Encoder.encode(image);
        System.out.println(encode);
        String params = "{\\\"ImageBase64\\\":\\" + encode + "}";
        Collection<String> strings = DetectLabelUtil.detectLabel("{\"ImageBase64\":\"  " + encode + " \"}");
        strings.forEach(str-> System.out.println(str));
    }
}

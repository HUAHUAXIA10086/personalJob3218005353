import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SimHashTest {
    @Test
    public void MD5_Hashtest(){
        SimHash.MD5_Hash("1323");
    }

    @Test
    public void First_FCtest(){
        SimHash.First_FC("lalalaaa");
    }

    @Test
    public void HMJLtest(){
        SimHash.HMJL("12","50");
    }

    @Test
    public void getFileTexttest() throws IOException {
        File originalFile = new File("D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig.txt");
        SimHash.getFileText(originalFile);
    }

    @Test
    public void writeToTxttest() throws IOException {
       SimHash.writeToTxt("测试测试测试");
    }
}

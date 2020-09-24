import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

public class SimHash {
    //定义待比较的字符串
    public static String MD5_Hash(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            return new BigInteger(1, md.digest(str.getBytes("UTF-8"))).toString(2);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }


    //将SimHash值降维，并以字符串形式返回
    public static String First_FC(String str) {
        //1.先创建一个存储SimHash值的128数组，并初始化为0
        int Hash_SZ[] = new int[128];
        for (int i = 0; i < Hash_SZ.length; i++)
            Hash_SZ[i] = 0;
        //2.将str字符串分词，并存入临时数组
        String[] newstr = str.split("/");
        for (int i = 0; i < newstr.length; i++) {
            String str_hash = MD5_Hash(newstr[i]);
            //这里主要是为了保证它是128位的二进制
            if (str_hash.length() < 128) {
                int que = 128 - str_hash.length();
                for (int j = 0; j < que; j++) {
                    str_hash = "0" + str_hash;

                }

            }
            //System.out.println(str_hash);
            char str_hash_fb[] = str_hash.toCharArray();
            //对每个词的Hash值（32位）求j位是1还是0,1的话加上该词的权重，0的话减去该词的权重
            for (int j = 0; j < Hash_SZ.length; j++) {
                if (str_hash_fb[j] == '1') {
                    Hash_SZ[j]++;

                } else {
                    Hash_SZ[j]--;

                }

            }

        }
        //4.将数组中的SimHash值降维，并以字符串形式返回
        String SimHash_number = "";
        for (int i = 0; i < Hash_SZ.length; i++) {
            if (Hash_SZ[i] <= 0)//小于等于0，就取0
                SimHash_number += "0";
            else//大于0，就取1
                SimHash_number += "1";

        }
        //System.out.println("");//换行
        return SimHash_number;

    }
    /* 函数名：HMJL(String a,String b)
     * 功能：a、b都是以String存储的二进制数，计算他们的海明距离，并将其返回
     * */


    public static int HMJL(String a, String b) {
        char[] FW1 = a.toCharArray();//将a每一位都存入数组中
        char[] FW2 = b.toCharArray();//将b每一位都存入数组中
        int haiming = 0;
        if (FW1.length == FW2.length) {//确保a和b的位数是相同的
            for (int i = 0; i < FW1.length; i++) {
                if (FW1[i] != FW2[i])//如果该位不同，海明距离加1
                    haiming++;

            }

        }
        return haiming / 2;

    }

    //    获取文本字符串
    public static String getFileText(File file) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String row = null;
        StringBuffer sb = new StringBuffer();
        while ((row = bufferedReader.readLine()) != null) {
            sb.append(row);
        }
        bufferedReader.close();
        String str = sb.toString();
        return str;
    }

    //    将结果写入文件
    public static void writeToTxt (String str) {
        File file = new File("D:/2020大三上学期/软工/个人项目/测试文件/test/result.txt");
        if (!file.exists()) {// 判断文件是否存在，若不存在则创建
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fileout = null;
        OutputStreamWriter output = null;
        try {
            fileout = new FileOutputStream(file);
            fileout = new FileOutputStream(file);
            output = new OutputStreamWriter(fileout, "utf-8");
            output.write(str + "\n");
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                fileout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException {
//        获取文件
        File originalFile = new File("D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig.txt");
        File origAdd = new File("D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_add.txt");
        File origDel = new File("D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_del.txt");
        File origDis_1 = new File("D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_dis_1.txt");
        File origDis_10 = new File("D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_dis_10.txt");
        File origDis_15 = new File("D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_dis_15.txt");

        System.out.println(("论文原文绝对路径：D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig.txt"));
        System.out.println(("抄袭论文1绝对路径：D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_add.txt"));
        System.out.println(("抄袭论文2绝对路径：D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_del.txt"));
        System.out.println(("抄袭论文3绝对路径：D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_dis_1.txt"));
        System.out.println(("抄袭论文4绝对路径：D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_dis_10.txt"));
        System.out.println(("抄袭论文5绝对路径：D:\\2020大三上学期\\软工\\个人项目\\测试文件\\test\\orig_0.8_dis_15.txt"));




        String s1 = getFileText(originalFile);
        String s2 = getFileText(origAdd);
        String s3 = getFileText(origDel);
        String s4 = getFileText(origDis_1);
        String s5 = getFileText(origDis_10);
        String s6 = getFileText(origDis_15);

        String a1 = First_FC(s1);
        String a2 = First_FC(s2);
        String a3 = First_FC(s3);
        String a4 = First_FC(s4);
        String a5 = First_FC(s5);
        String a6 = First_FC(s6);

        System.out.println("【orig.txt 文本】的SimHash值为：" + a1);
        System.out.println("【orig_0.8_add.txt 文本】的SimHash值为：" + a2);
        System.out.println("【orig_0.8_del.txt 文本】的SimHash值为：" + a3);
        System.out.println("【orig_0.8_dis_1.txt 文本】的SimHash值为：" + a4);
        System.out.println("【orig_0.8_dis_10.txt 文本】的SimHash值为：" + a5);
        System.out.println("【orig_0.8_dis_15.txt 文本】的SimHash值为：" + a6);

        String result1 = "【orig.txt 文本】和【orig.txt 文本(自己)】的海明距离为：" + HMJL(a1, a1) + "，相似度为：" + (100 - HMJL(a1, a1) * 100 / 128) + "%";
        String result2 = "【orig.txt 文本】和【orig_0.8_add.txt 文本】的海明距离为：" + HMJL(a1, a2) + "，相似度为：" + (100 - HMJL(a1, a2) * 100 / 128) + "%";
        String result3 = "【orig.txt 文本】和【orig_0.8_del.txt 文本】的海明距离为：" + HMJL(a1, a3) + "，相似度为：" + (100 - HMJL(a1, a3) * 100 / 128) + "%";
        String result4 = "【orig.txt 文本】和【orig_0.8_dis_1.txt 文本】的海明距离为：" + HMJL(a1, a4) + "，相似度为：" + (100 - HMJL(a1, a4) * 100 / 128) + "%";
        String result5 = "【orig.txt 文本】和【orig_0.8_dis_10.txt 文本】的海明距离为：" + HMJL(a1, a5) + "，相似度为：" + (100 - HMJL(a1, a5) * 100 / 128) + "%";
        String result6 = "【orig.txt 文本】和【orig_0.8_dis_15.txt 文本】的海明距离为：" + HMJL(a1, a6) + "，相似度为：" + (100 - HMJL(a1, a6) * 100 / 128) + "%";
        String result = result1 + '\n' + result2 + '\n' + result3 + '\n' + result4 + '\n' + result5 + '\n' + result6 + '\n';

//        将结果写入文件
        writeToTxt(result);
        System.out.println("查重结果已写入result.txt文件，结果为：");
        System.out.println(result);


    }
}

package patinousward.demo.demo01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * @Author jianfeng.tang
 * @create 2021/1/28 下午8:18
 */
public class ShellUtils {
    private static ProcessBuilder processBuilder = new ProcessBuilder();

    public static int exec(List<String> commands) {
        System.out.println(processBuilder.environment());
        processBuilder.command(commands);
        //将标准输入流和错误输入流合并，通过标准输入流读取信息
        processBuilder.redirectErrorStream(true);
        try {
            //启动进程
            Process start = processBuilder.start();
            //获取输入流
            InputStream inputStream = start.getInputStream();
            //转成字符输入流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gbk");
            int len = -1;
            char[] c = new char[1024];
            StringBuffer outputString = new StringBuffer();
            //读取进程输入流中的内容
            while ((len = inputStreamReader.read(c)) != -1) {
                String s = new String(c, 0, len);
                outputString.append(s);
                System.out.print(s);
            }
            inputStream.close();
            return start.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        String clientPath = "/home/patinousward/workspace/spark-2.3.0-odps0.32.2";
        //s1 - s7
        //
        int sh = exec(Arrays.asList("sh", "/home/patinousward/workspace/spark-2.3.0-odps0.32.2/crowdbuild.sh", "schedulerId=222", "putPlanIds=5fec3f9ce4b0bbaa9e5e5e8b", "crowdPackId=aaa"));
        System.out.println(sh);
        //int result = exec(Arrays.asList("java"));
        //System.out.println(result);
    }

}
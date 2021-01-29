package patinousward.demo.demo01;

import java.util.ArrayList;
import java.util.List;

public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread.sleep(15000);
        System.exit(0);
        throw new RuntimeException();
        //System.out.println("end");

        //process 中遇到文件不存在异常，基本都是环境变量的问题
        //可以通过 process 执行 sh xxx.sh 解决
        //xxx.sh 中指定export JAVA_HOME=XXX
        //export PATH=XXX:$JAVA_HOME


        int result = ShellUtils.exec(commands);
        if (result == 0) {
            poll.setSchedulerStatus(SchedulerStatus.SUCCESS);
        } else {
            poll.setSchedulerStatus(SchedulerStatus.FAILED);
        }
        System.out.println("exec" + schedulerId + "result:" + result);
    }
}

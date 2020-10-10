package patinousward.demo.demo01;

public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Thread.sleep(15000);
        System.exit(0);
        throw new RuntimeException();
        //System.out.println("end");
    }
}

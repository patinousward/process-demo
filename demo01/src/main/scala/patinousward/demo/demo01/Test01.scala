package patinousward.demo.demo01

import org.apache.commons.io.IOUtils
import org.junit.Test

import scala.concurrent.ExecutionContext.global

class Test01 {

  private implicit val executor = global

  @Test
  def test(): Unit = {
    //val builder = new ProcessBuilder("sh", "/home/patinousward/aaa.sh")
    val builder = new ProcessBuilder("sh", "/home/patinousward/ccc.sh")
    builder.redirectErrorStream(true)
    val process = builder.start()
    /*Future {
      val is = IOUtils.lineIterator(process.getInputStream, "utf-8")
      println(is.hasNext)
      while (is.hasNext) {
        print("--------------")
        println(is.next())
        println("end")
      }
      val exitCode = Option(process.waitFor)
      println(exitCode)
    }*/
    val is = IOUtils.lineIterator(process.getInputStream, "utf-8")
    println(is.hasNext)
    while (is.hasNext) {
      println(is.next())
    }
    //异步等2s去 destroy
    new Thread(new Runnable {
      override def run(): Unit = {
        Thread.sleep(100)
        process.destroyForcibly()//可能在继承sleep期间，无法进行destroy
        println(process.isAlive)
        println("destroy end")
      }
    }).start()

    val exitCode = Option(process.waitFor)
    println(exitCode)
    Thread.sleep(1000000)
    //1.命令自己重定向后，process获取inputstream没有值
    //2.java执行shell命令去启动另外一个java服务，会等main方法执行完成后才返回process,而且main方法报错，会导致process
    //3.java main 方法指定的退出码会返回个process
    //4.kill -9 会触发waitFor结束
    //http://www.bubuko.com/infodetail-1467743.html
    //输出流导致死锁
    //https://blog.csdn.net/shadow_zed/article/details/99636760?utm_medium=distribute.pc_relevant.none-task-blog-title-3&spm=1001.2101.3001.4242
    //查API可知晓，redirectErrorStream方法设置为ture的时候，会将getInputStream()，getErrorStream()两个流合并，自动会清空流，无需我们自己处理。如果是false，getInputStream()，getErrorStream()两个流分开，就必须自己处理，程序如下
  }

}

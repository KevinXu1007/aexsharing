package com.aexsharing.anysc.controller;

import com.aexsharing.anysc.task.AsyncTask;
import com.aexsharing.core.base.BaseController;
import com.aexsharing.core.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author : Kevin Xu
 * Date: 2018/6/8
 */

@RestController
@RequestMapping("/")
public class TestController extends BaseController {

    private final AsyncTask task;
    private static final Semaphore semaphore=new Semaphore(2);

    @Autowired
    public TestController(AsyncTask task) {
        this.task = task;
    }

    public String rescourceTest(){

        int availablePermits=semaphore.availablePermits();//可用资源数
        if(availablePermits>0){
            System.out.println("抢到资源");
        }else{
            System.out.println("资源已被占用，稍后再试");
            System.out.println("Resource is busy！");
        }
        try {
            semaphore.acquire(1);  //请求占用一个资源
            System.out.println("资源正在被使用");
            Thread.sleep(30000);//放大资源占用时间，便于观察
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            semaphore.release(1);//释放一个资源
        }
        return "Success";
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void executorTest() throws IOException, ExecutionException, InterruptedException {
        /*Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(1234));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        int coreNum = Runtime.getRuntime().availableProcessors();
        Processor[] processors = new Processor[coreNum];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new Processor();
        }
        int index = 0;
        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            for (SelectionKey key : keys) {
                keys.remove(key);
                if (key.isAcceptable()) {
                    ServerSocketChannel acceptServerSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = acceptServerSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("Accept request from {}"+ socketChannel.getRemoteAddress());
                    Processor processor = processors[(int) ((index++) % coreNum)];
                    processor.addChannel(socketChannel);
                    processor.wakeup();
                }
            }
        }*/
        ExecutorService service =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Callable<String> task = new Callable<String>() {
            public String call() {
                return rescourceTest();
            }
        };
         Future future = service.submit(task);
         System.out.println(future.get());

    }

    @RequestMapping(value = "task", method = RequestMethod.GET)
    public Result task() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
        return new Result<>(end - start);
    }


    /**
     * 查找所有(不带分页)
     *
     * @return result
     */
    @Override
    public Result<List> findAll() {
        return null;
    }

    /**
     * 带分页
     *
     * @param start    起始页
     * @param pageSize 页码数
     * @return result
     */
    @Override
    public Result<Page> findAll(@PathVariable int start, @PathVariable int pageSize) {
        return null;
    }

    /**
     * 根据id查看模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result findById(@PathVariable Long id) {
        return null;
    }

    /**
     * 根据名字查找模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result findByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根据名字删除模型
     *
     * @param name name
     * @return result
     */
    @Override
    public Result<Boolean> delByName(@PathVariable String name) {
        return null;
    }

    /**
     * 根据id删除模型
     *
     * @param id id
     * @return result
     */
    @Override
    public Result<Boolean> delById(@PathVariable Long id) {
        return null;
    }

    /**
     * 添加模型
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> add(@RequestBody Object model) {
        return null;
    }

    /**
     * 更新
     *
     * @param model model
     * @return result
     */
    @Override
    public Result<Boolean> update(@RequestBody Object model) {
        return null;
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return result
     */
    @Override
    public Result<Boolean> delByIds(@PathVariable List ids) {
        return null;
    }
}

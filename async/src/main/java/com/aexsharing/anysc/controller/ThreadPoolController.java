package com.aexsharing.anysc.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThreadPoolController {

    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolController.class);


    public class tryExecutor implements Callable<String>{
        String request;
        HttpServletResponse response;

        public tryExecutor (String request,  HttpServletResponse response) {
            this.request=request;
            this.response=response;
        }
        @Override
        public String call() throws Exception {
            long time_1 = System.currentTimeMillis();
            logger.error(time_1+"threadpool activeCount::"+threadpool.getActiveCount());
            String Jason=request;
            System.out.println(Jason);
            try {
                logger.error(time_1+"Sleep five seconds "+Jason);
                Thread.sleep(5000);
                response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                logger.error(time_1+"Sleep 2 seconds "+Jason);
                Thread.sleep(2000);
                response.getWriter().print(Jason);
                response.flushBuffer();
            } catch (Exception e) {
                // TODO: handle exception
            }
            return Jason;
        }

    }
    static ThreadPoolExecutor threadpool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    @RequestMapping(value = "/ScheduDemo", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody Object ScheduDemo(String demoType, HttpServletResponse response) {
        try {
            Callable task=new tryExecutor ( demoType,   response);
            Future result=threadpool.submit(task);
            logger.error(result.get().toString());
            return result.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


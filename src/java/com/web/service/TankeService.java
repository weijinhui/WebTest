package com.web.service;/**
 * @Auther: Administrator
 * @Date: 2019/1/8 0008 17:42
 * @Description:
 */

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: WebTest
 *
 * @description:
 *
 * @author: Carson Wei
 *
 * @create: 2019-01-08 17:42
 **/
@Service
public class TankeService {
    /**
     * 模拟登陆
     *
     * @param userName
     *            用户名
     * @param pwd
     *            密码
     *
     * **/
    public Map<String, String> login(String userName, String pwd) throws Exception {
        Map<String, String> cookies = null;
        Connection connection = Jsoup.connect("http://www.91tanke.com/member/login.php");
        // 伪造请求头
        connection.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
                "gzip, deflate");
        connection.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
        connection.header("Content-Length", "72").header("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        connection.header("Host", "qiaoliqiang.cn").header("Referer", "http://qiaoliqiang.cn/Exam/");
        connection.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .header("X-Requested-With", "XMLHttpRequest");// 配置模拟浏览器
        connection.data("forward", "http://www.91tanke.com/").data("username", userName).data("password", pwd)
                .data("submit", "");
        Connection.Response res = connection.ignoreContentType(true).method(Connection.Method.POST).execute();
        // 登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
        System.out.println(res.body());
        cookies = res.cookies();
        return cookies;
    }

    public void getTask(Map<String, String> cookies){
        try {
            String url = "http://www.91tanke.com/yptask/?catid=4";
            // 直接获取DOM树，带着cookies去获取
            Document document = Jsoup.connect(url).cookies(cookies).post();
            Elements elements = document.select("#taskList .c4");// 获取任务列表，可以通过查看页面源码代码得知
            String taobaohao = document.select(".iyellow").text();
            for (Element et : elements) {
                String  c42 = et.select(".c42").html();
                String itemid =c42.substring(c42.length()-7,c42.length());

                System.out.println("itemid:"+itemid+"  taobaohao:"+taobaohao);

                Connection connection = Jsoup
                        .connect("http://www.91tanke.com/yptask/acc.php");
                // 伪造请求头
                connection.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
                        "gzip, deflate");
                connection.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
                connection.header("Content-Length", "72").header("Content-Type",
                        "application/x-www-form-urlencoded; charset=UTF-8");
                connection.header("Host", "qiaoliqiang.cn").header("Referer", "http://qiaoliqiang.cn/Exam/");
                connection.header("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                        .header("X-Requested-With", "XMLHttpRequest");// 配置模拟浏览器
                connection.data("itemid", itemid).data("taobaohao", taobaohao);
                Connection.Response res = connection.ignoreContentType(true).method(Connection.Method.POST).cookies(cookies).execute();
                System.out.println(res.body());
            }
        }catch (Exception e){

        }
    }
}

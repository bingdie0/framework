package com.my.framework.crawler.test;

import org.jsoup.nodes.Element;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.HtmlNode;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author: Mr.WangJie
 * @date: 2018-09-19
 **/
public class GithubRepoPageProcessor extends HtmlNode implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        Selectable selectable = page.getHtml().xpath("div[@data-spm]");
        if (selectable instanceof HtmlNode) {
            HtmlNode htmlNode = (HtmlNode) selectable;
            List<Element> elements = htmlNode.getElements();
            for (Element element : elements) {

            }
        }
//        for (Selectable selectable : data) {
            /*if (selectable instanceof PlainText) {
                PlainText text = (PlainText) selectable;
                String[] split = text.getFirstSourceText().split("data-spm=");
                String[] split1 = split[1].split("\"");
                String dataSpm = split1[1];
                System.out.println(dataSpm);
            }*/
//        }
//        page.putField("dataIds", dataIds);
      /*  List<String> first = page.getHtml().xpath("//a[@class='category-name category-name-level1 J_category_hash']/text()").all();
        page.putField("first", first);
        List<String> second = page.getHtml().xpath("//a[@class='category-name']/text()").all();
        page.putField("second", second);
        System.out.println(1);*/

      /*  String imgUrl = null;
        Selectable selectable = page.getHtml().xpath("//li[@class='tb-selected']/div/a/img");
        if (selectable instanceof HtmlNode) {
            HtmlNode htmlNode = (HtmlNode) selectable;
            List<Element> elements = htmlNode.getElements();
            Element element = elements.get(0);
            imgUrl = element.attr("data-src");
        }
        String title = page.getHtml().xpath("//h3[@class='tb-main-title']/text()").toString();
        System.out.println(imgUrl);
        System.out.println(title);*/
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String categoryUrl = "https://www.taobao.com/tbhome/page/market-list";
        String detailUrl = "https://item.taobao.com/item.htm?spm=a219r.lm874.14.15.685d1bf42t9mVN&id=576245292245&ns=1&abbucket=2";
        Spider.create(new GithubRepoPageProcessor()).addUrl(categoryUrl).run();
    }
}

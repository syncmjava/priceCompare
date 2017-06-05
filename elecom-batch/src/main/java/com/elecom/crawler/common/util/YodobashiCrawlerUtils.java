package com.elecom.crawler.common.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elecom.crawler.common.crawler.WebCrawler;
import com.elecom.crawler.entity.Product;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class YodobashiCrawlerUtils implements CrawlerUtils {

	protected static final Logger logger = LoggerFactory.getLogger(YodobashiCrawlerUtils.class);

	public List<Product> crawlerSearchPages(String url) {
		logger.info("YodobashiCrawlerUtils.crawlerSearchPages start!!!");

		List<Product> list = new ArrayList<Product>();
		WebCrawler wc = new WebCrawler();
		WebURL weburl = new WebURL();
		weburl.setURL(url);
		try {
			Page page = wc.processPage(weburl);
			if (page.getParseData() instanceof HtmlParseData) {

				HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
				String html = htmlParseData.getHtml();
				Document doc = Jsoup.parse(html);

				Elements productList = doc.select("div.pListBlock");
				logger.info("prodcut count : " + productList.size());
				for (Element element : productList) {
					// div[class='pName'] Tag
					Elements prodcutInfo = element.select("div.pName>p");
					String brandName = "";
					String productNo = "";
					String productName = "";
					if (prodcutInfo.size() > 1) {
						brandName = prodcutInfo.get(0).text();
						productName = prodcutInfo.get(1).text();
						int index = productName.indexOf("[");
						productNo = productName.substring(0, index).trim();
					}
					// prouct link
					Elements productlink = element.select("a.js_productListPostTag");
					String link = productlink.attr("href");
					// prouct image link
					Elements productImagelink = element.select("div.pImg>img");
					String imageLink = productImagelink.attr("src");
					// price
					Elements productPrice = element.select("span.productPrice");
					String price = productPrice.text().substring(1).replace(",", "");
					// ポイント
					Elements productPoint = element.select("span.orange");
					String ponit = productPoint.text();
					Product product = new Product();
					product.setProduct_no(productNo);
					product.setProduct_name(productName);
					product.setProduct_price(price);
					product.setProduct_point(ponit);
					product.setProduct_link_url(link);
					product.setProduct_image_url(imageLink);
					list.add(product);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("YodobashiCrawlerUtils.crawlerSearchPages end!!!");
		return list;
	}

}

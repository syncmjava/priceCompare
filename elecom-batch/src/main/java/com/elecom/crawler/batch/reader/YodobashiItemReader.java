package com.elecom.crawler.batch.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.elecom.crawler.common.util.CrawlerUtils;
import com.elecom.crawler.entity.Product;

public class YodobashiItemReader extends AbstractPagingItemReader<Product> {

	@Autowired
	private CrawlerUtils utils;
	
	private boolean flg = true;

	@Override
	protected void doReadPage() {
		List<Product> list = new ArrayList<Product>();
		if (utils != null && flg) {
			list = utils.crawlerSearchPages("http://www.yodobashi.com/p1/?word=%E3%82%A8%E3%83%AC%E3%82%B3%E3%83%A0");
			flg = false;
		}
		if (results == null) {
			results = new CopyOnWriteArrayList<Product>();
		} else {
			results.clear();
		}
		results.addAll(list);
	}

	@Override
	protected void doJumpToPage(int itemIndex) {
		// TODO Auto-generated method stub

	}

	public CrawlerUtils getUtils() {
		return utils;
	}

	public void setUtils(CrawlerUtils utils) {
		this.utils = utils;
	}

}

package com.elecom.crawler.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.elecom.crawler.entity.Product;
import com.elecom.crawler.pilot.batch.writer.StoreUpdateItemWriter;

public class YodoboshiItemWriter implements ItemWriter<Product> {
    private static final Logger logger = LoggerFactory.getLogger(StoreUpdateItemWriter.class);

	@Override
	public void write(List<? extends Product> items) throws Exception {
		// TODO Auto-generated method stub
		for (Product product : items) {
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>" + product.getProduct_no());
		}
		
	}

}

package com.elecom.crawler.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.elecom.crawler.common.message.SpringMessageResourceMessages;
import com.elecom.crawler.entity.ProductBaseInfo;
import com.elecom.crawler.service.ProductInfoService;

@Controller
@RequestMapping("/")
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private SpringMessageResourceMessages messageSource;

    @Autowired
    private ProductInfoService service;

    /**
     * @param form
     * @param result
     * @return
     */
    @RequestMapping(value = "/")
    public String home(Model model) throws Exception {
        return "tables";
    }

    /**
     * @param form
     * @param result
     * @return
     */
    @RequestMapping(value = "/compare")
    public String index(@RequestParam(value="pno" ,required =false ) String pno, Model model) throws Exception {
    	logger.info("pno = " + pno);
    	model.addAttribute("pno", pno);
        return "priceCompare";
    }

    /**
     * Handle request to download an Excel document
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam(value="pno" ,required =false ) String pno, HttpServletRequest request, HttpServletResponse response) throws Exception { 

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
        	File file = ResourceUtils.getFile("classpath:template.xls");
        	XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
            Sheet sheet = wb.getSheetAt(0);
            List<ProductBaseInfo> productList = service.getProductAllPricesByPno(pno);
            for (ProductBaseInfo productBaseInfo : productList) {
				// 1 yamada
            	// 2 rakuten
            	// 3 Amazon
            	// 4 yodobasi
            	double f = Float.parseFloat(productBaseInfo.getProduct_price());
            	if ("1".equals(productBaseInfo.getShop_id())) {
                    sheet.getRow(3).getCell(3).setCellValue(f);
            	} else if ("2".equals(productBaseInfo.getShop_id())) {
                    sheet.getRow(4).getCell(3).setCellValue(f);
            	} else if ("3".equals(productBaseInfo.getShop_id())) {
                    sheet.getRow(5).getCell(3).setCellValue(f);
            	} else if ("4".equals(productBaseInfo.getShop_id())) {
                    sheet.getRow(6).getCell(3).setCellValue(f);
            	}
			}
        	wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        
    	response.reset();
    	response.setContentType("application/vnd.ms-excel;charset=utf-8");
    	response.setHeader("Content-Disposition", "attachment;filename="+ new String((pno + ".xls").getBytes(), "iso-8859-1"));
    	
    	 ServletOutputStream out = response.getOutputStream();BufferedInputStream bis = null;
         BufferedOutputStream bos = null;
         try {
             bis = new BufferedInputStream(is);
             bos = new BufferedOutputStream(out);
             byte[] buff = new byte[2048];
             int bytesRead;
             // Simple read/write loop.
             while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                 bos.write(buff, 0, bytesRead);
             }
         } catch (final IOException e) {
             throw e;
         } finally {
             if (bis != null)
                 bis.close();
             if (bos != null)
                 bos.close();
         }
    }


}

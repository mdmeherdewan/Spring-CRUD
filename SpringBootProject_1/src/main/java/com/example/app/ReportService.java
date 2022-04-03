package com.example.app;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class ReportService {
	
	@Autowired
	CustormerRepository customerRepository;
	
	public String selectAllCustomerForReport(String extension) throws Exception{
		String path = "F:\\Jasper_Reports";
		List<Customer> cusList =  customerRepository.findAll();
		
		
		//File file = ResourceUtils.getFile("classpath:CustomerDataReport");
		JasperDesign jd  = JRXmlLoader.load("CustomerDataReport.jrxml");
		
		JasperReport jrp = JasperCompileManager.compileReport(jd);
		JRBeanCollectionDataSource  jb= new JRBeanCollectionDataSource(cusList);
		Map<String, Object> param = new HashMap<>();
		param.put("createdBy", "Meher Dewan");
		JasperPrint jp = JasperFillManager.fillReport(jrp, param, jb);
		if (extension.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jp, path+"customer.html");
		}else {
			JasperExportManager.exportReportToPdfFile(jp, path+"customer.pdf");
		}
		return "Report generated in "+path;
		
	}

}

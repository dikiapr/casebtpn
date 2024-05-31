package com.casebtn.casebtn.service;

import com.casebtn.casebtn.model.Orders;
import com.casebtn.casebtn.repository.OrdersRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private OrdersRepository ordersRepository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\User\\Desktop";

        List<Orders> order = ordersRepository.findAll();

        File file = ResourceUtils.getFile("classpath:orderreport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(order);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
        JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\orderreport.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
JasperExportManager.exportReportToPdfFile(jasperPrint,path + "\\orderreport.pdf");
        }
        return "report generated in path: " + path;
    }
}














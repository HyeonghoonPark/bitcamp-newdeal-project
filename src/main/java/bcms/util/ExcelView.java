package bcms.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import bcms.domain.BusinessCard;

public class ExcelView extends AbstractXlsView {

    //private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    
    @Override
    protected void buildExcelDocument(Map<String, Object> model, 
            Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        // change the file name
        String filename = "members.xls";

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        response.setHeader("Content-Type", "application/vnd.ms-excel");
        
        @SuppressWarnings("unchecked")
        List<BusinessCard> BusinessCards = (List<BusinessCard>) model.get("excelList");

        // create excel xls sheet
        Sheet sheet = workbook.createSheet("Spring MVC AbstractXlsView");

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("번호");
        header.createCell(1).setCellValue("이름");
        header.createCell(2).setCellValue("회사명");
        header.createCell(3).setCellValue("직급");
        header.createCell(4).setCellValue("주소");
        header.createCell(5).setCellValue("휴대전화");
        header.createCell(6).setCellValue("일반전화");
        header.createCell(7).setCellValue("팩스");
        header.createCell(8).setCellValue("이메일");
        header.createCell(9).setCellValue("홈페이지");

        // Create data cells
        int rowCount = 1;
        int numCount = 1;
        for (BusinessCard biz : BusinessCards){
            Row courseRow = sheet.createRow(rowCount++);
            courseRow.createCell(0).setCellValue(numCount++);
            courseRow.createCell(1).setCellValue(biz.getName());
            courseRow.createCell(2).setCellValue(biz.getCname());
            courseRow.createCell(3).setCellValue(biz.getCposi());
            courseRow.createCell(4).setCellValue(biz.getCaddr());
            courseRow.createCell(5).setCellValue(biz.getMtel());
            courseRow.createCell(6).setCellValue(biz.getTel());
            courseRow.createCell(7).setCellValue(biz.getFax());
            courseRow.createCell(8).setCellValue(biz.getEmail());
            courseRow.createCell(9).setCellValue(biz.getHomepage());
        }
        System.out.println("build");
        
    }
}

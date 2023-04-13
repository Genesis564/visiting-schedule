package com.okei.visitingschedule.util;

import com.okei.visitingschedule.entity.schedule.Schedule;
import com.okei.visitingschedule.entity.schedule.Status;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ExcelGenerator {
    private List<Schedule> scheduleList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<Schedule> studentList) {
        this.scheduleList = studentList;
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Schedule");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "Дата", style);
        createCell(row, 1, "Статус", style);
        createCell(row, 2, "Посещающий", style);
        createCell(row, 3, "Преподаватель", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Schedule schedule: scheduleList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            Status scheduleStatus = Status.PLANNED;
            for (Status status:schedule.getStatus()) {
                scheduleStatus = status;
            }
            createCell(row, columnCount++, schedule.getVisiting() == null?schedule.getVisitingWeek():schedule.getVisiting().getDate(), style);
            createCell(row, columnCount++, scheduleStatus.getName(), style);
            createCell(row, columnCount++, schedule.getVisitorUser().getLastname()+" "+schedule.getVisitorUser().getFirstname()+" "+(schedule.getVisitorUser().getMiddlename().equals(null)?"":schedule.getVisitorUser().getMiddlename()), style);
            createCell(row, columnCount++, schedule.getVisitedUser().getLastname()+" "+schedule.getVisitedUser().getFirstname()+" "+(schedule.getVisitedUser().getMiddlename().equals(null)?"":schedule.getVisitedUser().getMiddlename()), style);
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}

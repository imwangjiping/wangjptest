package com.jwong.junit.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jwong on 2017/9/8.
 */
public class ExcelUtil {

    /**
     * 获取单元格内容（字符串）
     *
     * @param isE2007 是否是2007的拓展名
     * @param sheet   sheet
     * @param row     行数
     * @param col     列数
     * @return 单元格内的字符串
     */
    public static String getExcelCellForString(boolean isE2007, Sheet sheet, int row, int col) {
        if (!isE2007) {
            HSSFRow fieldRow = (HSSFRow) sheet.getRow(row);
            HSSFCell fieldcell = fieldRow.getCell((short) col);
            if (fieldcell != null && !"".equals(fieldcell)) {
                fieldcell.setCellType(HSSFCell.CELL_TYPE_STRING);
                return String.valueOf(fieldcell.getStringCellValue().trim());
            } else {
                return "";
            }
        } else {
            XSSFRow fieldRow = (XSSFRow) sheet.getRow(row);
            XSSFCell fieldcell = fieldRow.getCell((short) col);
            if (fieldcell != null && !"".equals(fieldcell)) {
                fieldcell.setCellType(XSSFCell.CELL_TYPE_STRING);
                return String.valueOf(fieldcell.getStringCellValue().trim());
            } else {
                return "";
            }
        }
    }

    /**
     * @param isE2007 是否是2007的拓展名
     * @param sheet   sheet
     * @param row     行数
     * @param col     列数
     * @param pattern 格式化
     * @return 单元格内日期格式化后的字符串
     */
    public static String getExcelCellForDate(boolean isE2007, Sheet sheet, int row, int col, String pattern) {
        if (!isE2007) {
            HSSFRow fieldRow = (HSSFRow) sheet.getRow(row);
            HSSFCell fieldcell = fieldRow.getCell((short) col);
            if (fieldcell != null && !"".equals(fieldcell)) {
                // 单元格格式为日期
                if (0 == fieldcell.getCellType()) {
                    // 单元格为日期格式
                    fieldcell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    double d = fieldcell.getNumericCellValue();
                    Date date = HSSFDateUtil.getJavaDate(d);
                    SimpleDateFormat dformat = new SimpleDateFormat(pattern);
                    return dformat.format(date);
                    // 单元格格式为字符串（文本）
                } else if (1 == fieldcell.getCellType()) {
                    // 如果Excel没有设置为文本模式，或者分列的话就默认输入为正确的时间来处理
                    // fieldcell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    // return
                    // String.valueOf(fieldcell.getStringCellValue().trim());
                    // 如果Excel没有设置为文本模式，或者分列的话就默认输入为正确的时间来处理
                    fieldcell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    try {
                        String source = String.valueOf(fieldcell.getStringCellValue().trim());
                        Date d = new SimpleDateFormat(pattern).parse(source);
                        String s = new SimpleDateFormat(pattern).format(d);
                        return s;
                    } catch (ParseException e) {
                        return "";
                    }
                }
            } else {
                // 单元格内没有内容
                return "";
            }
        } else {
            XSSFRow fieldRow = (XSSFRow) sheet.getRow(row);
            XSSFCell fieldcell = fieldRow.getCell((short) col);
            if (fieldcell != null && !"".equals(fieldcell)) {
                // 单元格格式为日期
                if (0 == fieldcell.getCellType()) {
                    // 单元格为日期格式
                    fieldcell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    double d = fieldcell.getNumericCellValue();
                    Date date = HSSFDateUtil.getJavaDate(d);
                    SimpleDateFormat dformat = new SimpleDateFormat(pattern);
                    return dformat.format(date);
                    // 单元格格式为字符串（文本）
                } else if (1 == fieldcell.getCellType()) {
                    // 如果Excel没有设置为文本模式，或者分列的话就默认输入为正确的时间来处理
                    // fieldcell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    // return
                    // String.valueOf(fieldcell.getStringCellValue().trim());
                    // 如果Excel没有设置为文本模式，或者分列的话就默认输入为正确的时间来处理
                    fieldcell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    try {
                        String source = String.valueOf(fieldcell.getStringCellValue().trim());
                        Date d = new SimpleDateFormat(pattern).parse(source);
                        String s = new SimpleDateFormat(pattern).format(d);
                        return s;
                    } catch (ParseException e) {
                        return "";
                    }
                }
            } else {
                // 单元格内没有内容
                return "";
            }
        }
        // 默认返回空
        return "";
    }

}
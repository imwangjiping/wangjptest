package com.jwong.junit.test;

import com.jwong.junit.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * TestDownLoadImg class
 *
 * @author J.Wong
 * @date 2018/04/03
 */
public class TestDownLoadImg {

    @Test
    public void downloadImages() throws FileNotFoundException {

        File excelFile = new File("/home/jwong/Desktop/images/allusers.xls");
        String myFileName = "alluser.xls";

        /**** 解析需要配置的一些参数 begin ****/
        boolean isE2007 = false;    // 是否是word 2007+
        int sumCount = 0;   // 总条数
        int successCount = 0;   // 成功条数
        int errorCount = 0; // 失败条数
        int titleLine = 0;  // 表头占行数
        int modelColCount = 3;  // 模板正确的总列数
        /**** 解析需要配置的一些参数 end ****/
        // 判断是否是excel 2007格式
        if (myFileName.endsWith("xlsx")) {
            isE2007 = true;
        }

        /*************************** 解析excel begin ***************************/
        Workbook wb = null;

        InputStream inputstream = new FileInputStream(excelFile);
        try {
            if (!isE2007) {
                wb = new HSSFWorkbook(inputstream); // Excel 2003
            } else {
                wb = new XSSFWorkbook(inputstream); // Excel 2007
            }
        } catch (IOException e) {
        }

        int numberOfSheet = wb.getNumberOfSheets();

        for (int i = 0; i < numberOfSheet; i++) {
            Row row = null;
            Sheet sheet = wb.getSheetAt(i); // 获得第一个sheet
            int rowNum = sheet.getLastRowNum(); // 获取行数
            row = sheet.getRow(0);
            int colNum = row.getPhysicalNumberOfCells(); // 获取列数

            /*************** 解析业务逻辑 替换这一块即可 begin ***************/
            for (int j = titleLine; j <= rowNum; j++) {
                try {

                    /****************************************************************/
                    String id = ExcelUtil.getExcelCellForString(isE2007, sheet, j, 0);    // 公司名称
                    String companyName = ExcelUtil.getExcelCellForString(isE2007, sheet, j, 1);    // 法人
                    String filePath = ExcelUtil.getExcelCellForString(isE2007, sheet, j, 2);    // 法人
                    /****************************************************************/
                    if (null != filePath && !"".equals(filePath)) {
                        String fileName = id + "-" + companyName + "-三证合一";
                        System.out.println("fileName -> " + id + "-" + companyName + "-三证合一");
                        down(filePath, fileName);
                    }

                } catch (Exception e) {

                }

            }
            /*************** 解析业务逻辑 替换这一块即可 end ***************/

        }
    }

    public static void down(String filePath, String fileName) {
        String url = "http://www.imwangjiping.com/download?filePath=" + filePath;
        byte[] btImg = getImageFromNetByUrl(url);
        if (null != btImg && btImg.length > 0) {
            System.out.println("读取到：" + btImg.length + " 字节");
            writeImageToDisk(btImg, fileName);
        } else {
            System.out.println("没有从该连接获得内容");
        }
    }

    /**
     * 将图片写入到磁盘
     *
     * @param img      图片数据流
     * @param fileName 文件保存时的名称
     */
    public static void writeImageToDisk(byte[] img, String fileName) {
        try {
            File file = new File("/home/jwong/Desktop/images/" + fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据地址获得数据的字节流
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     *
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

}

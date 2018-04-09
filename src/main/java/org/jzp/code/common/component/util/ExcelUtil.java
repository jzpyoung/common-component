package org.jzp.code.common.component.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.*;
import org.jzp.code.common.component.constant.DateConstants;
import org.jzp.code.common.component.vo.ExcelSheetVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * excel工具类
 * created by jiazhipeng on 2018/3/27
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    //wrap 内容操作的字符大小界限
    private static final int wrapCharSize = 50;

    public static ExcelUtil getInstance() {
        return new ExcelUtil();
    }

    /**
     * 导出excel
     *
     * @param excelFileName
     * @param excelSheetDTOS
     * @param realPath
     * @throws Exception
     */
    public static void exportExcel(String excelFileName, List<ExcelSheetVO> excelSheetDTOS, String realPath) throws Exception {
        FileOutputStream outputStream = null;
        try {
            File targetFile = new File(realPath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            outputStream = new FileOutputStream(targetFile + File.separator + excelFileName);
            createWorkBook(excelSheetDTOS).write(outputStream);
        } catch (Exception e) {
            logger.info("导出excel文件异常，文件={}", excelFileName);
            logger.info("导出excel文件异常", e);
            throw new RuntimeException("导出excel文件异常");
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * 创建WorkBook
     *
     * @param excelSheetDTOS
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> XSSFWorkbook createWorkBook(List<ExcelSheetVO> excelSheetDTOS) {
        // 创建excel工作簿
        XSSFWorkbook wb = new XSSFWorkbook();

        ExcelSheetVO excelSheetDTO;
        for (int i = 0; i < excelSheetDTOS.size(); i++) {
            try {
                excelSheetDTO = excelSheetDTOS.get(i);
                if (excelSheetDTO == null) {
                    throw new RuntimeException("excel第" + i + "个sheet页为空");
                }
                if (CollectionUtils.isEmpty(excelSheetDTO.getHeadKeys())) {
                    throw new RuntimeException("excel第" + i + "个sheet页表头为空");
                }
                if (CollectionUtils.isEmpty(excelSheetDTO.getDatas())) {
                    throw new RuntimeException("excel第" + i + "个sheet页数据为空");
                }

                createSheet(excelSheetDTO.getDatas(), excelSheetDTO.getHeadKeys(), wb, excelSheetDTO.getSheetName());
            } catch (Exception e) {
                logger.info("导出excel第" + i + "个sheet页异常,e:{}", e.getMessage());
            }
        }

        return wb;
    }

    private static <T> void createSheet(List<T> dataList, Map<String, String> headKeys, XSSFWorkbook wb, String sheetName) throws Exception {
        // 创建第一个sheet（页），并命名
        XSSFSheet sheet = wb.createSheet(sheetName);

        CreationHelper createHelper = wb.getCreationHelper();
        Set<String> sets = headKeys.keySet();
        int len = sets.size();
        // 提取vo属性名
        String[] columns = new String[len];
        for (int i = 0; i < len; i++) {
            columns[i] = sets.toArray()[i].toString();
        }
        //头部，第一行
        XSSFRow row = sheet.createRow(0);
        int i = 0;
        //定义style
        XSSFCellStyle style = wb.createCellStyle();
        // 定义表头
        for (Iterator<String> it = sets.iterator(); it.hasNext(); ) {
            String key = it.next();
            XSSFCell cell = row.createCell(i++);
            cell.setCellValue(createHelper.createRichTextString(headKeys.get(key)));
            cell.setCellStyle(getColumnTopStyle(wb, style));
        }

        //设置每行每列的值
        XSSFCellStyle cellStyle = getStyle(wb, style);
        if (dataList != null && !dataList.isEmpty()) {
            for (int m = 1, length = dataList.size(); m <= length; m++) {
                // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
                // 创建一行，在页sheet上
                XSSFRow row1 = sheet.createRow(m);
                // 在row行上创建一个方格
                Object o = dataList.get(m - 1);
                if (o == null) {
                    continue;
                }
                String value;
                List<Object> bankList;
                for (int n = 0; n < len; n++) {
                    value = "";
                    XSSFCell cell = row1.createCell(n, HSSFCell.CELL_TYPE_STRING);
                    if (columns[n].indexOf("List") != -1) {
                        String[] split = columns[n].split(":");
                        if (getValue(o, split[0]) != null) {
                            bankList = (List) getValue(o, split[0]);
                            for (Object listO : bankList) {
                                value += getValue(listO, split[1]) + "\n";
                            }
                        }
                    } else {
                        if (getValue(o, columns[n]) != null) {
                            value = getValue(o, columns[n]).toString();
                        }
                    }
                    cell.setCellValue(value);
                    cell.setCellStyle(cellStyle);
                }
            }
        }
        //自动调整宽度，设置wrap
        autoAdapt(sheet, len, style);
    }

    /**
     * 获取对应的值
     *
     * @param o
     * @param fieldName
     * @return
     */
    private static Object getValue(Object o, String fieldName) throws Exception {
        Class clazz = o.getClass();
        return getField(o, clazz, fieldName);
    }

    /**
     * 递归获得属性值，没有返回null
     *
     * @param o
     * @param clazz
     * @param fieldName
     * @return
     * @throws Exception
     */
    private static Object getField(Object o, Class clazz, String fieldName) throws Exception {
        Object object;
        Field field;
        Boolean flag = false;
        Field[] fields = clazz.getDeclaredFields();
        for (Field fieldEach : fields) {
            if (fieldEach.getName().equals(fieldName)) {
                flag = true;
                break;
            }
        }

        if (flag) {
            field = clazz.getDeclaredField(fieldName);
        } else {
            Class cc = clazz.getSuperclass();
            if (cc == null) {
                return null;
            }
            return getField(o, cc, fieldName);
        }

        field.setAccessible(true);
        if (field.get(o) instanceof Date) {
            object = DateUtil.dateToString((Date) field.get(o), DateConstants.YYYY_MM_DD_HH_MM_SS);
        } else {
            object = field.get(o);
        }

        return object;
    }

    /*
     * 列数据信息单元格样式
     */
    public static XSSFCellStyle getStyle(XSSFWorkbook workbook, XSSFCellStyle style) {
        // 设置字体
        XSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    private static void autoAdapt(XSSFSheet sheet, int len, XSSFCellStyle style) {
        //让列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < len; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    XSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        //长度大于wrapCharSize个字符的利用wrapText处理
                        if (length < wrapCharSize) {
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        } else {
                            columnWidth = wrapCharSize;
                            //设置内容wrap
                            currentCell.setCellStyle(getWrapStyle(style));
                        }
                    }
                }
            }
            if (colNum == 0) {
                sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
            } else {
                sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
            }
        }
    }

    /*
     * wrap单元格样式
     */
    public static XSSFCellStyle getWrapStyle(XSSFCellStyle style) {
        style.setWrapText(true);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        return style;
    }

    /*
     * 列头单元格样式
     */
    public static XSSFCellStyle getColumnTopStyle(XSSFWorkbook workbook, XSSFCellStyle style) {

        // 设置字体
        XSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}

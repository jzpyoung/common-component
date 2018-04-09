package org.jzp.code.common.component.vo;

import java.util.List;
import java.util.Map;

/**
 * created by jiazhipeng on 2018/4/9
 */
public class ExcelSheetVO<T> {

    // sheet页名称
    private String sheetName;

    // 表头
    private Map<String, String> headKeys;

    // 数据
    private List<T> datas;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Map<String, String> getHeadKeys() {
        return headKeys;
    }

    public void setHeadKeys(Map<String, String> headKeys) {
        this.headKeys = headKeys;
    }
}

package org.jzp.code.common.component.vo;

import java.util.List;

/**
 * created by jiazhipeng on 2018/3/27
 */
public class AddressBookVO {

    // 字母值
    private String letter;
    // 对应的学生
    private List<SortVO> addressBookStudentList;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public List<SortVO> getAddressBookStudentList() {
        return addressBookStudentList;
    }

    public void setAddressBookStudentList(List<SortVO> addressBookStudentList) {
        this.addressBookStudentList = addressBookStudentList;
    }
}

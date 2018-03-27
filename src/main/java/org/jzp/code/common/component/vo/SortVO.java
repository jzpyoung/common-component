package org.jzp.code.common.component.vo;

/**
 * created by jiazhipeng on 2018/3/27
 */
public class SortVO {

    // 学生Id
    private Long studentId;
    // 学生姓名
    private String stuName;
    // 头像
    private String avatar;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

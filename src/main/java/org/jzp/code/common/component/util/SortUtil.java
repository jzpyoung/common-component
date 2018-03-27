package org.jzp.code.common.component.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import org.apache.commons.lang3.StringUtils;
import org.jzp.code.common.component.vo.AddressBookVO;
import org.jzp.code.common.component.vo.SortVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 排序工具类
 * created by jiazhipeng on 2018/3/27
 */
public class SortUtil {

    private static final Logger LOG = LoggerFactory.getLogger(SortUtil.class);

    /**
     * 通讯录排序
     */
    private List<AddressBookVO> addressBookSort(List<SortVO> sortVOS) {
        //name首字母分组排序返回，先分组后排序，最优解
        if (CollectionUtils.isEmpty(sortVOS)) {
            return null;
        }

        // 按照首字母分组借助treemap，无stuName || stuName首字母不是英文字母 放到第一组key是#
        Map<String, List<SortVO>> map = Maps.newTreeMap();
        List<SortVO> dtolist;
        for (SortVO dto : sortVOS) {
            if (StringUtils.isEmpty(dto.getStuName())) {
                setSpecialGroupAddress(map, dto);
            } else {
                try {
                    String firstLetter = PinyinUtil.transFirstPinyin(dto.getStuName(), HanyuPinyinCaseType.UPPERCASE).substring(0, 1);

                    if (!firstLetter.matches("[a-zA-Z]")) {
                        setSpecialGroupAddress(map, dto);
                    } else {
                        if (map.get(firstLetter) == null) {
                            dtolist = Lists.newArrayList();
                            dtolist.add(dto);
                            map.put(firstLetter, dtolist);
                        } else {
                            map.get(firstLetter).add(dto);
                        }
                    }
                } catch (Exception e) {
                    LOG.error("address通讯录汉语转拼音出错,把该用户放至特殊组#,出错人姓名:{}" + dto.getStuName());
                    setSpecialGroupAddress(map, dto);
                }
            }
        }

        // 首字母排序每个分组的用户信息
        for (Map.Entry<String, List<SortVO>> m : map.entrySet()) {
            Collections.sort(m.getValue(), new Comparator<SortVO>() {
                public int compare(SortVO o1, SortVO o2) {
                    String studentName1 = o1.getStuName();
                    String studentName2 = o2.getStuName();

                    if (StringUtils.isEmpty(studentName1) && StringUtils.isEmpty(studentName2)) {
                        return o1.getStudentId() >= o2.getStudentId() ? 1 : -1;
                    }

                    if (StringUtils.isEmpty(studentName1)) {
                        return -1;
                    }

                    if (StringUtils.isEmpty(studentName2)) {
                        return 1;
                    }

                    Collator collator = Collator.getInstance(java.util.Locale.CHINA);
                    CollationKey key1 = collator.getCollationKey(studentName1);
                    CollationKey key2 = collator.getCollationKey(studentName2);
                    return key1.compareTo(key2);
                }
            });
        }

        // treemap to list
        List<AddressBookVO> result = Lists.newArrayList();
        AddressBookVO lpAddressBookDto;
        for (Map.Entry<String, List<SortVO>> m : map.entrySet()) {
            lpAddressBookDto = new AddressBookVO();
            lpAddressBookDto.setLetter(m.getKey());
            lpAddressBookDto.setAddressBookStudentList(m.getValue());
            result.add(lpAddressBookDto);
        }
        return result;
    }

    /**
     * 设置通讯录特殊的一组,组名用#,位置置顶,stuName == null || firstName isnot en
     *
     * @param map
     * @param dto
     */
    private void setSpecialGroupAddress(Map<String, List<SortVO>> map, SortVO dto) {
        List<SortVO> dtolist;
        if (map.get("#") == null) {
            dtolist = Lists.newArrayList();
            dtolist.add(dto);
            map.put("#", dtolist);
        } else {
            map.get("#").add(dto);
        }
    }
}

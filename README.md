# common-component
+ 添加 common-component 依赖：

    ```xml
    <dependency>
        <groupId>org.jzp.code</groupId>
        <artifactId>common-component</artifactId>
        <version>1.0.1</version>
    </dependency>
    ```
    
+ 工具类介绍:

    + 加解密：
        + [AES](src/main/java/org/jzp/code/common/component/util/AESUtil.java): AES工具；
        + [RSA](src/main/java/org/jzp/code/common/component/util/RSAUtil.java): RSA工具；
        + [MD5](src/main/java/org/jzp/code/common/component/util/MD5Util.java): MD5工具；
        + [Base64](src/main/java/org/jzp/code/common/component/util/Base64Util.java): Base64工具；
    + 序列化：
        + [Gson](src/main/java/org/jzp/code/common/component/util/GsonUtil.java): Gson工具；
    + 效率类：
        + [Date](src/main/java/org/jzp/code/common/component/util/DateUtil.java): 日期工具；
        + [Digit](src/main/java/org/jzp/code/common/component/util/DigitUtil.java): 运算工具；
        + [Http](src/main/java/org/jzp/code/common/component/util/HttpUtil.java): Http工具；
        + [Sort](src/main/java/org/jzp/code/common/component/util/SortUtil.java): 排序工具；
        + [Email](src/main/java/org/jzp/code/common/component/util/EmailUtil.java): 邮件工具；
        + [Pinyin](src/main/java/org/jzp/code/common/component/util/PinyinUtil.java): 拼音工具；
        + [Excel](src/main/java/org/jzp/code/common/component/util/ExcelUtil.java): Excel工具；
        + [Regex](src/main/java/org/jzp/code/common/component/util/RegexUtil.java): 正则工具；
        + [Reflect](src/main/java/org/jzp/code/common/component/util/ReflectUtil.java): 反射工具；
        + [String](src/main/java/org/jzp/code/common/component/util/StringUtil.java): 字符串工具；
        + [Check](src/main/java/org/jzp/code/common/component/util/CheckUtil.java): 参数校验工具；
        + [Result](src/main/java/org/jzp/code/common/component/util/ResultUtil.java): 返回结果处理工具；
    
+ 历史版本:

	+ 1.0.0:
		
		+ 基本功能实现。
		
	+ 1.0.1:
        		
        + 增加excel、邮件、拼音、排序工具支持。


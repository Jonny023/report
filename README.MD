# Grails3 Report Demo

* ireport报表


> 直接不用再网页上显示，调用本地打印机打印，只需添加这句：


```
//直接调用本地打印机打印
JasperPrintManager.printReport(jasperPrint, false);
```

### 常见问题
* 中文不显示
    * 设计器勾选pdf预览`isPdfEmbedded`
    * 字体 `STSong-Light` 、`UniGB-UCS2-H`
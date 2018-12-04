package com.lee.report

import net.sf.jasperreports.engine.JRDataSource
import net.sf.jasperreports.engine.JREmptyDataSource
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperRunManager
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource

import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *  简单表表服务
 */
class BaseReportService {

    /**
     *  生成简单报表
     * @param request
     * @param response
     * @param templateName jrxml模板名称
     * @param lists 模板所需数据
     * @return
     */
    def baseReport(HttpServletRequest request, HttpServletResponse response,String templateName,List<?> lists) {

        InputStream ins = null
        ServletOutputStream out = null

        try {

            // 报表数据源
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(lists)

            //获取模板文件存放路径
            String path = request.getSession().getServletContext().getRealPath("templates")
            String tempPath = path +File.separator+ "jrxml" + File.separator + templateName

            //编译后的.jasper文件存放路径
            String jrxmlDestSourcePath = path+File.separator+"jasper"+File.separator+templateName.substring(0,templateName.indexOf("."))+".jasper"
            JasperCompileManager.compileReportToFile(tempPath,jrxmlDestSourcePath)
            ins = new FileInputStream(new File(jrxmlDestSourcePath))
            out = response.getOutputStream()
            response.setContentType("application/pdf")
//            JasperRunManager.runReportToPdfStream(ins,out,new HashMap(),new JREmptyDataSource())
            JasperRunManager.runReportToPdfStream(ins,out,new HashMap(),jrDataSource)
            println jrDataSource.data
            response.getOutputStream().flush()
        } catch (e) {
            e.printStackTrace()
        }finally{
            try {
                if(ins!=null){ins.close()}
                if(out!=null){out.close()}
            } catch (e) {
                e.printStackTrace()
            }
        }
    }
}
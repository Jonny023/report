package com.lee

import net.sf.jasperreports.engine.JRDataSource
import net.sf.jasperreports.engine.JREmptyDataSource
import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JasperCompileManager
import net.sf.jasperreports.engine.JasperRunManager
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import report.User

import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletRequest
import org.springframework.ui.Model

import javax.servlet.http.HttpServletResponse


class ReportService {

    def report(HttpServletRequest request) {

        Model model

        //获取模板文件存放路径
        String path = request.getSession().getServletContext().getRealPath("template")

        String tempPath = path + "/user.jasper"

        //封装userList
        List<User> userList = User.findAll()

        // 报表数据源
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(userList)

        // 动态指定报表模板url
        model.addAttribute("url", tempPath)
        model.addAttribute("format", "pdf") // 报表格式
        model.addAttribute("jrMainDataSource", jrDataSource);  model.addAllAttributes()
        return model
    }

    def export(HttpServletRequest request,HttpServletResponse response) {

        InputStream ins = null
        ServletOutputStream out = null

        try {

            //封装userList
            List<?> userList = User.findAll()

            // 报表数据源
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(userList)

            //获取模板文件存放路径
            String path = request.getSession().getServletContext().getRealPath("template")
            String tempPath = path + "\\user.jrxml"
            String jrxmlDestSourcePath = path+"\\user.jasper"
            JasperCompileManager.compileReportToFile(tempPath,jrxmlDestSourcePath)
            ins = new FileInputStream(new File(jrxmlDestSourcePath))
            out = response.getOutputStream()
            response.setContentType("application/pdf")
//            JasperRunManager.runReportToPdfStream(ins,out,new HashMap(),new JREmptyDataSource())
            JasperRunManager.runReportToPdfStream(ins,out,new HashMap(),jrDataSource)
        } catch (JRException e) {   // TODO Auto-generated catch block
            e.printStackTrace()
        }finally{
            if(ins!=null){ins.close()}
            if(out!=null){out.close()}
        }
    }
}

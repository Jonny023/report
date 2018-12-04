package com.lee

/**
 * @auther Lee
 * @Date 2017/12/12 16:24
 * return 
 *
 */
class ReportExportMode {
    
    public static String EXP_PDF_MODE="PDF"
    public static String EXP_EXCEL_MODE="EXCEL"
    public static boolean isPDF(String mode){
        return EXP_PDF_MODE.equals(mode)
    }
    public static boolean isEXCEL(String mode){
        return EXP_EXCEL_MODE.equals(mode)
    }

}

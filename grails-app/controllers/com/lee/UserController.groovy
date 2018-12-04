package com.lee

class UserController {

    def reportService

    def index() {
        render "index page."
    }

    def export() {
//        reportService.report(request)
        reportService.export(request,response)
//        render view: "export"
    }
}

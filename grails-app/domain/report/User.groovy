package report

class User {

    String name
    String sex
    Integer age

    static constraints = {
        name nullable: false
        sex nullable: false
        age nullable: false
    }

    static mapping = {
        version false
    }

}

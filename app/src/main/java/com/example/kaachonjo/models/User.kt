package com.example.kaachonjo.models

class User {
    var name:String = ""
    var email:String = ""
    var password:String = ""
    var userId:String = ""

    constructor(name: String, email: String, password: String, userId: String) {
        this.name = name
        this.email = email
        this.password = password
        this.userId = userId
    }
    constructor()
}
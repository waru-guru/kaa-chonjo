package com.example.kaachonjo.models

class UserInput {
    var description:String = ""
    var imageUrl:String = ""
    var userId:String = ""

    constructor(description: String, imageUrl: String, userId: String) {
        this.description = description
        this.imageUrl = imageUrl
        this.userId = userId
    }
    constructor()
}
package com.example.kaachonjo.models

class Criminal {
    var name:String = ""
    var description:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(name: String, description: String, image: String, id: String) {
        this.name = name
        this.description = description
        this.imageUrl = image
        this.id = id
    }
    constructor()
}
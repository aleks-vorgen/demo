let body = document.querySelector("body")
let container = document.querySelector(".container")
let navbar = document.querySelector(".navbar");
let footer = document.querySelector(".footer")

container.offsetHeight = (body.offsetHeight - navbar.offsetHeight - footer.offsetHeight).toString();
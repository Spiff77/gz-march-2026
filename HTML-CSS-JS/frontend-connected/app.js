function changeTitle(){
    let h1Title = document.getElementById("top")
    let lis = document.querySelectorAll('li')

    console.log(lis)

    const person = {
        firstname: "Thomas",
        age: 20
    }
    
    person.age = 30;

    console.log(person)

    for (let index = 0; index < lis.length; index++) {
        const element = lis[index];
        element.innerHTML = "Hello"
    }

    let inputName = document.querySelector("#username");
    let newName = inputName.value

    h1Title.innerHTML = newName

    inputName.value = ''
}

async function loadData(){

    let data = await fetch('http:localhost:8080/albums')
    let users = await data.json()

    let tbody = document.querySelector('tbody')

    let username = "Tom"

    console.log("Hello my name is " + username)
    console.log('Hello my name is ' + username)
    console.log(`Hello my name is ${username}`)
    
    for (let index = 0; index < users.length; index++) {
        const element = users[index];
        tbody.innerHTML += "<tr>"
        tbody.innerHTML += `<td>${element.id}</td><td>${element.title}</td><td>${element.numberOfTracks}</td>`
        tbody.innerHTML += "</tr>"
    }
    

}

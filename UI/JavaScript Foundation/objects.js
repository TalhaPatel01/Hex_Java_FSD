const user = {
    id: 12,
    name: "Talha",
    skills: ["Spring Boot","Java"]
}

//destructure
const {id,name,skills} = user

//display
console.log(`Username = ${name}`);

//display array
skills.forEach(s=> console.log(s));
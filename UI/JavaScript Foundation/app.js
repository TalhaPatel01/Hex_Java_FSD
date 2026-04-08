const user = {
    id: 1,
    name: "Talha",
    role: "Developer"
}

//old way
console.log(user.name);

//new way - destructuring the object
const {id,name,role} = user;
console.log(`User name is ${name}`);
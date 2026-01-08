// Shared script for login, register, and todos pages
const SERVER_URL = "http://localhost:8080";
const token = localStorage.getItem("token");

// Login page logic
function login() {
	debugger
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	
	fetch(`${SERVER_URL}/auth/login`,
	{method:"POST",
	headers:{"Content-Type":"application/json"},
	body:JSON.stringify({email,password})
	})
	.then(response =>{
		if(!response.ok){
			throw new Error(data.message||"registration failed");
		}
		return response.json();
		
	})
	.then(data =>{
		localStorage.setItem("token",data.token);
		window.location.href = "/index";

	})
	.catch(error=>{alert(error.message)});
	

}

// Register page logic
function register() {
const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;
	
	fetch(`${SERVER_URL}/auth/register`,
	{method:"POST",
	headers:{"Content-Type":"application/json"},
	body:JSON.stringify({email,password})
	})
	.then(response =>{
		if(response.ok){
			alert("registration is succesful");
			window.location.href = "login.html";
		}
		else{
			return response.json().then(data =>{throw new Error(data.message||"registration failed")})
		}
	}).catch(error=>{alert(error.message)});
}

// Todos page logic
function createTodoCard(todo) {

}

function loadTodos() {
	if(!token){
		alert("please login first");
		window.location.href ="login.html";
	}
	fetch(`${SERVER_URL}/product`,
	{method:"GET",
	headers:{Authorization:`Bearer ${token}`},
	})
		.then(response =>{
		if(!response.ok){
			throw new Error(data.message||"registration failed");
		}
		return response.json();
		
	}).then(data =>{
		localStorage.setItem("token",data.token);
		window.location.href="index.html"
	})
	.catch(error=>{alert(error.message)});
	

}

function addTodo() {

}

function updateTodoStatus(todo) {

}

function deleteTodo(id) {

}

// Page-specific initializations
document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementById("todo-list")) {
        loadTodos();
    }
});
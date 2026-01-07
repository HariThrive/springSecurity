// Shared script for login, register, and todos pages
const SERVER_URL = "http://localhost:8080";
const token = localStorage.getItem("token");

// Login page logic
function login() {
	
	

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
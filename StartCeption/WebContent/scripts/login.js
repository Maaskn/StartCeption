
document.getElementById("passLog").addEventListener("mouseover",verifyPassword);
document.getElementById("passLog").addEventListener("mouseout",verifyPassword);
document.getElementById("passLog").addEventListener("click",verifyPassword);
	
function verifyPassword() {
	var pass = document.getElementById("passLog").value;
	var passArray = pass.split('');
	var acceptable = true;
	var c;
	for (var i = 0; i < passArray.length; i++) {
		c = passArray[i];
		if (!c.match(/^[A-Za-z0-9]+$/)) {
			acceptable = false;
			break;
		}
	}

	generatePasswordMessage(acceptable, passArray, c);
}



function generatePasswordMessage(acceptable, passArray, c) {
	var errorElement = document.getElementById("errorMsg");
	errorElement.style.fontWeight = "bold";
	var errorMessage;
	var button = document.getElementById("logButton");

	if (!acceptable) {
		errorElement.style.backgroundColor = "red";
		errorMessage = "This character \(" + c + "\) is not valid, try again! ";
		errorElement.innerHTML = "Invalid! " + errorMessage;
		button.disabled = true;
	}else{
		errorElement.innerHTML = "";
		button.disabled = false;
	}
		
	
	
}
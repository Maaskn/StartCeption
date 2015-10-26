/**
 * this method verifies if the password input is allowed in the logging
 * @author Erik Perez
 * */	
function verifyPassword() {
	var pass = document.getElementById("passReg").value;
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


/**
 * this method generates a message depending if it is allowed or not
 * @param acceptable true if it is allowed, false otherwise
 * @param passArray the password converted to a char array
 * @param c the wrong character to be directed too if there is one
 * @author Erik Perez
 * */	
function generatePasswordMessage(acceptable, passArray, c) {
	var errorElement = document.getElementById("errorMsg");
	errorElement.style.fontWeight = "bold";

	var button = document.getElementById("regButton");

	if (acceptable && passArray.length > 6) {
		errorElement.innerHTML = "This works perfectly!";
		errorElement.style.backgroundColor = "#66FF33";
		errorElement.style.fontStyle = "italic";
		button.disabled = false;

	} else {
		var errorMessage;
		if (passArray.length <= 6) {
			errorElement.style.backgroundColor = "#FFCC00";
			errorMessage = "Write your password a little bit longer!";
		} else {
			errorElement.style.backgroundColor = "red";
			errorMessage = "We can't allow this character \(" + c
					+ "\)! Only numbers and letters please!";
		}
		errorElement.innerHTML = "Invalid! " + errorMessage;
		button.disabled = true;
	}
	
}
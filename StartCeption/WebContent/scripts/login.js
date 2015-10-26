
/**
 * this method verifies if the password input is allowed
 * @author Erik Perez
 * */	
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
	var errorMessage;
	var button = document.getElementById("logButton");
	
	if (passArray.length <= 6) {
		errorElement.innerHTML = "";
		button.disabled = true;
	}else{
		if (!acceptable) {
			errorElement.style.backgroundColor = "red";
			errorMessage = "this character \(" + c + "\) is not valid! ";
			errorElement.innerHTML = "Oh please! " + errorMessage;
			button.disabled = true;
		}else{
			errorElement.innerHTML = "";
			button.disabled = false;
		}
	}

	
		
	
	
}
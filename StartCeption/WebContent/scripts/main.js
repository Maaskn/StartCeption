
function verifyPassword() {
	var pass = document.getElementById("pass").value;
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

	var button = document.getElementById("regButton");

	if (acceptable && passArray.length > 6) {
		errorElement.innerHTML = "Acceptable";
		errorElement.style.backgroundColor = "#66FF33";
		errorElement.style.fontStyle = "italic";
		button.disabled = false;

	} else {
		var errorMessage;
		if (passArray.length < 6) {
			errorElement.style.backgroundColor = "#FFCC00";
			errorMessage = "Your password must have minimum 7 characters ";
		} else {
			errorElement.style.backgroundColor = "red";
			errorMessage = "This character \(" + c
					+ "\) is not valid, try again! ";
		}
		errorElement.innerHTML = "Invalid! " + errorMessage;
		button.disabled = true;
	}
}
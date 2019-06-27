var language = document.getElementById('form:language');
var languages = document.getElementById('languages');

language.onkeyup = function(event) { 
	console.log('changed: ' + language.value + " fertig");
	console.log("event: " + event.keyCode);
	
    const charList = 'abcdefghijklmnopqrstuvwxyz';
    const key = event.key.toLowerCase();

    if (event.keyCode === 8) 
    	; // noop: backspace
    else if (charList.indexOf(key) === -1) 
    	return;
	
	var suggestionFetch = fetch('http://localhost:8080/jsf-advanced/api/languages/suggestions?input=' + language.value);

	suggestionFetch
	  .then(function(response) { 
		  return response.json(); 
	  })
	  .then(function(names) {
		  console.log("names: " + names);
		  languages.innerHTML = '';
		  for (var i = 0; i < names.length; i++) {
			  var listItem = document.createElement('option');
			  listItem.innerHTML = names[i];
			  languages.appendChild(listItem);
		  }
		  console.log(languages);
	  });
};
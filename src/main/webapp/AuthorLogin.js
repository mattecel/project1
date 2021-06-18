// let section = document.getElementById('author');

// let newP = document.createElement('p');
// section.appendChild(newP);
// newP.setAttribute('id', 'newP');
// newP
// newP.innerHTML = "I'm a new paragraph -- inside of a section element";



// Add event listener
// document.getElementById('').onclick = getData;
let apiUrl = 'https://pokeapi.co/api/v2/pokemon/';

// Add event listener
document.getElementById('login-button').addEventListener('click', login);
let dataSection = document.getElementById('author-data');


function login() {
    console.log("button is clicked");

    let username = document.getElementById('username').value;
    let password = document.getElementById('password');

    let url = apiUrl + username;

    // AJAX

    // Step 1 Create an XMLHHttpRequestObject
    let xhttp = new XMLHttpRequest();

    // Step 2 Set a callback function for the readystatechange event
    xhttp.onreadystatechange = recieveData;

    // Step 3 Open the request
    xhttp.open('GET', url, true);

    // Step 4: Send the request
    xhttp.send();

    

    // callback function
    function recieveData(){
        /**
         * 
         * 
         * 
         * 
         * 
         * */
        // Get the element we want to manipulate

        
        dataSection.innerHTML = '';

        if (xhttp.readyState == 4) {
            if (xhttp.status == 200) {

                let r = xhttp.responseText;

                r = JSON.parse(r);
                console.log(r + "This is the results object");

                //Populate page with data
                populateData(r)


            } else {
                // Ready state is done but status code is not "Ok"
            }
        } else {
            // error handling
        }
    }
}

const populateData = (res) => {
    // name
    let nameTag = document.createElement('h3');
    nameTag.innerHTML = res.name;
    dataSection.appendChild(nameTag);
    // abilities
    let abilitiesArray = res.abilities;
    console.log(abilitiesArray);

    let abilities = document.createElement('ul');
    dataSection.innerHTML += 'Abilities<br>';
    dataSection.appendChild(abilities);

    abilitiesArray.map(a => {
        ablit = document.createElement("li")
        console.log(a);
        ablit.innerHTML = a.ability.name;
        abilities.appendChild(ablit);
    })
}
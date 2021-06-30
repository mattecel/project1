
const URL = 'http://localhost:8080/project1/controller';
// temp author object
let IamthebetterMatt = document.getElementById("weight")
let author = {};
// let author = {"authorId": 1,
// "authorUsername": "lulu1",
// "authorPassword": "123",
// "authorFirstName": "Lulu",
// "authorLastName": "Hill",
// "authorPoints": 60,
// "stories": [
//     {
//         "storyId": 3,
//         "title": "Murder in Heaven",
//         "tagline": "Who killed Santa Claus?",
//         "description": "A short story depicting a bloody end to Saint Nick",
//         "completionDate": "2021-06-07",
//         "genre": "Mystery",
//         "weight": "Novella",
//         "status": {
//             "statusId": 3,
//             "status": "pending_general",
//             "priority": false,
//             "statusDate": "2021-06-14"
//         }
//     },
//     {
//         "storyId": 4,
//         "title": "Lord of the Kings",
//         "tagline": "Who will sit on the copper throne?",
//         "description": "An epic article depicting love at first sight",
//         "completionDate": "2021-06-06",
//         "genre": "Fantasy",
//         "weight": "Short Story",
//         "status": {
//             "statusId": 4,
//             "status": "pending_senior",
//             "priority": false,
//             "statusDate": "2021-06-14"
//         }
//     },
//     {
//         "storyId": 5,
//         "title": "Gone Boy",
//         "tagline": "Where did that boy go?",
//         "description": "An article detailing the horrifying case of John Snow, The Woodland Killer.",
//         "completionDate": "2021-06-05",
//         "genre": "Horror",
//         "weight": "Short Story",
//         "status": {
//             "statusId": 5,
//             "status": "pending_senior",
//             "priority": false,
//             "statusDate": "2021-06-14"
//         }
//     }
// ]};

const getStory = () => {

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = recieveData;

    xhttp.open("GET", URL + "/author-page", true);
    xhttp.send();

     function recieveData () {

        if (xhttp.readyState == 4) {
            if (xhttp.status == 200) {
               let r = xhttp.response;
                rr = r.replace("AuthorLogin.html", "")
                author = JSON.parse(rr);
                console.log(author);
                
                populateData(author);

            } else {
                // Ready state is done but status code is not "Ok"
            }
        } else {
            // error handling
        }
    }
}
// *************************
// change this to getStory()
// *************************
window.onload = getStory();
// *************************
// *************************

function populateData (at) {
    let storySection = document.getElementById("stories");

    // Author Welcome
    let authorWelcome = document.createElement('h2');
    authorWelcome.innerHTML = "Welcome, " + at.authorFirstName + " " + at.authorLastName + "!";
    authorWelcome.setAttribute("class", "author-welcome") ;
    storySection.appendChild(authorWelcome);

    // Current points
    let authorPoints = document.createElement('h3'); // may need a diff name than author points
    authorPoints.innerHTML = "Your current points are: " + at.authorPoints;
    authorPoints.setAttribute("class", "author-points");
    storySection.appendChild(authorPoints);

    // Create a story button

    // Stories Container
    let storiesContainer = document.createElement('div');
    storiesContainer.setAttribute("class", "sto-container");
    storySection.appendChild(storiesContainer);

    at.stories.map(st => {
            //Div for holding Story Table
                let sDiv = document.createElement('div');
                sDiv.setAttribute("class", "table-wrapper")
                storiesContainer.appendChild(sDiv)
        
            // Story Table
            let sTbl = document.createElement('table');
            sTbl.setAttribute("class", "sto__table");
            sDiv.appendChild(sTbl);
        
        
            //****************************
            //Story Table Headers
            let sthead = document.createElement('thead');
            sTbl.appendChild(sthead);
        
            let strh = document.createElement('tr');
            sthead.appendChild(strh);
        
            let th1 = document.createElement('th');
            th1.innerHTML = "Title"
            strh.appendChild(th1);
        
            let th2 = document.createElement('th');
            th2.innerHTML = "Tagline"
            strh.appendChild(th2);
        
            let th3 = document.createElement('th');
            th3.innerHTML = "Description"
            strh.appendChild(th3);
        
            let th4 = document.createElement('th');
            th4.innerHTML = "Completion Date"
            strh.appendChild(th4);
        
            let th5 = document.createElement('th');
            th5.innerHTML = "Genre"
            strh.appendChild(th5);
        
            let th6 = document.createElement('th');
            th6.innerHTML = "Type"
            strh.appendChild(th6);
        
            let tbody = document.createElement('tbody');
            sTbl.appendChild(tbody);
        
            let tr = document.createElement('tr');
            sTbl.appendChild(tr);
        
            
                let std1 = document.createElement('td');
                std1.innerHTML = st.title;
                tr.appendChild(std1);
        
                let std2 = document.createElement('td');
                std2.innerHTML = st.tagline;
                tr.appendChild(std2);
        
                let std3 = document.createElement('td');
                std3.innerHTML = st.description;
                tr.appendChild(std3);
        
                let std4 = document.createElement('td');
                std4.innerHTML = st.completionDate;
                tr.appendChild(std4);
        
                let std5 = document.createElement('td');
                std5.innerHTML = st.genre;
                tr.appendChild(std5);
        
                let std6 = document.createElement('td');
                std6.innerHTML = st.weight;
                tr.appendChild(std6); 
        
        
        
                    //Div for holding Status Table
                    let xDiv = document.createElement('div');
                    xDiv.setAttribute("class", "status-wrapper")
                    storiesContainer.appendChild(xDiv)
        
            // Status Table
            let xTbl = document.createElement('table');
            xTbl.setAttribute("class", "xst__table");
            xDiv.appendChild(xTbl);
        
        
            //****************************
            //Status Table Headers
            let xthead = document.createElement('thead');
            xTbl.appendChild(xthead);
        
            let xtrh = document.createElement('tr');
            xthead.appendChild(xtrh);
        
            let xth1 = document.createElement('th');
            xth1.innerHTML = "Status"
            xtrh.appendChild(xth1);
        
            let xth3 = document.createElement('th');
            xth3.innerHTML = "Status Date"
            xtrh.appendChild(xth3);
        
            let xtbody = document.createElement('tbody');
            xTbl.appendChild(xtbody);
        
            let xtr = document.createElement('tr');
            xTbl.appendChild(xtr);
        
                let xst = st.status
        
                let xtd1 = document.createElement('td');
                xtd1.innerHTML = xst.status;
                xtr.appendChild(xtd1);
        
                let xtd3 = document.createElement('td');
                xtd3.innerHTML = xst.statusDate;
                xtr.appendChild(xtd3);
        
            //***************************
            //******Approval*************
                if (st.status.status == "pending_approval") {
                        //Div for holding Story Table
                    let aDiv = document.createElement('div');
                    aDiv.setAttribute("class", "status-wrapper")
                    storiesContainer.appendChild(aDiv)
        
        
        
                    let aTbl = document.createElement('table');
                    aTbl.setAttribute("class", "app__table");
                    aDiv.appendChild(aTbl);
        
        
                    //****************************
                    //Status Table Headers
                    let athead = document.createElement('thead');
                    aTbl.appendChild(athead);
        
                    let atrh = document.createElement('tr');
                    athead.appendChild(atrh);
        
                    let ath1 = document.createElement('th');
                    ath1.innerHTML = "Status"
                    atrh.appendChild(ath1);
        
                    let ath2 = document.createElement('th'); // dont show for author
                    ath2.innerHTML = "Number out of: 3"
                    atrh.appendChild(ath2);
        
                    let atbody = document.createElement('tbody');
                    aTbl.appendChild(atbody);
        
                    let atr = document.createElement('tr');
                    aTbl.appendChild(atr);
        
                    let ast = st.status.approval
        
                    let atd1 = document.createElement('td');
                    atd1.innerHTML = ast.approvalStatus;
                    atr.appendChild(atd1);
        
                    let atd3 = document.createElement('td');
                    atd3.innerHTML = ast.approvalNumber;
                    atr.appendChild(atd3);
                    }
    })
}

document.getElementById("sololo").addEventListener('click', () => {
    showAddStory(author)
});

function showAddStory(at) {
    console.log(at);
    let apts = at.authorPoints;
    console.log("This is how many points they have in option function" + apts);

    if(apts >= 50) {
        let novel = document.createElement('option')
        novel.innerHTML = "Novel (101+ pages)";
        novel.setAttribute("value", "Novel");
        IamthebetterMatt.appendChild(novel);
    }

    if(apts >= 25) {
        let novella = document.createElement('option')
        novella.innerHTML = "Novella (21-100 pages)";
        novella.setAttribute("value", "Novella");
        IamthebetterMatt.appendChild(novella);
    }

    if(apts >= 20) {
        let shortstory = document.createElement('option')
        shortstory.innerHTML = "Short Story (3-10 pages)";
        shortstory.setAttribute("value", "Short Story");
        IamthebetterMatt.appendChild(shortstory);
    }

    if(apts >= 10) {      
        let article = document.createElement('option')
        article.innerHTML = "Article (1-2 pages)";
        article.setAttribute("value", "Article");
        IamthebetterMatt.appendChild(article);
    }
    showEls();
}

function showEls() {
    let elems = document.querySelectorAll(".hidden")
    for (let index = 0; index < elems.length; index++) {
        elems[index].classList.remove('hidden')
    }
}


document.getElementById("as-form").addEventListener('submit',(e) => {
    addStory(e);
});
function addStory(e) {
    e.preventDefault();
    let aSto = {
            title: e.target["title"].value,
            tagline: e.target["tagline"].value,
            description: e.target["description"].value,
            completionDate: e.target["date"].value,
            genre: e.target["genre"].value,
            weight: e.target["weight"].value,
            status: {
                status: "pending_assistant",
                priority: false,
                statusDate: "2021-06-25",
            }
    }

    let pts = 0;
    if(aSto.weight === "Novel") {
        pts = 50;
    }
    
    if(aSto.weight === "Novella") {
        pts = 25;
    }
    
    if(aSto.weight === "Short Story") {
        pts = 20;
    }
    
    if(aSto.weight === "Article") {
        pts = 10;
    }

    stoJson = JSON.stringify(aSto);
    
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = postStory;
    xhttp.open("POST", URL + "/add-story", true);
    xhttp.send(stoJson);

    function postStory () {
        if (xhttp.readyState == 4) {
            if (xhttp.status == 200) {
                updateAuthor(author, pts)
                alert("Story Accepted. Your new story will show up the next time you log in!")

            } else {
                // Ready state is done but status code is not "Ok"
            }
        } else {
            // error handling
        }
    }
}

function updateAuthor (attt, pts) {
    attt["authorPoints"] = attt.authorPoints - pts;

    autJson = JSON.stringify(attt);

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = postAuthor;
    xhttp.open("POST", URL + "/update-author", true);
    xhttp.send(autJson);
    console.log("Author Sent to Back end: " + autJson);

    function postAuthor () {
        if (xhttp.readyState == 4) {
            if (xhttp.status == 200) {
            } else {
                // Ready state is done but status code is not "Ok"
            }
        } else {
            // error handling
        }
    }
}

// logout
let logoutbtn = document.getElementById("logout");
logoutbtn.addEventListener('click',() => {
    logout();
});

function logout() {
    // finish this on backend
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = logoutPlease;
    xhttp.open("GET", URL + "/logout", true);
    xhttp.send();

    function logoutPlease () {
        if (xhttp.readyState == 4) {
            if (xhttp.status == 200) {
                window.location.href = "AuthorLogin.html";
            } else {
                // Ready state is done but status code is not "Ok"
            }
        } else {
            // error handling
        }
    }
}

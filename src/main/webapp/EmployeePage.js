
const URL = 'http://localhost:8080/project1/controller';
// temp author object
// let employee = {

//     "employeeId": 1,
//     "employeeUsername": "demi1",
//     "employeePassword": "123",
//     "employeeFirstName": "Demi",
//     "employeeLastName": "Bush",
//     "employeeType": "assistant",
//     "genre1": "Mystery",
//     "genre2": "Fantasy",
//     "genre3": "Horror",

//     "story1": {
//         "storyId": 1,
//         "title": "All The Devils",
//         "tagline": "Who killed Matt Ecelbarger?",
//         "description": "A novel depicting a justified murder of a mad man",
//         "completionDate": "2021-06-07",
//         "genre": "Mystery",
//         "weight": "Novella",
//         "status": {
//             "statusId": 3,
//             "status": "pending_approval",
//             "priority": false,
//             "statusDate": "2021-06-14",
//             "approval": {
//                 "approvalId": 1,
//                 "approvalStatus": "committee",
//                 "approvalInfo": "Fix this dumb draft u fool",
//                 "approvalNumber": 2
//             }
//         }
//     },
//     "story2": {
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
//     "story3": {
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
//     }
// };

const getStory = () => {

    let xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange = recieveData;

    xhttp.open("GET", URL + "/employee-page", true);
    xhttp.send();

     function recieveData () {

        if (xhttp.readyState == 4) {
            if (xhttp.status == 200) {
               let r = xhttp.response;
                rr = r.replace("EmployeeLogin.html", "")
                employee = JSON.parse(rr);
                console.log(employee);
                populateData(employee);

            } else {
                // Ready state is done but status code is not "Ok"
            }
        } else {
            // error handling
        }
    }
}

let penBtn = document.getElementById("austat");
penBtn.addEventListener('submit', (e) => {
    console.log(e);
    updateStatus(e)
});
// *************************
// change this to getStory()
// *************************
window.onload = getStory();
// *************************
// *************************

function populateData(em) {
    let prioExists = false;
    let storySection = document.getElementById("stories");
    let eType = em.employeeType;

    let mattArray = [];
    mattArray.push(em.story1)
    if (em.story2 != null) {
        mattArray.push(em.story2)
    }
    if (em.story3 != null) {
        mattArray.push(em.story3)
    }

    mattArray.map(st => {
        if (st.status.priority) {
            prioExists = true;
        }
    })

    //Employee Genres
    let authorGenreBox = document.createElement('div');
    authorGenreBox.setAttribute("class", "genre-box")
    storySection.appendChild(authorGenreBox);

    let genArr = []
    genArr.push(em.genre1, em.genre2, em.genre3);
    genArr.map(g => {
        let geEl = document.createElement('h4');
        geEl.innerHTML = g;
        geEl.setAttribute("class", "genre");
        authorGenreBox.appendChild(geEl);
    })

    // Employee Welcome
    let authorWelcome = document.createElement('h2');
    authorWelcome.innerHTML = "Salutations valued personnel: " + em.employeeFirstName + " " + em.employeeLastName;
    authorWelcome.setAttribute("class", "author-welcome") ;
    storySection.appendChild(authorWelcome);

    // Employee Type
    let eeType = document.createElement('h3');
    eeType.innerHTML = em.employeeType + " editor"
    storySection.appendChild(eeType);

    // Stories Container
    let storiesContainer = document.createElement('div');
    storiesContainer.setAttribute("class", "sto-container");
    storySection.appendChild(storiesContainer);

    mattArray.map(st => {
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

    let xth2 = document.createElement('th'); // dont show for author
    xth2.innerHTML = "Priority"
    xtrh.appendChild(xth2);

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

        let xtd2 = document.createElement('td');
        if (xst.priority) {
            xtd2.innerHTML = "High Priority";
            xtd2.setAttribute("class", "sta-priority-high");
        } else {
            xtd2.innerHTML = "Low Priority";
            xtd2.setAttribute("class", "sta-priority=low");
        }
        xtr.appendChild(xtd2);

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

    mattArray.map(st => {
        // -----------------------------------------
        // Status Approve Button
        let sta = st.status;
        jSta = JSON.stringify(sta);


        let psta = sta.status;
        let pstaSelect = document.getElementById("ustat-sel")

        // Dynamically added priority
        if (prioExists) {
            if (sta.priority) {
                if (psta === "pending_senior") {
                    if (eType === "senior") {
                        let opt1 = document.createElement('option')
                        opt1.innerHTML = st.storyId + ": " + st.title;
                        opt1.setAttribute("value", jSta);
                        pstaSelect.appendChild(opt1);
                    }
                } else {
                    let opt1 = document.createElement('option')
                    opt1.innerHTML = st.storyId + ": " + st.title;
                    opt1.setAttribute("value", jSta);
                    pstaSelect.appendChild(opt1);
                }
            }
        } else {
            if (psta === "pending_senior") {
                if (eType === "senior") {
                    let opt1 = document.createElement('option')
                    opt1.innerHTML = st.storyId + ": " + st.title;
                    opt1.setAttribute("value", jSta);
                    pstaSelect.appendChild(opt1);
                }
            } else {
                let opt1 = document.createElement('option')
                opt1.innerHTML = st.storyId + ": " + st.title;
                opt1.setAttribute("value", jSta);
                pstaSelect.appendChild(opt1);
            }
        }
    })
}

function updateStatus(e) {
    e.preventDefault();
    let sta = JSON.parse(e.target["statusSelect"].value);
    console.log(sta);
    let xa = {};
    if (sta.approval != null) {
        xa = sta.approval
    }
    let oldSta = sta.status
    let nSta = ""
    let uSta = sta;

    if (oldSta === "pending_assistant") {
        nSta = "pending_general"
    }
    if (oldSta === "pending_general") {
        nSta = "pending_senior"
    }
    if (oldSta === "pending_senior") {
        nSta = "pending_approval"
        console.log(uSta);
        addApproval(sta);
    }
    if (oldSta === "pending_approval") {
        if (xa.approvalNumber == 1) {
            nSta = "pending_approval";
            uSta.approval["approvalNumber"] = 2;
            updateApprovalF(uSta)
        } else {
            nSta = "approved";
            updateApproval(sta);
        }
    }
    
    uSta["status"] = nSta;
    uSta["priority"] = false;
    console.log(uSta);

    jSta = JSON.stringify(uSta);

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = postStatus;
    xhttp.open("POST", URL + "/update-status", true);
    console.log(jSta);
    xhttp.send(jSta);

    function postStatus() {
        if (xhttp.readyState == 4) {
            if (xhttp.status == 200) {
                alert("Status updated. You will see the changes the next time you log in.")
            } else {
                // Ready state is done but status code is not "Ok"
            }
        } else {
            // error handling
        }
    }
}

function addApproval (sta) {
    let approval = {
        approvalStatus: "committee",
        approvalNumber: 1,
        statusId: sta.statusId
    }

    jApp = JSON.stringify(approval);

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = postApp;
    xhttp.open("POST", URL + "/add-approval", true);
    xhttp.send(jApp);

    function postApp () {
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

function updateApproval(st) {
    let sid = st.statusId;
    let author = {
        authorId: 0,
        authorPoints: 0
    }

    if (sid == 1) {
        author["authorId"] = 3;
        author["authorPoints"] = 100;
    }
    if (sid == 6) {
        author["authorId"] = 3;
        author["authorPoints"] = 100;
    }

    let oApp = st.approval;
    oApp[status] = "approved"

    jApp = JSON.stringify(oApp);

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = postApproval;
    xhttp.open("POST", URL + "/update-approval", true);
    xhttp.send(jApp);

    function postApproval() {
        if (xhttp.readyState == 4) {
            if (xhttp.status == 200) {
                updateAuthor(author)
            } else {

                // Ready state is done but status code is not "Ok"
            }
        } else {
            // error handling
        }
    }
}

function updateApprovalF(st) {
    let sid = st.statusId;

    let oApp = st.approval;

    jApp = JSON.stringify(oApp);

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = postApproval;
    xhttp.open("POST", URL + "/update-approval", true);
    xhttp.send(jApp);

    function postApproval() {
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

function updateAuthor (at) {
    let author = {
        authorId: at.authorId,
        authorPoints: at.authorPoints
    }
    console.log("you make it here");

    jAuthor = JSON.stringify(author);

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = postAuthor;
    xhttp.open("POST", URL + "/update-author", true);
    xhttp.send(jAuthor);

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



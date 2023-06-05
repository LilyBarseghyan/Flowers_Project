const myContent = document.getElementById('content');
let modalIsOpen = false;
const rootElement = document.getElementById('root');
const myInput = document.getElementById('myInput');


async function getData() {
    const result = await fetch("http://10.212.20.49:7050/flower", {method: "GET"});
    const parsedObj = await result.json();
    return parsedObj;
}

const myPromise = getData();
    myPromise.then((data1) => {
        data1.forEach(eachFlower => {
            myContent.innerHTML += flowerCard(eachFlower);
        })
        })
        .catch(myError => {
        })

async function getInputData() {
    let inputValue = myInput.value;
    const result = await fetch(`http://10.212.20.49:7050/flowerByTitle?name=${inputValue}`, {method: "GET"});
    const parsedObj = await result.json();
    return parsedObj;
}
async function getOneFlower(id) {
    const result = await fetch(`http://10.212.20.49:7050/flower/${id}`, {method: "GET"});
    const parsedObj = await result.json();  
    return parsedObj;
}

async function getCategory(type) {
    const result = await fetch(`http://10.212.20.49:7050/flowerByCategory?type=${type}`, {method: "GET"});
    const parsedObj = await result.json();  
    return parsedObj;
}

function flowerCard(flower) {
    return (
        `
            <div class="eachCard" onclick="openModal(${flower.id})">
                <img src="${flower.picture}" class="flower"><br>
                <p class="flowerName">
                ${flower.name}
                </p><br>
        </div>
        `
    )
}

function searchButton() {
    myContent.innerHTML = "";
    const myInput = document.getElementById('myInput'); 
        if (myInput.value == "") {
            alert("You must type something");
            const myPromise = getData();  
            myPromise.then((data1) => {
                data1.forEach(eachFlower => {
                    myContent.innerHTML += flowerCard(eachFlower);
                })
            })
        } else {
            const myInputPromise = getInputData(myInput.value);     
            myInputPromise.then((data1) => {
                data1.forEach(eachFlower => {
                    myContent.innerHTML += flowerCard(eachFlower);               
                })
            })
            .catch(myError => {
            })
        }
        myInput.value = "";
        
}
   

function createMyModal(singleFlower) {
    return (
        `
            <div class="fade" id="fade">
                <div class="modal" id="modal")">
                    <div class="colums">
                     <button id="closeButton">&times;</button>
                        <div>
                            <img src="${singleFlower.picture}" class="imageSize"><br>
                        </div>
                        <div>
                        <p class="flowerName">
                            ${singleFlower.name}
                        </p>
                        <p class="flowerName">
                            ${singleFlower.price}
                        </p>
                        <p class="flowerName">
                            ${singleFlower.description}
                        </p><br>
                        </div>
                    </div>
                </div>
            </div>
        `
    )
   
}  

function openModal(id) {
    const myUserPromise = getOneFlower(id);
    myUserPromise.then(myFlower => {
        rootElement.innerHTML += createMyModal(myFlower[0]);
    })
    .catch(error => {
      })
    modalIsOpen = !modalIsOpen;
    document.addEventListener("mousedown", listener);
}


function homeClick () {
    myContent.innerHTML = "";
    const myPromise = getData();
    myPromise.then((data1) => {
        data1.forEach(eachFlower => {
            myContent.innerHTML += flowerCard(eachFlower);
        })
    })
    .catch(myError => {
    })
}

  function perFlower () {
    myContent.innerHTML = "";
    const myPromise = getCategory('per');
    myPromise.then((data1) => {
        data1.forEach(eachFlower => {
            myContent.innerHTML += flowerCard(eachFlower);
        })
    })
    .catch(myError => {
    })
}

function bouquetFlower () {
    myContent.innerHTML = "";
    const myPromise = getCategory('bouquet');
    myPromise.then((data1) => {
        data1.forEach(eachFlower => {
            myContent.innerHTML += flowerCard(eachFlower);
        })
    })
    .catch(myError => {
    })
}

function bowlFlower () {
    myContent.innerHTML = "";
    const myPromise = getCategory('bowl');
    myPromise.then((data1) => {
        data1.forEach(eachFlower => {
            myContent.innerHTML += flowerCard(eachFlower);
        })
    })
    .catch(myError => {
    })
}



function listener(e) {
    if (modalIsOpen) {
        const fadeElement = document.getElementById('fade');
        const closeButton = document.getElementById('closeButton')
        if (closeButton.contains(e.target)) {
            fadeElement.remove();
            modalIsOpen = !modalIsOpen;
            document.removeEventListener("mousedown", listener)
        }
    }
}  

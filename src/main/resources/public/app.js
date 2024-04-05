const performLinearSearch = (event) => {
    event.preventDefault();
    const list = document.getElementById("linearSearchInput").value;
    const value = document.getElementById("linearSearchValue").value;
    performSearch("/linearsearch", list, value);
};

const performBinarySearch = (event) => {
    event.preventDefault();
    const list = document.getElementById("binarySearchInput").value;
    const value = document.getElementById("binarySearchValue").value;
    performSearch("/binarysearch", list, value);
};

const performSearch = (route, list, value) => {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        alert(this.responseText);
    };
    xhttp.open("GET", `${route}?list=${list}&value=${value}`);
    xhttp.send();
};


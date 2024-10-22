document.getElementById('addButton').addEventListener('click', function() {
    const newInput = document.createElement("input");
    newInput.type = "text";
    newInput.placeholder = "Search for contact";
    document.getElementById("inputDiv").appendChild(newInput);
    console.log("Add new input")
});



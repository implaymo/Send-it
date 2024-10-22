document.getElementById('addButton').addEventListener('click', function() {
    const newInput = document.createElement("input");
    newInput.type = "text";
    newInput.placeholder = "Search for contact";
    console.log("Add new input")

    const deleteButton = document.createElement('deleteButton');
    deleteButton.textContent = 'Delete';
    deleteButton.type = 'button';
    deleteButton.classList.add('btn', 'btn-danger', 'ml-2');
    document.getElementById("inputDiv").appendChild(newInput);
    document.getElementById("inputDiv").appendChild(deleteButton);

    deleteButton.addEventListener("click", function(event) {
        newInput.remove();
        deleteButton.remove();
    });

});





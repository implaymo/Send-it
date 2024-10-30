document.getElementById('addButton').addEventListener('click', function() {
    const newInput = document.createElement("input");
    newInput.type = "text";
    newInput.placeholder = "Search for contact";
    newInput.id = `contactInput-${Date.now()}`;
    newInput.name = "contactSearch";
    newInput.autocomplete = "off";
    const uniqueId = newInput.id;

    const deleteButton = document.createElement('deleteButton');
    deleteButton.textContent = 'Delete';
    deleteButton.type = 'button';
    deleteButton.classList.add('btn', 'btn-danger', 'ml-2');
    document.getElementById("inputDiv").appendChild(newInput);
    document.getElementById("inputDiv").appendChild(deleteButton);
    console.log("Add new input and delete button.");



    deleteButton.addEventListener("click", function(event) {
        newInput.remove();
        deleteButton.remove();
        console.log("Remove input and delete button.")
    });

    newInput.addEventListener("input", function() {
        const userInput = newInput.value;  // Access the input's value directly
        console.log(userInput);
    })
});

function sendInputBackend(userInput) {

    fetch('/contacts/search', {  // Adjust the URL as per your backend endpoint
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ query: input })  // Sending the input as JSON
    })
        .then(response => response.json())
        .then(data => {
            // Process and display the response data
            displayResults(data);
        })
        .catch(error => console.error('Error:', error));
}









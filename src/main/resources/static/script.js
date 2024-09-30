document.addEventListener('DOMContentLoaded', () => {
    const baseUrl = 'http://localhost:8080/api/users';
    const responseBody = document.getElementById('responseBody');
    const prettyPrintCheckbox = document.getElementById('prettyPrint');

    async function sendRequest(method, id = '', body = null) {
        const url = id ? `${baseUrl}/${id}` : baseUrl;
        try {
            const options = {
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                },
            };

            if (method === 'POST' || method === 'PUT') {
                options.body = body;
            }

            const response = await fetch(url, options);
            
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            let responseText = await response.text();
            if (responseText) {
                try {
                    const data = JSON.parse(responseText);
                    displayResponse(data);
                } catch (e) {
                    responseBody.textContent = responseText;
                }
            } else {
                responseBody.textContent = `Operation successful. Status: ${response.status}`;
            }

            if (method === 'DELETE' && response.status === 204) {
                responseBody.textContent = `User successfully deleted. Status: ${response.status}`;
            }

        } catch (error) {
            responseBody.textContent = `Error: ${error.message}`;
        }
    }

    function displayResponse(data) {
        if (prettyPrintCheckbox.checked) {
            responseBody.textContent = JSON.stringify(data, null, 2);
        } else {
            responseBody.textContent = JSON.stringify(data);
        }
    }

    prettyPrintCheckbox.addEventListener('change', () => {
        if (responseBody.textContent) {
            try {
                const data = JSON.parse(responseBody.textContent);
                displayResponse(data);
            } catch (e) {
                // If it's not valid JSON, leave it as is
            }
        }
    });

    document.getElementById('getAllButton').addEventListener('click', () => {
        sendRequest('GET');
    });

    document.getElementById('getButton').addEventListener('click', () => {
        const id = document.getElementById('getUserId').value;
        if (!id) {
            alert('Please enter a User ID for GET request');
            return;
        }
        sendRequest('GET', id);
    });

    document.getElementById('postButton').addEventListener('click', () => {
        const body = document.getElementById('postRequestBody').value;
        if (!body) {
            alert('Please enter user data for POST request');
            return;
        }
        sendRequest('POST', '', body);
    });

    document.getElementById('putButton').addEventListener('click', () => {
        const id = document.getElementById('putUserId').value;
        const body = document.getElementById('putRequestBody').value;
        if (!id || !body) {
            alert('Please enter both User ID and user data for PUT request');
            return;
        }
        sendRequest('PUT', id, body);
    });

    document.getElementById('deleteButton').addEventListener('click', () => {
        const id = document.getElementById('deleteUserId').value;
        if (!id) {
            alert('Please enter a User ID for DELETE request');
            return;
        }
        sendRequest('DELETE', id);
    });

    // Open the default tab
    document.getElementById("defaultOpen").click();
});

function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}
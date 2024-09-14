document.addEventListener('DOMContentLoaded', async function () {
    try {
        const data = await getCollege();
        const h1 = document.querySelector('h1');

        if (data && data.name) {
            h1.innerText = `Добре дошли в ${data.name}`;
        } else {
            h1.innerText = 'College data not found';
        }
    } catch (error) {
        console.error('Error fetching college data:', error);
    }
});

async function getCollege() {
    try {
        let headers = new Headers();
        headers.set('Authorization', 'Basic ' + btoa('admin:admin'));

        const response = await fetch('http://localhost:8080/college', {
            method: 'GET',
            headers: headers,
        });

        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }

        const data = await response.json();
        console.log(data);
        return data; // Parse JSON response into native JavaScript objects
    } catch (error) {
        console.error('Fetch error:', error);
    }
}

function showEditCollegeForm() {
    const editCollegeForm = document.getElementById('loginForm');
    const editCollegeBtn = document.getElementById('editCollegeBtn');
    editCollegeBtn.style.display = 'none';
    editCollegeForm.style.display = 'block';

}
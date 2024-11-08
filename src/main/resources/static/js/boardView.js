document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');
    viewPost(id);
});

function viewPost(id) {
    fetch(`http://localhost:8080/board/${id}`) // Spring Boot 서버의 포트 사용
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('view-title').textContent = data.title;
            document.getElementById('view-content').textContent = data.content;
        })
        .catch(error => console.error('Fetch error:', error));
}

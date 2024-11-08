document.addEventListener('DOMContentLoaded', () => {
    fetchPosts();
});

function fetchPosts() {
    fetch('http://localhost:8080/board/list', { cache: 'no-cache' })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Fetched data:', data); // 데이터 로깅
            const postList = document.getElementById('post-list');
            postList.innerHTML = '';
            data.forEach(post => {
                const li = document.createElement('li');
                li.textContent = post.title;
                li.onclick = () => window.location.href = `BoardView.html?id=${post.id}`;
                postList.appendChild(li);
            });
        })
        .catch(error => console.error('Fetch error:', error));
}


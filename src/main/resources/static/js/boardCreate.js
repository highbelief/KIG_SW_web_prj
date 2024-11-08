function createPost() {
    const title = document.getElementById('title').value;
    const content = document.getElementById('content').value;

    fetch('http://localhost:8080/board/create', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, content })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            alert('게시글이 작성되었습니다!');
            window.location.href = 'BoardList.html'; // 목록 페이지로 이동
        })
        .catch(error => console.error('Fetch error:', error));
}

// CSRF 토큰 가져오기
const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

// axios 기본 설정에 CSRF 헤더 추가
axios.defaults.headers.common[header] = token;
document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function () {
            console.log('Delete button clicked');
            const boardId = this.getAttribute('boardId');
            deletePost(boardId);
        });
    });

    function deletePost(boardId) {
        if (confirm('정말 삭제하겠습니까?')) {
            axios.delete(`/board/post/${boardId}`) //요청 주소 여기에 입력.
                .then(response => {
                    if (response.status === 200) {
                        alert('삭제되었습니다.');
                    }
                })
                .catch(error => {
                    console.error('error:', error);
                });
        }
    }

});


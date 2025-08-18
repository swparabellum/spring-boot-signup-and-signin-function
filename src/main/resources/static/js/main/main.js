// CSRF 토큰 가져오기
            const token = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            const header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            // axios 기본 설정에 CSRF 헤더 추가
            axios.defaults.headers.common[header] = token;

            document.addEventListener('DOMContentLoaded', function() {
                document.querySelectorAll('.delete-btn').forEach(button => {
                    button.addEventListener('click', function() {
                        console.log('Delete button clicked');
                        const email = this.getAttribute('data-email');
                        deleteUser(email);
                    });
                });

                function deleteUser(email) {
                    if(confirm('정말 삭제하시겠습니까?')) {
                        axios.delete(`/api/users/${email}`)
                            .then(response => {
                                if(response.status === 200) {
                                    alert('삭제되었습니다.');
                                    window.location.reload();
                            }
                        })
                        .catch(error => {
                            console.error('Error:', error);
                            if(error.response.status === 403){
                                alert('자기 자신은 삭제할 수 없습니다.');
                            } else {
                                alert('삭제 중 오류가 발생했습니다.');
                            }
                        });
                    }
                }
            });
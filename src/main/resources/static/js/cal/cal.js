document.addEventListener('DOMContentLoaded', function() {
    const calendarEl = document.getElementById('calendar');
    const addEventModal = new bootstrap.Modal(document.getElementById('addEventModal'));
    const eventStartDateInput = document.getElementById('eventStartDate');
    const eventEndDateInput = document.getElementById('eventEndDate'); // 종료일 input 요소 가져오기
    const eventTitleInput = document.getElementById('eventTitle');

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        // 서버에서 이벤트를 가져오는 부분
        events: function(fetchInfo, successCallback, failureCallback) {
            const start = fetchInfo.startStr.split('T')[0];
            const end = fetchInfo.endStr.split('T')[0];

            fetch(`/api/events?start=${start}&end=${end}`)
                .then(response => response.json())
                .then(data => successCallback(data))
                .catch(error => failureCallback(error));
        },
        // 1. 날짜를 클릭하면 모달의 시작일과 종료일을 모두 기본값으로 설정
        dateClick: function(info) {
            eventStartDateInput.value = info.dateStr;
            eventEndDateInput.value = info.dateStr; // 종료일도 클릭한 날짜로 설정
            addEventModal.show();
        }
    });

    calendar.render();

    // 2. 모달의 저장 버튼 클릭 시 이벤트
    document.getElementById('saveEventBtn').addEventListener('click', function() {
        const startDate = eventStartDateInput.value;
        const endDate = eventEndDateInput.value;



        // 3. 유효성 검사
        if (endDate < startDate) {
            alert('종료일은 시작일보다 빠를 수 없습니다.');
            return;
        }

        // 4. 서버로 보낼 이벤트 객체 생성 (시작일, 종료일 포함)
        const newEvent = {
            title: eventTitleInput.value,
            start: startDate,
            end: endDate
        };

        console.log(JSON.stringify(newEvent));

        // 5. 서버에 새로운 이벤트를 POST 요청으로 전송
        fetch('/api/events', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newEvent)
        })
        .then(response => {
            if (response.ok) {
                addEventModal.hide();
                document.getElementById('addEventForm').reset();
                calendar.refetchEvents(); // 캘린더의 이벤트를 다시 불러옴
            } else {
                console.log('Error saving event:', response);
                alert('일정 저장에 실패했습니다.');
            }
        });
    });
});
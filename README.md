## 기술 스택

- WAS
    - spring
- database
    - mysql
    - redis

## 서버 아키텍쳐

- 범용 api 서버
    - spring
        - 대부분의 api 요청을 처리하기 위한 웹서버
        - 전화번호 인증의 경우 외부 sms 전송 api를 사용할 예정
    - mysql
        - 당첨자 저장 및 이벤트 데이터 저장용 DB
- 선착순 api 서버
    - message queue
        - 대규모 트래픽으로 인한 요청 손실을 줄이기 위한 queue
    - spring
        - 선착순 요청 api용 웹서버
    - redis
        - 선착순 인원수 체크를 위한 인메모리 저장소
<img width="1176" alt="architecture" src="https://github.com/user-attachments/assets/7b6a596a-e948-4bbc-93d5-43498ffe48cb">

## ERD
<img width="997" alt="ERD" src="https://github.com/user-attachments/assets/ecf6b95d-6ddd-4369-912e-9286341e17e7">
## 발생 가능한 이슈

- 대규모 트래픽으로 인해 redis가 ec2 메모리를 넘어가는 경우

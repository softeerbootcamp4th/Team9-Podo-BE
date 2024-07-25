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

![Untitled](%E1%84%87%E1%85%A1%E1%86%AF%E1%84%91%E1%85%AD%E1%84%8C%E1%85%A1%E1%84%85%E1%85%AD%20cbb4733670294caa961f6cbe99dd249a/Untitled.png)

## ERD

![Untitled](%E1%84%87%E1%85%A1%E1%86%AF%E1%84%91%E1%85%AD%E1%84%8C%E1%85%A1%E1%84%85%E1%85%AD%20cbb4733670294caa961f6cbe99dd249a/Untitled%201.png)

## 발생 가능한 이슈

- 대규모 트래픽으로 인해 redis가 ec2 메모리를 넘어가는 경우

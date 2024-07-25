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

## 그라운드 룰

## 공통

- 모든 컨벤션은 팀원간 합의를 통해서 변경할 수 있다.

### 코드 컨벤션

- 클래스 : **UpperCamelCase,** and·or와 같은 접속사를 사용하지 않고 25자 내외로 작성한다.
- 함수 : **lowerCamelCase**
- 변수, 상수 : **lowerCamelCase**
- DB 테이블: **lower_snake_case**를 사용하며, tb_name과 같이 접두사를 붙이지 않으며, 복수형으로 지정한다.
- ENUM, 상수: **Upper_snake_case**
- 컬렉션(Collection): **복수형**을 사용하거나 **컬렉션을 명시합니다**. (Ex. userList, users, userMap)
- LocalDateTime: 접미사에 **Date**를 붙입니다.

### 커밋 컨벤션

**`태그: 제목`의 형태이며, `:`뒤에 space를 추가한다.**

**태그는 다음과 같다.**

- `feat` : 기능 추가
- `fix` : 기존 코드 수정, 버그 수정 등
- `docs` : 문서 추가, 수정
- `style` : 코드 포맷팅, 오탈자 수정, 코드 로직상의 변경이 없는 경우
- `refactor` : 코드 리팩토링
- `test` : 테스트 코드 관련 작업
- `chore` : 빌드 업무 등 기타 작업

### 브랜치 전략
![Untitled](https://github.com/user-attachments/assets/4be0cbab-c7e5-4710-b4db-a41a2ce49b4e)


→ 기본적으로는 **Github Flow** 전략을 사용한다. 

→ PR시 Issue 번호를 명시하고, 코드리뷰 진행후 머지한다.

### 브랜치 명명규칙

→ 브랜치는 [branch]/[detail]과 같이 브랜치명을 앞에 명시하고, 뒤에 작업 내용을 적는다. [ex) feat/chat]

### 작업 순서

→ 이슈를 작성한 후, Assignee를 지정하고 Github Project 백로그에 저장한다.

→ 스프린트 계획에 따라 계획된 팀원이 정해진 명명규칙에 따라서 브랜치를 만들고, 작업에 들어간다.

→ 작업이 완료되었다면 이슈 번호를 명시하고 Pull Request를 작성한다.

→ 팀원의 코드리뷰 후 문제가 없다면 머지하고, 논의할 사항이 있다면 별도의 시간을 할당하여 합의한다.


## 팀원 소개

<div align="center">
  
| 김영빈 <br/> [@eckrin](https://github.com/eckrin) | 강승구 <br/> [@luna156](https://github.com/luna156) |
|:--:|:--:|
| <img src="https://avatars.githubusercontent.com/eckrin"  width=200> | <img src="https://avatars.githubusercontent.com/luna156"  width=200> |

</div>

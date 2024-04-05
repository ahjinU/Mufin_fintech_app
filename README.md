# 머핀 (mufin)

> 삼성 청년 소프트웨어 아카데미(SSAFY) 10기 2학기 특화 프로젝트 (A502)

![Project Cover](./assets/cover.jpg)

## ✨ 프로젝트 개요

🏷 **프로젝트 이름 : 머핀(mufin)**

🗓️ **프로젝트 기간 : 2023.02.26 ~ 2024.04.04 (6주)**

👥 **구성원 : 👑윤진아, 윤서안, 장수민, 김라연, 김한슬, 전은평**

---

## 🕶 서비스 구경하기

### [배포 사이트](https://mufin.life)

---

## 🌃 기획 의도

> **'혹시 내가 금융문맹?'**
>
> '금융문맹'이란 문자를 읽고 쓰지 못하는 것을 문맹이라고 하듯이 재화의 소중함과 관리 방식을 몰라 생활에 불편을 주고 삶의 질을 저하시키는 것을 지칭합니다.

대한민국 학생들의 금융이해도 수준이 40점 안팎으로, 국가별 수준의 최하위에 위치하고 있습니다.

이 문제를 해결하기 위해 어린이들을 위한 경제 교육 핀테크 서비스를 개발하였습니다.

---

## 💎 서비스 소개

- 부모님이 계좌를 개설한 후, 아이의 회원가입과 **계좌 개설**을 할 수 있습니다.
- 부모님과 아이 계정은 서로 **연동**되어 아이들의 **소비 내역을 파악**할 수 있습니다.
- 아이들은 **용돈기입장 기능**을 통해, 소비와 수입을 정리할 수 있습니다.
- **OCR 기능**을 통해 소비한 **영수증을 스캔**하면 소비 내역의 **자세한 내역을 자동으로 등록**할 수 있습니다.
- **부모님**이 **적금 상품**을 **직접 등록**할 수 있습니다.
- 아이들은 부모님이 만든 적금 상품 중 **원하는 상품을 선택**해서 가입할 수 있습니다.
- 아이들이 돈이 필요할 때는, **부모님께 대출 신청**을 할 수 있습니다.
- 대출 신청서에는 **대출의 목적, 금액, 기간, 불입일**의 정보와 **챗봇과의 대화**를 통해, **대출이 정말 필요한지 스스로 판단**할 기회를 제공합니다.
- 대출을 신청하게 되면, 부모님은 **대출 신청서를 심사**합니다.
- 대출 심사 후 **승인** 또는 **거절**할 수 있습니다.
- **날씨와 주가와 연동**시켜 **모의 주식 서비스**를 제공합니다.
- 아이들은 주식 차트와 날씨 정보를 기반으로 **주식을 매도**하고, **매수**할 수 있습니다.
- 주식에 투자하지 않는 돈은 **파킹통장에 저축**되어, **안정적**으로 **투자**할 수 있습니다.
- 투자 수익률로 전체 유저 중 **내 순위**와 **전체 랭킹**을 제공하고 있습니다.

---

## 🎯 서비스 대상

- 조기 금융 교육이 필요한 초,중,고등학생과 부모님

---

## 👨‍👩‍👧‍👦 구성원

| [![윤진아](https://github.com/ahjinU.png)](https://github.com/ahjinU) | [![윤서안](https://github.com/annyoon.png)](https://github.com/annyoon) | [![장수민](https://github.com/jangsumin.png)](https://github.com/jangsumin) | [![김라연](https://github.com/fkdusrh.png)](https://github.com/fkdusrh) | [![김한슬](https://github.com/slcloe.png)](https://github.com/slcloe) | [![전은평](https://github.com/pyeong114.png)](https://github.com/pyeong114) |
| :-------------------------------------------------------------------: | :---------------------------------------------------------------------: | :-------------------------------------------------------------------------: | :---------------------------------------------------------------------: | :-------------------------------------------------------------------: | :-------------------------------------------------------------------------: |
|                 [👑윤진아](https://github.com/ahjinU)                 |                  [윤서안](https://github.com/annyoon)                   |                   [장수민](https://github.com/jangsumin)                    |                  [김라연](https://github.com/fkdusrh)                   |                  [김한슬](https://github.com/slcloe)                  |                   [전은평](https://github.com/pyeong114)                    |

---

## 👩‍💻 역할

### 윤진아

- **팀장**
- **frontend**
  - 주식 페이지
  - 대출 신청 페이지
  - 대출 납부 페이지
  - 가계부 페이지

### 윤서안

- **Frontend**
  - 회원가입 페이지
  - 로그인 페이지
  - 송금 페이지
  - 대출심사 페이지

### 장수민

- **Frontend**
  - 결제 보안 키패드 페이지
  - 실시간 주식 페이지
  - 적금 신청 페이지
  - 적금 납부 페이지

### 김라연

- **Backend**
  - JWT 설정
  - 회원가입 및 로그인 API
  - 파킹 통장 생성 API
  - 가계부 API

### 김한슬

- **인프라**
- **Backend**
  - 결제 비밀번호 입력시, 보안 키패드 처리
  - 주식 거래 동시성 제어
  - 계좌 생성 API
  - 주식 거래 API
  - Redis를 통한 주식 수익률 순위 관리

### 전은평

- **Backend**
  - 주식 실시간 거래를 위한 WebSocket 연결
  - 계좌 거래 동시성 제어
  - 미체결 주식 조회 API
  - 계좌, 파킹통장 API
  - 적금 API
  - 대출 API

---

## 🌈 서비스 화면 소개

| ![Sign up](./assets/screen/signup.gif) | ![Sign in](./assets/screen/signin.gif) | ![Withdrawal](./assets/screen/withdraw.gif) |
| :------------------------------------: | :------------------------------------: | :-----------------------------------------: |
|                회원가입                |                 로그인                 |                    송금                     |

| ![Allowance Parents](./assets/screen/allowance_parent_child.gif) | ![Allowance child](./assets/screen/allowance_receipt_memo.gif) | ![Savings register](./assets/screen/savings_register.gif) |
| :--------------------------------------------------------------: | :------------------------------------------------------------: | :-------------------------------------------------------: |
|                        가계부(부모님화면)                        |                        가계부(아이화면)                        |                         적금 등록                         |

| ![Savings Apply](./assets/screen/savings_apply.gif) | ![Savings Deposit](./assets/screen/savings_deposit.gif) | ![Loan Apply](./assets/screen/loan_apply.gif) |
| :-------------------------------------------------: | :-----------------------------------------------------: | :-------------------------------------------: |
|                      적금 가입                      |                        적금 불입                        |                   대출 신청                   |

| ![Loan Test](./assets/screen/loan_test.gif) | ![Loan Check](./assets/screen/loan_check.gif) | ![Stock Chart](./assets/screen/stock_chart.gif) |
| :-----------------------------------------: | :-------------------------------------------: | :---------------------------------------------: |
|                  대출 심사                  |                   대출 내역                   |                    주식 차트                    |

| ![Stock Rank](./assets/screen/stock_rank.gif) | ![Stock Buy](./assets/screen/stock_rank.gif) | ![Stock Sell](./assets/screen/stock_sell.gif) |
| :-------------------------------------------: | :------------------------------------------: | :-------------------------------------------: |
|                   주식 랭킹                   |                주식 매수 주문                |                주식 매도 주문                 |

---

## 🛠 기술 스택

### Frontend

<p>
	<img src="https://img.shields.io/badge/Next.js-black?style=flat-square&logo=Next.js&logoColor=white">
  <img src="https://img.shields.io/badge/NextAuth.js-black?style=flat-square&logo=Next.js&logoColor=white">
	<img src="https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=React&logoColor=white">
  <img src="https://img.shields.io/badge/Typescript-3178C6?style=flat-square&logo=typescript&logoColor=white">
  <img src="https://img.shields.io/badge/TailwindCSS-06B6D4?style=flat-square&logo=TailwindCSS&logoColor=white"/>
  <img src="https://img.shields.io/badge/node.js-339933?style=flat-square&logo=nodedotjs&logoColor=white">
  <img src="https://img.shields.io/badge/PWA-5A0FC8?style=flat-square&logo=pwa&logoColor=white">
  <img src="https://img.shields.io/badge/yarn-2C8EBB?style=flat-square&logo=yarn&logoColor=white">
  <img src="https://img.shields.io/badge/express.js-black?style=flat-square&logo=express&logoColor=white">
   <img src="https://img.shields.io/badge/StoryBook-FF4785?style=flat-square&logo=storybook&logoColor=white">
</p>

### Backend

<p>
	<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=OpenJDK&logoColor=white"/>
  <img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=gradle&logoColor=white">
	<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/>
	<img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=springsecurity&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat-square&logo=spring&logoColor=white"/>
</p>

### Database

<p>
  <img src="https://img.shields.io/badge/Postgresql-003545?style=flat-square&logo=postgresql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white"/>
</p>

### DevOps

<p>
  <img src="https://img.shields.io/badge/Ubuntu-E95420?style=flat-square&logo=ubuntu&logoColor=white">
	<img src="https://img.shields.io/badge/AWS EC2-FF9900?style=flat-square&logo=amazonec2&logoColor=white"/>
	<img src="https://img.shields.io/badge/Jenkins-D24939?style=flat-square&logo=jenkins&logoColor=white"/>
	<img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white"/>
	<img src="https://img.shields.io/badge/NGINX-009639?style=flat-square&logo=nginx&logoColor=white"/>
</p>

### Communication

<p>
	<img src="https://img.shields.io/badge/figma-F24E1E?style=flat-square&logo=figma&logoColor=white">
  <img src="https://img.shields.io/badge/GitLab-FC6D26?style=flat-square&logo=gitlab&logoColor=white">
	<img src="https://img.shields.io/badge/jira-0052CC?style=flat-square&logo=jira&logoColor=white">
	<img src="https://img.shields.io/badge/notion-000000?style=flat-square&logo=notion&logoColor=white">
	<img src="https://img.shields.io/badge/Mattermost-0058CC?style=flat-square&logo=mattermost&logoColor=white">
</p>

---

## 📝 프로젝트 산출물

- [Notion](https://www.notion.so/588744f79b4b48308e66723e188582a4)

### 시스템 아키텍처

![System Architecture](./assets/system_architecture.png)

### E-R Diagram

![ERD](./assets/erd.png)

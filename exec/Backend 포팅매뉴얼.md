# Backend 포팅매뉴얼

## 소개

Pet&Me Backend 빌드 매뉴얼

## 기술스택 및 라이브러리

| Project     | Version | Description                |
| ----------- | ------- | -------------------------- |
| Java        | 11      | JDK 11 GA (build 11+28)    |
| Spring boot | 2.5.3   | Gradle, Build Tool         |
| MySQL       | 8.0.28  | DB                         |
| Mybatis     | 2.2.1   | DB                         |
| Swagger     | 1.6.2   | API 명세                   |
| Python      | 3.7.12  |                            |
| Django      | 3.2.9   | moviepy 사용하기 위한 서버 |
| IntelliJ    | 21.3.1  | Icommunity/ultimate  IDE   |
| VS Code     | 1.64.2  | IDE                        |

## 개발 환경 구성

> Windows 10 기준으로 개발 환경 구성 방법을 설명한다.

### 1. OpenJDK 설치

- JDK 다운로드 사이트에서 **11 GA 버전** 설치 파일 다운로드 및 실행

  - OpenJDK 공식 웹사이트 : https://jdk.java.net/archive/

- 명령 프롬프트(cmd) 확인

  ```shell
  > java -version
  ```

  ```shell
  openjdk version "11" 2018-09-25
  OpenJDK Runtime Environment 18.9 (build 11+28)
  OpenJDK 64-Bit Server VM 18.9 (build 11+28, mixed mode)
  ```

### 2. DB 구성 

> 이미 설치되어 있거나 원격 DB를 사용하는 경우 설치 부분 생략

- MySQL 사이트에서 **Windows (x86, 32-bit), MSI Installer (435.7M)** 설치 파일 다운로드 및 실행
  -  MySQL 공식 웹사이트 : https://dev.mysql.com/downloads/mysql/

- 명령 프롬프트(cmd) 확인

  ```shell
  > mysql --version
  ```

- dump.sql 파일 실행하여 DB 생성

### 3. 프로젝트 다운로드 및 설정

- 프로젝트 다운로드

  ```shell
  git clone <repo URL>
  ```

- `src/main/resources/application.properties` 

  > root 비밀번호 : root1234
  
  ```properties
  spring.datasource.driverClassName=com.mysql.jdbc.Driver
  spring.datasource.url=jdbc:mysql://i6b106.p.ssafy.io:3306/pet_sns?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8&allowMultiQueries=true
  spring.datasource.username=petsns
  spring.datasource.password=pet&meb106
  ```

### 4. Python 설치



## 디렉토리 구조

```
.
└─src
    ├─main
         ├─java
         │  └─com
         │      └─sns
         │          └─pet
         │              ├─config  /* Spring Config 파일 */
         │              ├─controller  /* Controller 단위의 클래스 */
         │              ├─dao  /* DB 연결을 위한 Data Access Object */
         │              ├─dto  /* 객체 클래스 */
         │              ├─exception  /* exception 파일 */	
         │              ├─interceptor  /* JWT interceptor */	
         │              └─service  /* 객체 서비스 클래스 */
         └─resources
         	└─mapper  /* Mapper xml 파일 */
```


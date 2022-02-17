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

### 4. Python 및 필요 라이브러리 설치

- **Python v3.7** 이상 권장

  - Python 공식 웹사이트 : https://www.python.org/downloads/

  - 설치 확인(CMD)

    ```bash
    > python
    Python 3.7.9 (tags/v3.7.9:13c94747c7, Aug 17 2020, 18:58:18) [MSC v.1900 64 bit (AMD64)] on win32
    Type "help", "copyright", "credits" or "license" for more information.
    ```

  

- Python 설치 후 `{origin_DIR}/tapingDjagno` 이동하여 필요 라이브러리 설치

  - `pip install -r requirements.txt`

  - > 필요에 따라 가상환경 상태에서 설치
    >
    > `python -m venv {venv_name}`
    >
    > `source {venv_name}/Scripts/activate `
    >
    > `pip install -r requirements.txt`

  - Django 서버 로컬 구동 확인

    ```bash
    > python manage.py migrate
    Operations to perform:
      Apply all migrations: admin, auth, contenttypes, sessions
    Running migrations:
      Applying contenttypes.0001_initial... OK
      ...
        Applying auth.0012_alter_user_first_name_max_length... OK
      Applying sessions.0001_initial... OK
     
     > python manage.py runserver
    Watching for file changes with StatReloader
    Performing system checks...
    
    System check identified no issues (0 silenced).
    February 18, 2022 - 04:10:28
    Django version 3.2.9, using settings 'tapingDjango.settings'
    Starting development server at http://127.0.0.1:8000/
    Quit the server with CTRL-BREAK.
    ```

- WSGI[gunicorn] 설치

  - gunicorn 공식 웹사이트 : https://gunicorn.org/

  - 또는 개인 Terminal에서 `pip install gunicorn`

  - 설치확인

    ```
    $ cat myapp.py
      def app(environ, start_response):
          data = b"Hello, World!\n"
    		...
          return iter([data])
    $ gunicorn -w 4 myapp:app
    [2014-09-10 10:22:28 +0000] [30869] [INFO] Listening at: http://127.0.0.1:8000 (30869)
    [2014-09-10 10:22:28 +0000] [30869] [INFO] Using worker: sync
    ...
      [2014-09-10 10:22:28 +0000] [30876] [INFO] Booting worker with pid: 30876
      [2014-09-10 10:22:28 +0000] [30877] [INFO] Booting worker with pid: 30877
    ```

    - 서버 시작 `sudo systemctl start gunicorn` 

​			





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
         	
         	
<-- Django -->
.
└─taping 	/* project app */
    ├─ ...
    ├─ urls.py	/* api 요청을 보내기 위한 url 매핑 파일 */
    ├─ views.py	/* 메소드에 따라 데이터 처리 로직을 실행하는 파일 */
    └─ {Users_video}	/* 생성된 video 파일을 저장하는 DIR */ 	
└─tapingDjango 	/* project */
    ├─ ...
    ├─settins.py	/* 로그, APP관리, admin관리 등 주요 세팅 파일 */
    ├─urls.py		/* api 요청을 보내기 위한 url 매핑 파일 */
    └─wsgi.py		/* WSGI 서버 구동을 위한 파일 */
├─ manage.py /* Django - Project 상호작용 연동 파일 */
└─ requirements.txt /* 필요 라이브러리 저장 파일 */
```


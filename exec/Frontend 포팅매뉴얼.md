# Frontend 포팅매뉴얼

## 소개

Pet&Me Frontend 빌드 매뉴얼

## 기술스택 및 라이브러리

| Project     | Version | Description                |
| ----------- | ------- | -------------------------- |
| Vue.js      | 2.6.11  | JavaScript Framework       |
| Vue CLI     | 4.5.15  | Vue Command Line Interface |
| Vuex        | 3.6.2   | State Management Library   |
| Vue-router  | 3.2.0   | Vue3 Official Router       |
| sass-loader | 10.2.1  | SCSS Compiler              |
| Node.js     | 10.15.3 |                            |
| npm         | 6.4.1   |                            |
| VScode      | 1.64.2  | IDE                        |

## 개발 환경 구성

1. 프로젝트 다운로드

   ```shell
   git clone <repo URL> <folder-name>
   ```

2. frontend 폴더로 이동

   ```shell
   cd <folder-name>/frontend
   ```

3. 패키지 설치

   ```shell
   npm install
   ```

4. 프로젝트 실행

   ```shell
   npm run serve
   ```

## 디렉토리 구조

```plaintext
.
└─src
    ├─api
    ├─assets  /* image, css, js 등의 리소스 */
    ├─components  /* 컴포넌트 단위의 Feed, Signup, SimilarAnimal관련 Vue 파일 */
    ├─config  /* API 경로 설정 파일 */
    ├─css  /* css 디자인 파일 */
    ├─js  /* Taping(영상) 제작 관련 파일 */
    ├─router  /* 컴포넌트 단위의 Vue 파일 */
    ├─store  /* Vuex 관련 파일 */
    └─views  /* 페이지 단위의 Vue 파일 */
```


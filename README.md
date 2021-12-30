<p align="center">
    <img width="200px;" src="https://raw.githubusercontent.com/woowacourse/atdd-subway-admin-frontend/master/images/main_logo.png"/>
</p>
<p align="center">
  <img alt="npm" src="https://img.shields.io/badge/npm-%3E%3D%205.5.0-blue">
  <img alt="node" src="https://img.shields.io/badge/node-%3E%3D%209.3.0-blue">
  <a href="https://edu.nextstep.camp/c/R89PYi5H" alt="nextstep atdd">
    <img alt="Website" src="https://img.shields.io/website?url=https%3A%2F%2Fedu.nextstep.camp%2Fc%2FR89PYi5H">
  </a>
  <img alt="GitHub" src="https://img.shields.io/github/license/next-step/atdd-subway-service">
</p>

<br>

# 지하철 노선도 미션
[ATDD 강의](https://edu.nextstep.camp/c/R89PYi5H) 실습을 위한 지하철 노선도 애플리케이션

<br>

## 🚀 Getting Started

### Install
#### npm 설치
```
cd frontend
npm install
```
> `frontend` 디렉토리에서 수행해야 합니다.

### Usage
#### webpack server 구동
```
npm run dev
```
#### application 구동
```
./gradlew bootRun
```
<br>

## ✏️ Code Review Process
[텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

<br>

## 🐞 Bug Report

버그를 발견한다면, [Issues](https://github.com/next-step/atdd-subway-service/issues) 에 등록해주세요 :)

<br>

## 📝 License

This project is [MIT](https://github.com/next-step/atdd-subway-service/blob/master/LICENSE.md) licensed.

# 기능 목록 정리
## 1단계 - 인수 테스트 기반 리팩터링리
- 공통
  - [X] 공통 상수 생성
  - [X] 커스텀 익셉션 생성
  - [X] 공통 메시지 생성
  - [X] 공통 에러 응답 생성
  - [X] 공통 에러 핸들러 생성
- LineService 리팩터링
  - Line
    - [X] 접근제어자 수정
    - [X] 정적 메소드 활용
    - [X] sections 일급콜렉션 사용
    - [X] 구간 추가 기능 추가
    - [X] 구간 삭제 기능 추가
    - [X] Validation 추가
  - Section
    - [X] 접근제어자 수정
    - [X] 정적 메소드 활용
    - [X] distance 분리 추가
    - [X] 상행역, 하행역 동일 여부 체크 로직 추가
    - [X] merge 기능 추가
  - Sections 
    - [X] 접근제어자 수정
    - [X] 정적 메소드 활용
    - [X] 일급콜렉션 생성
    - [X] merge 기능 추가
    - [X] 노선의 속한 구간에 상행, 하행역 동일 체크 로직 추가
    - [X] validation 기능 추가
  - Distance
    - [X] validataion 기능 추가
    - [X] 거리 더하기, 빼기 기능 추가
- LineSectionAcceptanceTest 리팩터링
  - [X] 텍스트 픽스쳐 생성
  - [X] 중복 테스트 제거 
  - [X] 전체 시나리오 나열
  - [X] 도메인 테스트 생성
    - [X] LineTest
    - [X] SectionTest
    - [X] SectionsTest

## 2단계 - 경로 조회 기능
- 요구사항
  - 최단 경로 조회 인수 테스트 만들기
  - 최단 경로 조회 기능 구현하기
- 미션 수행 순서
  - 인수 테스트 성공 시키기
  - 기능 구현
- 진행 순서
  - 지난 미션 피드백
    - [X] RestAdvice 커스텀 익센션별로 나눔
  - PathFinder
    - [X] 예외처리 로직
    - [X] jgraph 활용 최적 경로 찾는 로직
    - [X] 전 역 조회하는 메소드
  - PathFinderTest
    - [X] PathFinder 도메인 단위테스트 진행
  - PathServiceTest 
    - 가짜객체 활용하여 서비스 테스트 진행
  - PathAcceptanceTest 
    - 인수테스트 시나리오 작성
      - Feature : 최단 경로 조회
        - [X] Scenario: 출발역과 도착역의 최단경로 조회한다.
          - Given 지하철_역_생성_요청
          - Given 지하철_노선_생성_요청
          - Given 지하철_노선에_구간_등록_요청
          - When 지하철_최적_경로_요청
          - Then 지하철_최적_경로_목록_응답됨
          - Then 지하철_최적_경로_목록_포함됨
      - Feature : 최단 경로 조회 시 예외처리
        - [X] Scenario: 출발역과 도착역이 같을 경우 예외처리한다.
          - Given 지하철_역_생성_요청
          - Given 지하철_노선_생성_요청
          - Given 지하철_노선에_구간_등록_요청
          - When 지하철_최적_경로_요청
          - Then 지하철_최적_경로_목록_실패됨
      - Feature : 최단 경로 조회 시 예외처리
        - [X] Scenario: 출발역과 도착역이 연결이 되어 있지 않은 경우 예외처리한다.
          - Given 지하철_역_생성_요청
          - Given 지하철_노선_생성_요청
          - Given 지하철_노선에_구간_등록_요청
          - Given 다른_지하철_역_노선_구간_요청
          - When 지하철_최적_경로_요청
          - Then 지하철_최적_경로_목록_실패됨
      - Feature : 최단 경로 조회 시 예외처리
        - [X] Scenario: 존재하지 않은 출발역이나 도착역을 조회 할 경우 예외처리한다.
          - Given 지하철_역_생성_요청
          - Given 지하철_노선_생성_요청
          - Given 지하철_노선에_구간_등록_요청
          - When 등록되지_않는_도착역_지하철_최적_경로_요청
          - Then 지하철_최적_경로_목록_실패됨

## 3단계 - 인증을 통한 기능 구현
- 지난 미션 피드백 진행
  - [X] PathService 외부 라이브러리 결합 분리
  - [X] CustomeException 생성
  - [X] 불필요한 static 제거
- 요구사항 
  - 토큰 발급 기능 (로그인) 인수 테스트 만들기
  - 인증 - 내 정보 조회 기능 완성하기
  - 인증 - 즐겨 찾기 기능 완성하기
- 진행 순서
  - 토큰 발급 관련
    - AuthAcceptanceTest 작성 
      - [X] Feature: 로그인 기능 
        - Scenario: 로그인을 시도한다. 
          - Given 회원 등록되어 있음 
          - When 로그인 요청 
          - Then 로그인 됨
      - [X] Feature: 로그인 실패
        - Scenario: 등록되지 않은 회원으로 로그인 시도한다.
          - Given 등록되어 있지 않은 회원
          - When 로그인 요청
          - Then 로그인 실패
      - [X] Feature: 로그인 실패
        - Scenario: 입력한 정보가 등록된 정보와 다르게 로그인 시도한다.
          - Given 회원 등록되어 있음
          - When 로그인 요청
          - Then 로그인 실패
      - [X] Feature: 유효하지 않은 토큰
        - Scenario: 유효하지 않은 토큰으로 본인 정보 조회를 요청한다.
          - Given 유효하지 않은 토큰 발급
          - When 본인 정보 조회 요청
          - Then 본인 정보 조회 실패
    - AuthServiceTest 추가 작성
      - [X] mockitoExtension을 활용하여 단위테스트 작성
  - 사용자 관련
    - MemberAcceptanceTest 작성
      - [X] Feature: 나의 정보 관리
        - Scenario: 로그인 후 토큰 정보를 활용하여 나의 정보를 관리한다.
          - Given 회원 등록 요청
          - And 회원 생성됨 
          - And 로그인 요청
          - And 로그인 되어 있음
          - When 본인_정보_조회_요청
          - Then 본인_정보_조회됨
          - When 본인_정보_수정_요청
          - Then 본인_정보_수정됨
          - When 본인_삭제_요청
          - Then 본인_삭제됨
    - MemberRepository 작성 
      - [X] 사용자 저장, 정보 수정, 조회, 삭제 기능 작성
    - MemberTest 작성
      - [X] 패스워드 다르게 입력 시 예외 처리 작성
    - 인수테스트, 단위테스트 등으로 커버가 되어서 서비스테스트는 따로 작성하지 않았음
  - 즐겨찾기 관련
    - FavoriteAcceptanceTest 작성 
      - [X] Feature: 즐겨찾기를 관리한다. 
        - Background 
          - Given 지하철역 등록되어 있음 
          - And 지하철 노선 등록되어 있음 
          - And 지하철 노선에 지하철역 등록되어 있음 
          - And 회원 등록되어 있음 
          - And 로그인 되어있음 
        - Scenario: 즐겨찾기를 관리 
          - When 즐겨찾기 생성을 요청 
          - Then 즐겨찾기 생성됨 
          - When 즐겨찾기 목록 조회 요청 
          - Then 즐겨찾기 목록 조회됨 
          - When 즐겨찾기 삭제 요청 
          - Then 즐겨찾기 삭제됨
    - FavoriteServiceTest 작성
      - [X] mockitoExtension을 활용하여 단위테스트 작성
    - FavoriteRepositoryTest 작성
      - [X] 즐겨찾기 저장, 조회, 삭제 기능 작성
    - Favorite 
      - [X] Repository 생성
      - [X] Request / Response 셍성
      - [X] Controller 생성
      - [X] Service 생성
- 피드백 추가 진행
  - [X] PathService 외부 라이브러리 결합 분리
    - PathFinder : 전체 노선
    - PathStrategy : 최단 노선 찾기
    - DijkstraShortest : 외부 라이브러리를 이용한 최단 노선 찾기
    - Path : 최단 노선 결과LoginMember
  - [X] CustomException 클래스 확장서 고려
    - 추상클래스 변경
    - HttpStatus를 각가의 CustomeException에서 입력받을 수 있도록 처리
  - [X] Memeber와 Favorite의 강한 의존성 문제점 파악 및 개선
    - memeber를 직접 생성이 아닌 전달받은 객체를 활용 처리
  - [X] 그외 객체 이름 적절하게 수정, 컨벤션 수정

## 4단계 - 요금 조회
- 요구사항
  - 경로 조회 시 거리 기준 요금 정보 포함하기
  - 노선별 추가 요금 정책 추가
  - 연령별 할인 정책 추가
- 요구사항 설명
  - 거리별 요금 정책 
    - 기본운임(10㎞ 이내) : 기본운임 1,250원
    - 이용 거리초과 시 추가운임 부과
    - 10km초과∼50km까지(5km마다 100원)
    - 50km초과 시 (8km마다 100원)
    - 지하철 운임은 거리비례제로 책정됩니다. (실제 이동한 경로가 아닌 최단거리 기준으로 계산)
    - 수정된 인수 조건 
      - Feature: 지하철 경로 검색 
        - Scenario: 두 역의 최단 거리 경로를 조회 
        - Given 지하철역이 등록되어있음 
        - And 지하철 노선이 등록되어있음 
        - And 지하철 노선에 지하철역이 등록되어있음 
        - When 출발역에서 도착역까지의 최단 거리 경로 조회를 요청 
        - Then 최단 거리 경로를 응답 
        - And 총 거리도 함께 응답함 
        - And 지하철 이용 요금도 함께 응답함
  - 노선별 추가 요금 정책
    - 노선에 추가 요금 필드를 추가
    - 추가 요금이 있는 노선을 이용 할 경우 측정된 요금에 추가
    - ex) 900원 추가 요금이 있는 노선 8km 이용 시 1,250원 -> 2,150원
    - ex) 900원 추가 요금이 있는 노선 12km 이용 시 1,350원 -> 2,250원
    - 경로 중 추가요금이 있는 노선을 환승 하여 이용 할 경우 가장 높은 금액의 추가 요금만 적용
    - ex) 0원, 500원, 900원의 추가 요금이 있는 노선들을 경유하여 8km 이용 시 1,250원 -> 2,150원
  - 로그인 사용자의 경우 연령별 요금 할인 적용
    - 청소년: 운임에서 350원을 공제한 금액의 20%할인
    - 어린이: 운임에서 350원을 공제한 금액의 50%할인
    - 청소년: 13세 이상~19세 미만
    - 어린이: 6세 이상~ 13세 미만

- 지난 미션 피드백 진행
  - [X] LoginMember로 사용자 검증
  - [ ] A 유저가 만든 데이터를 B 유저가 삭제 요청을 했을 때 예외처리
  - [X] PathService에서 인터페이스로 주입
- 진행 순서
  - [X] 요금 계산 관련
    - [X] 요금 객체 클래스 
    - [X] 거리 계산 정책 클래스
    - [X] 나이 계산 정책 클래스 
    - [X] 테스트 반영
      - [X] 도메인 테스트
        - [X] 나이 계산 정책 테스트
          - [X] 청소년, 어린이, 성인의 따른 기본 운임료 계산
        - [X] 거리 계산 정책 테스트
          - [X] 10km이내, 11km ~ 50km까지, 51km 초과의 따른 추가 운임 계산
          - [X] 10km ~ 50km에서 5km마다 100원씩 증가
          - [X] 51km초과 시 8km마다 100원씩 증가
      - [X] 인수 테스트
        - [X] 경로조회 인수 테스트 수정 반영
      - [X] 서비스 테스트
        - [X] 경로조회 서비스 테스트 수정 반영
- 미션 피드백 진행
  - 외부 라이브러리를 사용한 최단거리 구하는 클래스 @Component 사용
    - 싱글톤으로 사용되기 때문에 클래스내 변수 공유되기 때문에 파라미터를 사용해서 유지
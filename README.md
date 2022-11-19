## 박스오피스와 검색 및 저장이 가능한 영화앱🍿

#### 영화진흥위원회 오픈API를 이용한 영화앱입니다.

## Tech🛠️
- `Kotlin`
- `MVVM` `Clean Architecture` `Multi Module`
- `databinding`
- `Coroutine` `Flow`
- `Retrofit2` `OkHttp3`
- `Hilt`
- `Room`
- `Navigtaion`
- `Glide` `BindingAdapter`

## About Service📱
- 메인  
<img src="https://user-images.githubusercontent.com/86879099/202859892-3eebd2fc-c2a2-4de1-b44f-9d9cc9d98c6c.gif" width="220" height="440"/>

- 디테일  
<img src="https://user-images.githubusercontent.com/86879099/202859897-4584c90a-e170-442f-a782-f86a0599d69d.gif" width="220" height="440"/> 

- 검색  
<img src="https://user-images.githubusercontent.com/86879099/202859903-f7d1b095-8d75-4125-923e-d009279a244d.gif" width="220" height="440"/>

- 북마크  
<img src="https://user-images.githubusercontent.com/86879099/202859906-eb09b18e-ef31-45e0-8511-67ae3176c887.gif" width="220" height="440"/>  


## Feat🎈
#### 영화 받아오기
- 3개의 api(영화리스트, 영화디테일, 포스터 및 평점)를 결합하여 페칭
- Flow의 flatMap api를 사용하여 결합
- 포스터를 다른 api를 사용하여 정확도가 떨어져 (제목+제작연도+감독)을 조건으로 매칭함
- shimmer effect를 사용 skeleton loading view 구현
- carouselrecyclerview를 사용하여 Trending 리스트 구현

#### 디테일
- 사용한 api
  - 배우 이미지: kakao검색 api
  - 줄거리: KMDB api 
  - 관련기사: 네이버 검색 api
  - 티저영상: Youtube 검색 api
- 배우 이미지의 경우 직접 내려준 api가 없어 '영화제목 + 배우'의 쿼리의 검색결과의 이미지 사용
- 줄거리의 경우 영화를 받아온 api와 달라 '영화제목+감독(한글or영문)'을 통해 검색
- 티저영상의 경우 직접 내려준 api가 없어 '영화제목 + 티저'의 쿼리 검색결과의 링크 사용
- ***직접내려주지 않은 api의 경우 정확도가 떨어질 수 있음***
- 줄거리는 영구저장, 배우이미지 7일단위 캐싱, 관련기사 3일단위 캐싱 적용

#### 검색
- kobis영화 검색 api
- api 일일 한도를 고려하여 debounce 적용x -> 최대 20개까지만 반영

#### 북마크
- Room 사용하여 저장
- 검색에 debounce 적용

#### 그 외
- 카카오톡 예매링크 공유
- 3사 영화홈페이지 예메화면 바로가기
- 유튜브 티저영상 이동

<img src="https://user-images.githubusercontent.com/86879099/202860136-692a7921-83e3-4139-9a51-1e12369f83f2.gif" width="180" height="360"/>  <img src="https://user-images.githubusercontent.com/86879099/202860140-eb8217dc-92da-4573-a2ff-5c4546cdcef2.gif" width="180" height="360"/>  <img src="https://user-images.githubusercontent.com/86879099/202860141-ffa004ee-c001-4e8a-875e-a1f2724f1007.gif" width="180" height="360"/>



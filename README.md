## 박스오피스와 검색 및 저장이 가능한 영화🍿 (진행중)

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
<img src="https://user-images.githubusercontent.com/86879099/202497257-6b0dc448-1e2f-4f9b-a487-561390dd826e.gif" width="220" height="440"/>
<img src="https://user-images.githubusercontent.com/86879099/202497825-576a402c-d2c6-44e9-b535-935d3fbe98e1.gif" width="220" height="440"/>
<img src="https://user-images.githubusercontent.com/86879099/202497834-ea53e7b7-5d75-47b3-a6c9-d3e9a032e798.gif" width="220" height="440"/>

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

#### 북마크(진행중)
- Room 사용
- dragdropswiperecyclerview 사용
- debounce 적용하여 검색

#### 그 외
- 카카오톡 예매링크 공유
- 3사 영화홈페이지 예메화면 바로가기
- 유튜브 티저영상 이동

<img src="https://user-images.githubusercontent.com/86879099/202504663-7f99f6d7-fbcd-4482-9e81-002f8a0268ff.jpg" width="180" height="360"/>  <img src="https://user-images.githubusercontent.com/86879099/202504654-b5f710fe-2d2a-44c8-9923-38ec0919fd36.jpg" width="180" height="360"/>  <img src="https://user-images.githubusercontent.com/86879099/202505180-e8c1754a-2370-4f96-aeb9-fd1356accd76.jpg" width="180" height="360"/>

## Todo❗
- 북마크 뷰 마무리
- Collapsing Toolbar 적용하기
- dragdropswiperecyclerview의 swipe를 이용한 북마크 영화 삭제
- UI 다듬기
- 코드 개선
- 각종 버그 잡기




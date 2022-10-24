package com.navermovie.data.remote.response


import kotlinx.serialization.Serializable

@Serializable
data class NaverArticleResponse(
    val display: Int?, // 10
    val items: List<Item?>?,
) {
    @Serializable
    data class Item(
        val description: String, // 드웨인 존슨의 &apos;<b>블랙 아담</b>&apos;이 한·미 극장가를 휩쓸었다. 24일 배급사 워너브라더스 코리아에 따르면, <b>블랙 아담</b>(감독 자움 콜렛 세라)은 개봉 첫 주말 국내와 북미 박스오피스 정상을 차지했다. 영화관입장권...
        val originallink: String, // http://www.newsis.com/view/?id=NISX20221024_0002059388&cID=10601&pID=10600
        val title: String // &apos;<b>블랙아담</b>&apos;, 사흘만에 북미 수익 965억…극장가 1위
    )
}
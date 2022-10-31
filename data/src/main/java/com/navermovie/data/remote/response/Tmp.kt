package com.navermovie.data.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tmp(
    @SerialName("Data")
    val data: List<Data>,
    @SerialName("KMAQuery")
    val kMAQuery: String, // 블랙 아담
    @SerialName("Query")
    val query: String, // 블랙 아담
    @SerialName("TotalCount")
    val totalCount: Int // 39
) {
    @Serializable
    data class Data(
        @SerialName("CollName")
        val collName: String, // kmdb_new2
        @SerialName("Count")
        val count: Int, // 10
        @SerialName("Result")
        val result: List<Result>,
        @SerialName("TotalCount")
        val totalCount: Int // 39
    ) {
        @Serializable
        data class Result(
            @SerialName("ALIAS")
            val aLIAS: String, // srcKmdb2
            @SerialName("actors")
            val actors: Actors,
            @SerialName("audiAcc")
            val audiAcc: String,
            @SerialName("Awards1")
            val awards1: String, // 전미비평가협회상(2002) : 실험영화상 - 리차드 링클레이터|뉴욕비평가협회상(2001) : 애니메이션상 -|로저 이버트 Roger Ebert TOP 10(2001) : 6 -|오타와국제애니메이션영화제(2002) : 카테고리B(커미션드) 최우수 장편 애니메이션 1등상 - 리차드 링클래이터, Fox Searchlight Pictures
            @SerialName("Awards2")
            val awards2: String,
            @SerialName("Codes")
            val codes: Codes,
            @SerialName("CommCodes")
            val commCodes: CommCodes,
            @SerialName("company")
            val company: String, // Detour Film Production, Flat Black Films, Independ
            @SerialName("DOCID")
            val dOCID: String, // F07910
            @SerialName("directors")
            val directors: Directors,
            @SerialName("episodes")
            val episodes: String,
            @SerialName("fLocation")
            val fLocation: String,
            @SerialName("genre")
            val genre: String, // 드라마,미스터리,판타지
            @SerialName("keywords")
            val keywords: String, // 애니메이션, 꿈, 초현실, 인디영화
            @SerialName("kmdbUrl")
            val kmdbUrl: String, // https://www.kmdb.or.kr/db/kor/detail/movie/F/07910
            @SerialName("modDate")
            val modDate: String, // 20101124
            @SerialName("movieId")
            val movieId: String, // F
            @SerialName("movieSeq")
            val movieSeq: String, // 07910
            @SerialName("nation")
            val nation: String, // 미국
            @SerialName("openThtr")
            val openThtr: String,
            @SerialName("plots")
            val plots: Plots,
            @SerialName("posters")
            val posters: String, // http://file.koreafilm.or.kr/thm/02/00/01/25/tn_DPF001163.jpg
            @SerialName("prodYear")
            val prodYear: String, // 2000
            @SerialName("ratedYn")
            val ratedYn: String, // Y
            @SerialName("rating")
            val rating: String, // 15세관람가
            @SerialName("ratings")
            val ratings: Ratings,
            @SerialName("regDate")
            val regDate: String, // 19000101
            @SerialName("repRatDate")
            val repRatDate: String, // 20020404
            @SerialName("repRlsDate")
            val repRlsDate: String,
            @SerialName("runtime")
            val runtime: String, // 101
            @SerialName("salesAcc")
            val salesAcc: String,
            @SerialName("screenArea")
            val screenArea: String,
            @SerialName("screenCnt")
            val screenCnt: String,
            @SerialName("soundtrack")
            val soundtrack: String,
            @SerialName("staffs")
            val staffs: Staffs,
            @SerialName("stat")
            val stat: List<Stat>,
            @SerialName("statDate")
            val statDate: String,
            @SerialName("statSouce")
            val statSouce: String,
            @SerialName("stlls")
            val stlls: String, // http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035565.jpg|http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035566.jpg|http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035567.jpg|http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035568.jpg|http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035569.jpg|http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035571.jpg|http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035572.jpg|http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035573.jpg|http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035575.jpg|http://file.koreafilm.or.kr/thm/01/copy/00/32/75/tn_DSFT035576.jpg
            @SerialName("themeSong")
            val themeSong: String,
            @SerialName("title")
            val title: String, //  웨이킹 라이프
            @SerialName("titleEng")
            val titleEng: String,
            @SerialName("titleEtc")
            val titleEtc: String, // 웨이킹 라이프^Waking Life^Waking Life^웨이킹라이프^웨이킹라이프^WakingLife^WakingLife^웨이킹라이프^
            @SerialName("titleOrg")
            val titleOrg: String, // Waking Life
            @SerialName("type")
            val type: String, // 애니메이션
            @SerialName("use")
            val use: String, // 극장용
            @SerialName("vods")
            val vods: Vods
        ) {
            @Serializable
            data class Actors(
                @SerialName("actor")
                val actor: List<Actor>
            ) {
                @Serializable
                data class Actor(
                    @SerialName("actorEnNm")
                    val actorEnNm: String, // Wiley Wiggins
                    @SerialName("actorId")
                    val actorId: String, // 00123369
                    @SerialName("actorNm")
                    val actorNm: String // 윌리 위긴스
                )
            }

            @Serializable
            data class Codes(
                @SerialName("Code")
                val code: List<Code>
            ) {
                @Serializable
                data class Code(
                    @SerialName("CodeNm")
                    val codeNm: String,
                    @SerialName("CodeNo")
                    val codeNo: String
                )
            }

            @Serializable
            data class CommCodes(
                @SerialName("CommCode")
                val commCode: List<CommCode>
            ) {
                @Serializable
                data class CommCode(
                    @SerialName("CodeNm")
                    val codeNm: String,
                    @SerialName("CodeNo")
                    val codeNo: String
                )
            }

            @Serializable
            data class Directors(
                @SerialName("director")
                val director: List<Director>
            ) {
                @Serializable
                data class Director(
                    @SerialName("directorEnNm")
                    val directorEnNm: String, // Richard Linklater
                    @SerialName("directorId")
                    val directorId: String, // 00111627
                    @SerialName("directorNm")
                    val directorNm: String // 리차드 링클래이터
                )
            }

            @Serializable
            data class Plots(
                @SerialName("plot")
                val plot: List<Plot>
            ) {
                @Serializable
                data class Plot(
                    @SerialName("plotLang")
                    val plotLang: String, // 한국어
                    @SerialName("plotText")
                    val plotText: String // 한 청년이 숱한 사람들을 만나면서 삶과 죽음과 꿈에 관한 이야기를 듣는다. 그들은 삶이란 무엇인가, 자아란 무엇인가, 그리고 영혼과 전생은 존재하는 가에 관한 자신들의 견해를 털어놓는다. 그 중에는 혼돈스러운 세상에서 자신의 목소리를 내기 위해 스스로 몸에 불을 질러 분신하는 사람도 있고, 괴한을 만난 이후 총을 소지하게 된 한 남자가 오발로 살인을 하기도 한다. 그가 기억하지 못하는 사람들이 그를 알아보기도 하고, 또 우연히 스치며 알게 된 사람들이 그를 기억하지 못하기도 한다. 스위치를 내려도 꺼지지 않는 조명, 시간을 읽을 수 없는 시계… 그리고 모든 사람들과의 대화는 꿈인지 현실인지 구분할 수 없는 상황이 계속 펼쳐지고, 그는 꿈을 따라가면서 깨어있는 삶과 꿈 사이의 절대적인 차이를 발견하고자 하는데…
                )
            }

            @Serializable
            data class Ratings(
                @SerialName("rating")
                val rating: List<Rating>
            ) {
                @Serializable
                data class Rating(
                    @SerialName("ratingDate")
                    val ratingDate: String, // 20020404
                    @SerialName("ratingGrade")
                    val ratingGrade: String, // 15세관람가
                    @SerialName("ratingMain")
                    val ratingMain: String, // Y
                    @SerialName("ratingNo")
                    val ratingNo: String, // 2002-F087
                    @SerialName("releaseDate")
                    val releaseDate: String,
                    @SerialName("runtime")
                    val runtime: String // 101
                )
            }

            @Serializable
            data class Staffs(
                @SerialName("staff")
                val staff: List<Staff>
            ) {
                @Serializable
                data class Staff(
                    @SerialName("staffEnNm")
                    val staffEnNm: String, // Richard Linklater
                    @SerialName("staffEtc")
                    val staffEtc: String,
                    @SerialName("staffId")
                    val staffId: String, // 00111627
                    @SerialName("staffNm")
                    val staffNm: String, // 리차드 링클래이터
                    @SerialName("staffRole")
                    val staffRole: String,
                    @SerialName("staffRoleGroup")
                    val staffRoleGroup: String // 감독
                )
            }

            @Serializable
            data class Stat(
                @SerialName("audiAcc")
                val audiAcc: String,
                @SerialName("salesAcc")
                val salesAcc: String,
                @SerialName("screenArea")
                val screenArea: String,
                @SerialName("screenCnt")
                val screenCnt: String,
                @SerialName("statDate")
                val statDate: String,
                @SerialName("statSouce")
                val statSouce: String
            )

            @Serializable
            data class Vods(
                @SerialName("vod")
                val vod: List<Vod>
            ) {
                @Serializable
                data class Vod(
                    @SerialName("vodClass")
                    val vodClass: String,
                    @SerialName("vodUrl")
                    val vodUrl: String
                )
            }
        }
    }
}
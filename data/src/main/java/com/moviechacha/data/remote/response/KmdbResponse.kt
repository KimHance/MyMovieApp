package com.moviechacha.data.remote.response


import com.google.gson.annotations.SerializedName

data class KmdbResponse(
    @SerializedName("Data")
    val data: List<Data>?,
) {
    data class Data(
        @SerializedName("Result")
        val result: List<Result?>?,
    ) {
        data class Result(
            val directors: Directors?,
            val plots: Plots?
        ) {
            data class Directors(
                val director: List<Director?>
            ) {
                data class Director(
                    val directorNm: String?, // 데잉 예
                    val directorEnNm: String?
                )
            }
            data class Plots(
                val plot: List<Plot>?
            ) {
                data class Plot(
                    val plotText: String? // 상하이 비밀조직의 공산당 고위지도자이자 혁명영웅 진. 그리고 그의 연인 주주. 진은 머리 부상으로 인해 주기적인 발작에 시달리는데 그때마다 주주가 국민당으로부터 자신을 보호하다가 살해된 죽은 아내라고 믿는다. 또한 주주는 어렸을 때 공산당원들이 자신의 아버지 하오 밍에 의해 처형되는 광경을 목격한다. 그녀는 아버지와 의절하고 어머니에 의해 선교사의 고아원으로 보내지지만, 곧 그녀의 어머니는 자살을 하고 주주는 혁명운동에 헌신하는 운명으로 빠진다. 시간이 흐르고 주주는 진과 사랑에 빠진다. 주주가 도움을 청한 페인 박사는 처음에는 진에 대한 주주의 사랑을 이해하지 못하지만 결국에는 그들이 자신들의 마음과 이상을 따르고 있다는 것을 깨닫는다. 한편 하오 밍은 국민당 비밀 경찰의 총수가 되고 공산당 지도자 진을 쫓는 도중 주주가 오래 전에 잃어버린 자신의 딸이란 사실을 알게 된다. 그는 딸을 검거하는 대신 주주로 하여금 비밀조직에서 그가 동지들에게 쌓아온 명성에 금이 가게 만든다. 수백 명의 상하이 엘리트의 모임에서 주주는 하오 밍을 사살하여 복수를 한 뒤 체포되어 살인죄로 기소된다. 법정에서 그녀는 아버지에 대하 자신의 증오와 공산당에 가입한 자부심을 감추지 않는다. 그녀가 진의 아이를 임신했다는 사실 또한 밝혀진다. 진은 주주의 청혼을 거절하지만 결국에는 그녀가 자신을 얼마나 사랑하며 어떠한 대가를 치르고라도 자신을 보호하려한다는 사실을 깨닫는다. 감옥의 운동장에서 진과 주주는 마지막으로 조우한다. 진은 그녀에 대한 자신의 진실한 사랑을 고백한다. 진은 자신의 딸이 감옥병원에서 태어나는 순간 처형당하며 주주는 아이를 낳다 사망한다. 그녀의 아이는 페인이 맡아 키우며 이름을 펄로 짓는다. 1949년 중국 인민해방군이 상하이에서 축포를 쏘고 곧 중국 전역을 해방시킨다. 페인은 펄이 해방군들과 춤을 추고 있는 것을 바라보면서 진과 주주가 생전에 그토록 열망했던 그들의 이상과 꿈이 실현되었음을 느낀다.
                )
            }
        }
    }
}
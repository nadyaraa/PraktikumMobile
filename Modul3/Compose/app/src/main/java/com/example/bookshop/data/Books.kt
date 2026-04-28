package com.example.bookshop.data

import com.example.bookshop.R

data class Books(
    val id: Int,
    val title: String,
    val writer: String,
    val publisher: String,
    val publicationYear: Int,
    val description: String,
    val imageId: Int,
    val olshopUrl: String
)

object BooksData {
    val BookList = listOf(
        Books(
            id = 1,
            title = "Madilog",
            writer = "Tan Malaka",
            publisher = "Narasi",
            publicationYear = 2014,
            description = "Ditulis dalam pelarian saat Indonesia berada di ambang kemerdekaan, kembalinya Tan Malaka dengan nama pena 'Iljas Hussein' melalui karya ini membawa pembaca menelusuri upaya radikal untuk membebaskan akal budi bangsa menuju cara berpikir yang ilmiah, rasional, dan revolusioner. Tiga konsep utamanya (Materialisme, Dialektika, dan Logik) menjadi pisau analisis tajam yang secara eksplisit bertujuan untuk memotong akar pemikiran yang tidak berdasarkan pada bukti, fakta, dan pengalaman.\n\n" +
                    "Berlatar situasi politik yang kacau balau di bawah penjajahan Jepang, Tan Malaka meramu narasi filsafat yang memadukan pemikiran Marxisme (Materialisme Dialektis) dengan Logika Hegelian, kemudian diolah dan dikontektualisasikan secara unik untuk kondisi Indonesia.",
            imageId = R.drawable.mdlg,
            olshopUrl = "https://tk.tokopedia.com/ZS9MaHeCw/"
        ),
        Books(
            id = 2,
            title = "Bumi Manusia",
            writer = "Pramoedya Ananta Toer",
            publisher = "Lentera Dipantara",
            publicationYear = 2005,
            description = "Bumi manusia, buku pertama dari “Tetralogi Pulau Buru”. Menceritakan perjalanan seorang anak manusia berdarah pribumi dengan seluk beluk Eropa, Minke. Kisah  yang bermula dari dunia pendidikan di sekolah HBS, sekolah bagi kaum totok (orang Eropa asli) atau Indo (campuran), atau si pribumi yang berkedudukan cukup tinggi. Minke tak pernah mengakui jaminan itu, Ia memperkenalkan dirinya sebagai Minke, tanpa nama keluarga, seorang pribumi. Dulu nama marga dianggap suatu hal yang sangat penting bagi kaum Eropa.\n\n" +
                    "Novel ini ditulis saat Pram masih berada di Pulau Buru sekitar tahun 1975. Cerita ini berlatar Wonokromo, Surabaya, dan beberapa kota lain di Jawa Timur. Menggambarkan keadaan  Indonesia di akhir 1800 hingga awal 1900 yang oleh sejarah kita tercatat sebagai masa awal Kebangkitan Nasional. Banyak hal yang dipelajari dari buku ini, kepribadian bangsa berkulit putih, sebuah perjuangan, banyak kisah sejarah, perjuangan yang tidak kenal lelah, melawanlah meski melalui lisan.\n",
            imageId = R.drawable.buman,
            olshopUrl = "https://tk.tokopedia.com/ZS9MmT2r9/"
        ),
        Books(
            id = 3,
            title = "Cantik Itu Luka",
            writer = "Eka Kurniawan",
            publisher = "Gramedia Pustaka Utama",
            publicationYear = 2020,
            description = "Kisah ini bermula dari peristiwa ajaib kebangkitan Dewi Ayu, seorang pelacur masyhur, dari kuburnya setelah dua puluh satu tahun kematiannya. Kembalinya sang ibu membawa pembaca menelusuri sejarah kelam sebuah keluarga yang didera oleh kutukan kecantikan. Tiga putrinya yang berparas jelita justru mengalami nasib tragis akibat nafsu dan kekerasan, sementara anak bungsunya yang berwajah buruk rupa justru diberi nama Cantik. Ironi ini menjadi pusat cerita yang mengikat nasib mereka dalam lingkaran dendam dan tragedi yang tak berkesudahan.\n\n" +
                    "Berlatar di kota fiktif Halimunda, Eka Kurniawan meramu narasi yang memadukan realisme magis dengan pasang surut sejarah Indonesia, mulai dari akhir masa kolonial hingga era kemerdekaan. Melalui gaya bercerita yang brutal namun memikat, novel ini menyajikan kritik sosial yang tajam bahwa kecantikan sering kali hanyalah luka yang tersembunyi.",
            imageId = R.drawable.ciluk,
            olshopUrl = "https://tk.tokopedia.com/ZS9MuJFbm/"
        ),
        Books(
            id = 4,
            title = "Dari Penjara Ke Penjara",
            writer = "Tan Malaka",
            publisher = "Narasi",
            publicationYear = 2015,
            description = "Buku ini menekankan bahwa perjuangan politik sejati seringkali dijalankan dalam bayang-bayang, bukan di atas panggung kekuasaan. Dengan narasi yang jujur dan analisis yang tajam, Tan Malaka menunjukkan bagaimana kehidupan berpindah-pindah, berganti-ganti nama, dan berulang kali keluar masuk penjara di berbagai negara (Hindia-Belanda, Filipina, Tiongkok, dsb.).\n\n" +
                    "Buku ini juga menawarkan kerangka pemikiran tentang strategi politik, ekonomi, dan pergerakan gerilya, yang menjadi konsep andalannya. Penulis membimbing pembaca untuk melihat dunia melalui mata seorang tokoh pergerakan yang tak pernah lelah mencari relasi internasional dan mempelajari bahasa serta lingkungan setempat. Buku ini ditahbiskan sebagai salah satu buku paling berpengaruh terhadap gagasan kebangsaan, sangat ideal bagi siapa pun yang ingin memahami jejak langkah dan pemikiran tokoh legendaris yang hidupnya penuh misteri dan kontroversi.",
            imageId = R.drawable.penjara,
            olshopUrl = "https://tk.tokopedia.com/ZS9MuxH3Q/"
        ),
        Books(
            id = 5,
            title = "Hujan Bulan Juni",
            writer = "Sapardi Djoko Damono",
            publisher = "Gramedia Pustaka Utama",
            publicationYear = 2015,
            description = "Bermula dari perjalanan seorang profesor Antropologi, Sarwono, dan sahabat sekaligus rekan kerjanya, Pingkan, yang menghadiri konferensi di Jepang. Perjalanan ini membawa pembaca menelusuri hubungan mereka yang dirangkai oleh rasa tak terucapkan selama bertahun-tahun. Sarwono, yang merupakan pria Jawa tradisional, dan Pingkan, perempuan Manado yang modern, terikat oleh perasaan mendalam, namun terhalang oleh perbedaan budaya dan hambatan sosial yang kompleks.\n\n" +
                    "Buku ini meramu narasi yang memadukan perenungan puitis tentang waktu, jarak, dan takdir, dengan dinamika hubungan yang realistis. Melalui gaya bercerita yang lembut namun penuh makna, disajikan juga kritik sosial yang halus tentang perjuangan merayakan cinta di tengah perbedaan dan kepastian perpisahan, yang pada akhirnya mengingatkan bahwa \"Tak ada yang lebih tabah dari hujan bulan Juni.\"",
            imageId = R.drawable.hujan,
            olshopUrl = "https://tk.tokopedia.com/ZS9MHLU95/"
        )
    )
}
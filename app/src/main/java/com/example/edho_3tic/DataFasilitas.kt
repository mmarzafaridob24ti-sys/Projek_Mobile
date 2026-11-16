package com.example.edho_3tic

// Sumber data fasilitas. Dipakai FasilitasDaruratFragment (daftar)
// dan InfoDaruratActivity (angka pada badge tab)
object DataFasilitas {
    val daftar = listOf(
        FasilitasModel("RSUD Arifin Achmad", "Rumah Sakit", "1,2 km", "119", R.drawable.ic_heart),
        FasilitasModel("RS Awal Bros Pekanbaru", "Rumah Sakit", "2,4 km", "119", R.drawable.ic_heart),
        FasilitasModel("RS Eka Hospital Pekanbaru", "Rumah Sakit", "3,1 km", "119", R.drawable.ic_heart),
        FasilitasModel("Klinik 24 Jam Terdekat", "Klinik", "0,8 km", "119", R.drawable.ic_heart),
        FasilitasModel("Pos Pemadam Kota Pekanbaru", "Pemadam Kebakaran", "1,6 km", "113", R.drawable.ic_fire),
        FasilitasModel("Pos Pemadam Sektor Timur", "Pemadam Kebakaran", "2,9 km", "113", R.drawable.ic_fire),
        FasilitasModel("Pos Pemadam Sektor Barat", "Pemadam Kebakaran", "3,8 km", "113", R.drawable.ic_fire),
        FasilitasModel("Polresta Pekanbaru", "Kepolisian", "2,0 km", "110", R.drawable.ic_shield),
        FasilitasModel("Polsek Terdekat", "Kepolisian", "1,1 km", "110", R.drawable.ic_shield),
        FasilitasModel("Pos Polisi Lalu Lintas", "Kepolisian", "1,9 km", "110", R.drawable.ic_shield),
        FasilitasModel("Kantor SAR Pekanbaru", "SAR / Basarnas", "5,2 km", "115", R.drawable.ic_warning),
        FasilitasModel("BPBD Kota Pekanbaru", "Penanggulangan Bencana", "4,3 km", "112", R.drawable.ic_warning)
    )
}
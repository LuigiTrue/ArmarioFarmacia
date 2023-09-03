package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.procurarMed.setOnClickListener {
            val nome = binding.nomeMed.text.toString().toUpperCase()
            val resultado = buscarFrasePorNome(nome)
            binding.resultadoMed.text = resultado
            binding.texto1.setText("RESULTADO")
        }


    }

    private fun buscarFrasePorNome(nome: String): String {
        val medicamentos = arrayOf("01.01.01 CARDIZEM SR 90mg\n\nPrincípio Ativo: CLORIDRATO DE DILTIAZEM\ncódigo: 7272",
            "02.01.01 CLARITIN 10mg\n\nPrincípio Ativo: LORATADINA\ncódigo: 4503 ",
            "03.01.01 COLTRAX 4mg\n\nPrincípio Ativo: TIOCOLCHICOSÍDEO\ncódigo: 283 ",
            "04.01.01 CRESTER 10mg \n\nPrincípio Ativo: ROSUVASTATINA\ncódigo: 36101",
            "05.01.01 DIGOXINA ASPEN 0,25mg \n\nPrincípio Ativo: DIGOXINA\ncódigo: 2219 ",
            "06.01.01 FENAREN 60mg \n\nPrincípio Ativo: DICLOFENACO SÓDICO\ncódigo: 167938 ",
            "07.01.01 \n\ncódigo: ",
            "08.01.01 KEFORAL 500mg\ncódigo: 51642 ",
            "01.02.01 CARDIZEM SR 120mg \n\ncódigo: 114015",
            "02.02.01 CLAVULIN BD 875mg + 125mg\n\ncódigo: 54992 ",
            "03.02.01 COMBIRON FÓLICO\ncódigo: 51495 ",
            "04.02.01 DAFLON 450mg + 50mg \ncódigo: 2303 ",
            "05.02.01 DIOVAN 80mg\ncódigo: 27351 ",
            "06.02.01 FERRONIL 40mg\ncódigo: 183162 ",
            "07.02.01 HIXIZINE 25mg \nPrincípio Ativo: Cloridrato de Hixizine\ncódigo: ",
            "08.02.01 KLARICID UD 500mg \n\ncódigo: 46983 ",
            "01.03.01 \ncódigo:",
            "02.03.01 CLAVULIN 500mg\nPrincípio Ativo: Amoxilina\ncódigo: 1251 ",
            "03.03.01 COMBODART 0,5mg + 0,3mg\nPrincípio Ativo: Dutasterida + Tansulosina\ncódigo: 132897 ",
            "04.03.01 DALACIN 300mg\nPrincípio Ativo: Cloridrato de Clindamicina \ncódigo: 609 ",
            "05.03.01 DIVELOL 3,125mg\nPrincípio Ativo: Carvedilol\ncódigo: 6412",
            "06.03.01 FLAGASS 40mg\nPrincípio Ativo: Simeticona \ncódigo: 172297",
            "07.03.01 HYDREA 500mg\nPrincípio Ativo: Hidroxiureia\ncódigo: 4758 ",
            "08.03.01 LABIRIN 24mg\nPrincípio Ativo: Betaistina\ncódigo: ",
            "01.04.01 CARVEDILOL 6,25mg\nPrincípio Ativo: Carvedilol\ncódigo: 165389 ",
            "02.04.01 CLO. VERAPAMIL 80mg\nPrincípio Ativo: Verapamil\ncódigo: 568 ",
            "03.04.01 CONCOR 2,5mg\nPrincípio Ativo: Fumarato de bisoprolol\ncódigo: 55024 ",
            "04.04.01 DARAPRIM 25mg\nPrincípio Ativo: Pirimetamina\ncódigo: 6154 ",
            "05.04.01 DOSTINEX 0,5mg\nPrincípio Ativo: Cabergolina\ncódigo: 47762 ",
            "06.04.01 FLAGYL 250mg\nPrincípio Ativo: Metronidazol\ncódigo: 55231 ",
            "07.04.01 HYPERIUM 1mg\nPrincípio Ativo: Dihidrogenofosfato De Rilmenidina\ncódigo: 157459 ",
            "08.04.01 \ncódigo:  ",
            "01.05.01 CEFADROXILA 500mg\nPrincípio Ativo: Cefadroxila\ncódigo: 123567",
            "02.05.01 CLORANA 25mg\nPrincípio Ativo: Hidroclorotiazida\ncódigo: 384431 ",
            "03.05.01 CONCOR 10mg\nPrincípio Ativo: Fumarato de bisoprolol\ncódigo: 55023",
            "04.05.01 DECADROM 4mg\nPrincípio Ativo: Dexametasona\ncódigo: 2238 ",
            "05.05.01 DULCOLAX 5mg\nPrincípio Ativo: Bisacodil\ncódigo: 1227",
            "06.05.01 FLAGYL 400mg\nPrincípio Ativo: Metronidazol \ncódigo: 626",
            "07.05.01 IMOSEC 2mg\nPrincípio Ativo: Cloridrato de Loperamida\ncódigo: 726 ",
            "08.05.01\nPrincípio Ativo:\ncódigo: ",
            "01.06.01 CEWIN 500mg\nPrincípio Ativo: Ácido Ascórbico\ncódigo: 22302 ",
            "02.06.01 CLORIDRATO DE SOTALOL 120mg\nPrincípio Ativo: Cloridrato de Sotalol\ncódigo: 121242 ",
            "03.06.01 COVERSYL 4mg\nPrincípio Ativo: Perindopril Arginina + Besilato de Anlodipino\ncódigo: 11479 ",
            "04.06.01 DIAMOX 250mg\nPrincípio Ativo: Acetazolamida\ncódigo: 925",
            "05.06.01 ENDOFOLIN 5mg\nPrincípio Ativo: Ácido Fólico\ncódigo: 51803",
            "06.06.01 FLAXIN 5mg\nPrincípio Ativo: Finasterida\ncódigo: 162694",
            "07.06.01 IMURAN 50mg\nPrincípio Ativo: azatioprina\ncódigo: ",
            "08.06.01\nPrincípio Ativo:\ncódigo:",
            "01.07.01\ncódigo:",
            "02.07.01 CLORTALIDONA 25mg\nPrincípio Ativo: clortalidona\ncódigo: 163124",
            "03.07.01 COZAAR 50mg\nPrincípio Ativo:\ncódigo: 4937 ",
            "04.07.01 DICOFLENACO SÓDICO 50mg\nPrincípio Ativo:\ncódigo: 2189",
            "05.07.01 EPHYNAL 400mg\nPrincípio Ativo:\ncódigo: 19391 ",
            "06.07.01 FLORATIL 100mg\nPrincípio Ativo:\ncódigo: 796",
            "07.07.01 ISORDIL SL 5Mmg\nPrincípio Ativo:\ncódigo: 23618 ",
            "08.07.01 LIPITOR 10mg\nPrincípio Ativo:\ncódigo:  ",
            "01.08.01 CITONEURIN 5.000\ncódigo: 2153",
            "02.08.01 COLCHIS 0,5mg\ncódigo: 41921",
            "03.08.01 CREON 25.000UI\ncódigo: 85093",
            "04.08.01 DIGESAN 10mg\ncódigo: 492 ",
            "05.08.01 ETNA 2,5mg + 1,5mg + 1mg \ncódigo: 55248 ",
            "06.08.01 FLORATIL 200mg\ncódigo: 112983 ",
            "07.08.01 ISORDIL 10mg\ncódigo: 1713 ",
            "08.08.01 LIPLESS 100mg\ncódigo: 54793",
            "01.09.01 FENERGAN 25mg\ncódigo:875",
            "02.09.01 ENTRESTO 24 + 25mg\ncódigo: 172359",
            "03.09.01 EFFIENT 10mg \ncódigo: 108213",
            "04.09.01 DRAMIN B6 50mg + 10mg\ncódigo: 429",
            "05.09.01 ENTRESTO 49mg + 51mg \ncódigo:172377",
            "06.09.01 ANCORON 100mg\ncódigo: 54829",
            "07.09.01 \ncódigo:",
            "08.09.01 LIVALO 1mg \ncódigo: 167532",
            "01.10.01 PROLOPA 100mg + 25mg\ncódigo: 55380",
            "02.10.01 LIPITOR 40mg\ncódigo: 38292",
            "03.10.01 RIFALDIN 300mg\ncódigo: 6180",
            "04.10.01 SINGULAIR 10mg\ncódigo: 24161",
            "05.10.01 SUSTRATE 10mg\ncódigo: 1735",
            "06.10.01 TRANSAMIN 250mg\ncódigo: 94331",
            "07.10.01 DIAMICRON 30mg\ncódigo: ",
            "08.10.01 ZINNAT 250mg\ncódigo: 4147",
            "01.11.01 PROLOPA BD 100mg + 25mg\ncódigo: 150787",
            "02.11.01 PYRIDIUM 100mg\ncódigo: 1704 ",
            "03.11.01 SECNIDAL 1g\ncódigo: 119007",
            "04.11.01 SINVASTATINA 10mg\ncódigo: 11880",
            "05.11.01 TAMARINE \ncódigo: 51438",
            "06.11.01 TYLENOL 750mg \ncódigo: 14251",
            "07.11.01 VOLTAREN 50mg\ncódigo: 7113",
            "08.11.01 ZITROMAX\ncódigo: 12703 ",
            "01.12.01 PROLOPA HBS 100mg + 25mg\ncódigo: 538052",
            "02.12.01 PYRIDIUM 200mg \ncódigo: 151828",
            "03.12.01 \ncódigo: ",
            "04.12.01\ncódigo: ",
            "05.12.01 TAMIFLU 75mg \ncódigo: 57623",
            "06.12.01 UNOPROST 2mg \ncódigo: 56010",
            "07.12.01 VONAU FLASH 4mg\ncódigo: 56220",
            "08.12.01 ZOLTEC 100mg \ncódigo: 51725",
            "01.13.01 PROPANOLOL 10mg \ncódigo: 15278 ",
            "02.13.01 RANIDIN 150mg\ncódigo: 167959",
            "03.13.01 SELOZOK 25mg\ncódigo: 39583",
            "04.13.01 SLOW K 600mg\ncódigo: 1511",
            "05.13.01 TAPAZOL 5mg\ncódigo: 11220",
            "06.13.01 URSACOL 150mg\ncódigo: 18690",
            "07.13.01 VYTORIA 10/20mg\ncódigo: 55231",
            "08.13.01 ZOLTEC 150mg\ncódigo: ",
            "01.14.01 NDA \ncódigo: NDA",
            "02.14.01 RANITEC 5mg\ncódigo: 862",
            "03.14.01 SELOZOK 100mg\ncódigo: 55877",
            "04.14.01 SOMALGIN CARDIO 325mg\ncódigo: 45592",
            "05.14.01 TEFLAN 20mg\ncódigo: 168963",
            "06.14.01 VASOGARD 100mg\ncódigo: 55033",
            "07.14.01 ZANIDIP 10mg\ncódigo: 56266",
            "08.14.01 ZOVIRAX 200mg\ncódigo: 2189",
            "01.15.01 PROPANOLOL 80mg\ncódigo: 126497",
            "02.15.01 RANITEC 20mg\ncódigo: 890",
            "03.15.01 SOLOZOK 50mg\ncódigo: 39413",
            "04.15.01 SOTALOL 160mg\ncódigo: 7322",
            "05.15.01 THIOCTACID HR 600mg\ncódigo: 55972",
            "06.15.01 VASTAREL MR 35mg \ncódigo: 56039",
            "07.15.01 ZENTEL 400mg\ncódigo: 19551",
            "08.15.01 ZYLORIC 100mg \ncódigo: 893",
            "01.16.01 PURAN T4 25mcg\ncódigo: 7790 ",
            "02.16.01 RETEMIC 5mg\ncódigo: 7933",
            "03.16.01 SEPURIC 120mg + 20mg\ncódigo: 55679",
            "04.16.01 NDA\ncódigo: NDA",
            "05.16.01 \ncódigo: 791",
            "06.16.01 VERTIX 10mg \ncódigo: 2137",
            "07.16.01 ZESTREIL 5mg\ncódigo: 7947",
            "08.16.01 ZYLORIC 300mg\ncódigo: ",
            "01.17.01 PURAN T4 88mcg\ncódigo: 59783",
            "02.17.01 GLIFAGE 850mg\ncódigo: 24305 ",
            "03.17.01 SIGMATRIOL 0,25mcg\ncódigo: 138968",
            "04.17.01 SUPOSITÓRIO DE GLICERINA ADULTO \ncódigo: 2204",
            "05.17.01 TIOFAN 100mg\ncódigo: 14450",
            "06.17.01 VERTIZINE 0,3mg + 10mg\ncódigo: 37441",
            "07.17.01 ZETIA 10mg \ncódigo: 96278",
            "08.17.01 NUJOL OLEO 120ml\ncódigo: 127",
            "01.18.01 PURAN T4 100mcg\ncódigo: 11331",
            "02.18.01 RITMONORM 300mg\ncódigo: 8050",
            "03.18.01 NOVALGINA 50mg/ml SOL ORAL 100ml\ncódigo: 140710",
            "04.18.01 STUGERON 75mg\ncódigo: 1733",
            "05.18.01 TETRACICLINA\ncódigo: 11312",
            "06.18.01 VESICARE 5mg \ncódigo: 126777",
            "07.18.01 JARDIANCE 25mg\ncódigo: 165317",
            "08.18.01 PRADAXA 150mg\ncódigo: 116535",
            "01.01.02 AAS 100mg\ncódigo: 66",
            "02.01.02 ALDOMET 250mg\ncódigo: 833",
            "03.01.02 AMOXIL 500mg\ncódigo: 585",
            "04.01.02 ARADOIS\ncódigo: 54849",
            "05.01.02 ATENOLOL 25mg\ncódigo: 18101",
            "06.01.02 BACTRIM 400mg + 80mg\ncódigo: 51775 ",
            "07.01.02 BENICAR 40mg\ncódigo: 54878",
            "08.01.02 CAPTOPRIL 25mg\ncódigo: 849 ",
            "01.02.02 ACICLOVIR 400mg\ncódigo: 56592",
            "02.02.02 ALDOMET 500mg\ncódigo: 835",
            "03.02.02 AMPICILINA 500mg\ncódigo: 602",
            "04.02.02 ARTRINID 50mg\ncódigo: 51562",
            "05.02.02 ATENOLOL 50mg\ncódigo: 9100",
            "06.02.02 BACTRIM F 800mg + 160mg\ncódigo: 24241",
            "07.02.02 BUSCOPAN COMPOSTO \ncódigo: 269",
            "08.02.02 CAPTOPRIL 50mg\ncódigo: 132217",
            "01.03.02 ADALAT 10mg \ncódigo: 827",
            "02.03.02 ALLEGRA 40mg\ncódigo: 11531",
            "03.03.02 ANNITA 500mg \ncódigo: 54837",
            "04.03.02 HIXIZINE 2mg/ml\ncódigo: 160650",
            "05.03.02 ATENSINA 0,150mg\ncódigo: 5791",
            "06.03.02 BAMIFIX 300mg\ncódigo: 6460",
            "07.03.02 BUSCOPAN 10mg\ncódigo 273",
            "08.03.02 CARDILOL 25mg\ncódigo: 23623",
            "01.04.02 ALDACTONE 25mg\ncódigo: 991",
            "02.04.02 ALLEGRA D 60mg + 120mg\ncódigo: 18953",
            "03.04.02 APRESOLINA 25mg\ncódigo:4113",
            "04.04.02 ASPIRINA PREVENT 300mg\ncódigo: 54858",
            "05.04.02 AVALOX 400mg\ncódigo: 29791",
            "06.04.02 BENERVA 300mg\ncódigo: 2142",
            "07.04.02 CALTREN 10mg\ncódigo: 24992",
            "08.04.02 CRESTOR 20mg\ncódigo: 55095",
            "01.05.02 ALDACTONE 100mg\ncódigo: 989",
            "02.05.02 ALLEGRA 150mg\ncódigo: 11581",
            "03.05.02 APRESOLINA 50mg\ncódigo: 837",
            "04.05.02 ATACAND 8mg\ncódigo: 26022",
            "05.05.02 BACLON 10mg\ncódigo: 103164",
            "06.05.02 ANTUX 6mg/ml\ncódigo: 57686",
            "07.05.02 BARACLUDE 0,5mg\ncódigo: 130797",
            "08.05.02 CARDIZEM 30mg\ncódigo: 850",
            "01.06.02 LORATADINA 1mg/ml 100ml\ncódigo: 168926",
            "02.06.02 NDA\ncódigo: NDA",
            "03.06.02 MUCOSOLVAN ADULTO 30mg/5ml\ncódigo: 160675",
            "04.06.02 ASPIRINA PREVENT 100mg\ncódigo: 51586",
            "05.06.02 POLARAMINE 2mg/5ml 120ml\ncódigo: 160714",
            "06.06.02 BENICAR 20mg\ncódigo: 51839",
            "07.06.02 CAPTOPRIL 12,5mg\ncódigo: 847",
            "08.06.02 CARDIZEM 60mg\ncódigo: 852",
            "01.07.02 LONITEN 10mg\ncódigo: 7580",
            "02.07.02 MAXALT 10mg\ncódigo: 55623",
            "03.07.02 MIOCARDIL 30mg\ncódigo: 172098",
            "04.07.02 NAPRIX 2,5mg\ncódigo: 55749",
            "05.07.02 NAUSICALM B6 50+10mg\ncódigo: 167939",
            "06.07.02 NORFLOXACINO 400mg\ncódigo: 397",
            "07.07.02 PARKIDOPA 25+250mg\ncódigo: 55628",
            "08.07.02 PLASIL 10mg\ncódigo: 525",
            "01.08.02 LOSEC MUPS 20mg\ncódigo: 14192",
            "02.08.02 MECLIN 25mg\ncódigo: 42753",
            "03.08.02 MIOSAN 10mg\ncódigo: 36761",
            "04.08.02 NAPRIX 5mg\ncódigo: 55750",
            "05.08.02 NEBILET 5mg\ncódigo: 55765",
            "06.08.02 NORIPURUM 100mg\ncódigo: 55364",
            "07.08.02 PENTOXIFILINA 400mg\ncódigo: 2129",
            "08.08.02 PLAVIX 75mg\ncódigo: 15821",
            "01.09.02 LOSEC MUPS 40mg\ncódigo: 157462",
            "02.09.02 MESACOL 800mg\ncódigo: 51501",
            "03.09.02 MODURETIC 50mg + 5mg\ncódigo: 1005",
            "04.09.02 NATRILIX SR 1,5mg\ncódigo: 55759",
            "05.09.02 NEUTROFER 500mg\ncódigo: 55776",
            "06.09.02 NOVALGINA 500mg\ncódigo: 347",
            "07.09.02 PERMANGANATO DE POTÁSSIO 100mg\ncódigo: 2282",
            "08.09.02 POLARAMINE 2mg\ncódigo: 881",
            "01.10.02 MACRODANTINA 100mg\ncódigo: 9933",
            "02.10.02 METICORTEN 5mg\ncódigo: 421",
            "03.10.02 MONOCORDIL 20mg\ncódigo: 1717",
            "04.10.02 NATRILIX 2,5mg\ncódigo: 11477",
            "05.10.02 NISULID 100mg\ncódigo: 55784",
            "06.10.02 NOVANLO 2,5mg \ncódigo: 140912",
            "07.10.02 PIDOMAG B3\ncódigo: 165940",
            "08.10.02 PLAQUINOL 400mg\ncódigo: 55818",
            "01.11.02 MANIVASC 10mg\ncódigo: 55631",
            "02.11.02 METICORTEN 20mg\ncódigo: 420",
            "03.11.02 MOTILIUM 10mg\ncódigo: 3763",
            "04.11.02 NATURETTI \ncódigo: 55762",
            "05.11.02 NORVASC 5mg\ncódigo: 23655",
            "06.11.02 MOBILITY OS-CAL 500mg\ncódigo: 318239",
            "07.11.02 MOBILITY OS-CAL D 500mg + 400ui\ncódigo: 318218",
            "08.11.02 PREDSIM 20mg\ncódigo: 55845",
            "08.12.02 MESACOL 500mg\ncódigo: 55581",
            "08.12.02 MICARDIS 40mg\ncódigo: 55569",
            "08.12.02 MYBETRIC 50mg\ncódigo: 170203",
            "08.12.02 NAUSEDRON 8mg\ncódigo: 57512",
            "08.12.02 MYFORTIC 360mg\ncódigo: 991 ",
            "08.12.02 PANTOZOL 40mg\ncódigo: 4962",
            "08.12.02 VALTREX 500mg\ncódigo: 22951 ",
            "08.12.02 PROCORALAN 5mg\ncódigo: 112917")

       if(nome.isEmpty()){
            Toast.makeText(this, "Digite o nome do medicamento", Toast.LENGTH_SHORT).show()
            return ""
           binding.texto1.setText("")
        }

        for (medicamento in medicamentos) {
            if (medicamento.contains(nome)) {
                val oitavoCaractere = medicamento[7]
                val armario: String = when (oitavoCaractere) {
                    '1' -> "ARMÁRIO DE TRÁS"
                    '2' -> "ARMÁRIO DA FRENTE"
                    else -> "ARMÁRIO INEXISTENTE"
                }
                return "FILEIRA: ${medicamento.substring(0, 2)}\nCOLUNA: ${
                    medicamento.substring(3, 5)
                }\n$armario\n\n\n${medicamento.substring(9)}"
            }

        }
        return "Resultado da pesquisa: Nome não encontrado."
    }

}
















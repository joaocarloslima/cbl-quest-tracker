package br.com.joaocarloslima.cbl_quest_tracker.student;

public enum Student {

    ALINE_FERNANDA("Aline"),
    ALISSA_MIKI("Alissa"),
    AMANDA_CAROLINE("Amanda"),
    ANA_CAROLINA("Ana"),
    ANDREZA_MARIANNO("Andreza"),
    BRUNA_KINJO("Bruna"),
    CAMILA_ABREU("Camila"),
    CARLA_DHEYSLANE("Carla"),
    CINTIA_LOPES("Cíntia"),
    ERICK_COSTA("Erick"),
    GIULIA_CACCIAGUERRA("Giulia"),
    GUILHERME_FAGGION("Guilherme Fabri"),
    GUILHERME_MATEUS("Guilherme Mateus"),
    GUSTAVO_SOUZA("Gustavo"),
    HEITOR_IVAZA("Heitor"),
    HELOISA_YAMANAKA("Heloisa"),
    HENRIQUE_LEAL("Henrique"),
    JOAO_VICTOR("João"),
    JOSE_ELIAS("José Elias"),
    JOSE_JOAQUIN("José Joaquin"),
    KAUE_ARAUJO("Kauê"),
    LUISIANA_THAIRINA("Luisiana"),
    MARIA_MERCEDES("Maria"),
    MATHEUS_MARINI("Matheus"),
    NOA_SANTANA("Noá"),
    PATRICIA_MILET("Patrícia"),
    PEDRO_PAULO("Pedro"),
    RUAN_LOPES("Ruan"),
    STELLA_ARANTES("Stella"),
    THAIS_RODRIGUES("Thais");

    private final String displayName;

    Student(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
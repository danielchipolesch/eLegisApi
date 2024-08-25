package br.com.danielchipolesch.domain.entities.DocumentNumbering;

public enum BasicSubject {
    DCA("Diretriz do Comando da Aeronáutica (DCA} - espécie destinada, precipuamente,\n" +
            "a definir, estabelecer ou orientar em caráter global, setorial ou específico, a Concepção\n" +
            "Estratégica do Comando da Aeronáutica nos campos de ação essenciais ao desenvolvimento da\n" +
            "Aeronáutica e ao fortalecimento e emprego do Poder Aeroespacial. Reservado apenas ao\n" +
            "Comandante da Aeronáutica, através do Decreto-Lei nº 991, de 21 de outubro de 1969, art.63."),
    FCA("Folheto do Comando da Aeronáutica (FCA) - espécie destinada a informar e a\n" +
            "noticiar assuntos específicos, de caráter administrativo, técnico, didático, literário, e à publicação\n" +
            "de transcrições, reproduções, traduções de livros, artigos, reportagens, discursos, conferências,\n" +
            "pronunciamentos, pareceres e relatórios"),
    ICA("Instrução do Comando da Aeronáutica (ICA) - espécie de caráter determinativo\n" +
            "e diretivo que é destinada a divulgar regras, preceitos, critérios, recomendações e\n" +
            "procedimentos diversos, visando a facilitar, de maneira inequívoca, a aplicação de leis, decretos,\n" +
            "portarias e regulamentos"),
    MCA("Manual do Comando da Aeronáutica (MCA) - espécie de caráter diretivo,\n" +
            "informativo e/ou didático destinada a regular e a divulgar assuntos relacionados com doutrina,\n" +
            "ensino, instrução, técnica, emprego de Unidades, de equipamentos ou de armamentos,\n" +
            "podendo, ainda, completar matéria já tratada em outros atos normativos. Os Manuais podem,\n" +
            "também, ser usados para compilação de matérias, tais como: os glossários, os dicionários, as\n" +
            "relações de abreviaturas, siglas e símbolos"),
    NSCA("Norma de Sistema do Comando da Aeronáutica (NSCA) - espécie destinada a\n" +
            "disciplinar, tecnicamente, matérias e assuntos ligados à atividade-meio do sistema considerado. As\n" +
            "NSCA são elaboradas exclusivamente pelos órgãos centrais dos sistemas, sendo aprovadas por ato\n" +
            "do Comandante ou Dirigentes do Órgão Central, de acordo com a competência regimental ou\n" +
            "delegada, cujo sistema se situem em suas estruturas de comando, conforme ICA 700-1\n" +
            "(Implantação e Gerenciamento de Sistemas no Comando da Aeronáutica)"),
    OCA("Ordem do Comando da Aeronáutica (OCA) - espécie de caráter determinativo\n" +
            "no campo operacional, a qual é destinada a consubstanciar as decisões tomadas em\n" +
            "determinado momento para cumprimento de uma missão"),
    PCA("Plano do Comando da Aeronáutica (PCA) - espécie de caráter determinativo\n" +
            "que é destinada a consubstanciar as decisões tomadas em um determinado momento e nível\n" +
            "hierárquico, a consecução de objetivos finais a serem alcançados no determinado período\n" +
            "específico"),
    RCA("Regulamento do Comando da Aeronáutica (RCA) - espécie de caráter\n" +
            "determinativo e diretivo que é destinada a dispor sobre a execução de leis ou de decretos e,\n" +
            "como tal, destina-se a estabelecer preceitos de administração e demais atividades gerais do\n" +
            "Comando da Aeronáutica, tais como: prescrições específicas relativas a recursos humanos,\n" +
            "economia, finanças, material, serviços internos, patrimônio e outros assuntos cabíveis de serem\n" +
            "regulamentados no seu âmbito. Sua aprovação é exclusiva ao Comandante da Aeronáutica"),
    RICA("Regimento Interno do Comando da Aeronáutica (RICA) - espécie destinada a\n" +
            "estabelecer o detalhamento da estrutura da Organização Militar, disciplinando o funcionamento\n" +
            "e as competências de seus órgãos constitutivos, em complemento ao respectivo Regulamento de\n" +
            "Organização"),
    ROCA("Regulamento de Organização do Comando da Aeronáutica (ROCA) - É a espécie\n" +
            "destinada a estabelecer a finalidade, a subordinação, a sede, a estrutura básica e as atribuições\n" +
            "gerais de uma Organização Militar. Pode referir-se a uma Organização específica ou a um\n" +
            "determinado tipo de Organização. Sua aprovação é exclusiva do Comandante da Aeronáutica"),
    TCA("Tabela do Comando da Aeronáutica (TCA) - espécie destinada a registrar,\n" +
            "catalogar, relacionar, listar e divulgar, periódica e detalhadamente, assuntos gerais, tais como:\n" +
            "cursos, cálculos, índices, publicações, desdobramentos estruturais, distribuição de material,\n" +
            "equipamento, endereços, etc");

    private String description;

    BasicSubject(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}

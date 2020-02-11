DROP DATABASE IF EXISTS scuolecalcioDB;
CREATE DATABASE scuolecalcioDB;
USE scuolecalcioDB;
DROP USER IF EXISTS 'userscuolecalcio'@'%';
CREATE USER 'userscuolecalcio'@'%' IDENTIFIED BY 'psw';
GRANT ALL ON scuolecalcio.* TO 'userscuolecalcio'@'%';

DROP TABLE IF EXISTS centrosportivo;
CREATE TABLE centrosportivo (
    nome VARCHAR(20) NOT NULL,
    via VARCHAR(20) NOT NULL,
    citta VARCHAR(20) NOT NULL,
    civico SMALLINT NOT NULL,
    PRIMARY KEY (nome)
);

INSERT INTO centrosportivo VALUES ('Campi in fiore', 'della Liberta', 'Villaricca', 2);

DROP TABLE IF EXISTS campo;
CREATE TABLE campo (
    idCampo INT AUTO_INCREMENT,
    tipoTerreno VARCHAR(20) NOT NULL,
    centrosportivo VARCHAR(20) NOT NULL,
    FOREIGN KEY (centrosportivo) REFERENCES centrosportivo(nome),
    PRIMARY KEY (idCampo)
) AUTO_INCREMENT=0;

INSERT INTO campo(tipoTerreno, centrosportivo) VALUES ('Erba sintetica', 'Campi in fiore');
INSERT INTO campo(tipoTerreno, centrosportivo) VALUES ('Terra', 'Campi in fiore');
INSERT INTO campo(tipoTerreno, centrosportivo) VALUES ('Erba sintetica', 'Campi in fiore');

DROP TABLE IF EXISTS spogliatoio;
CREATE TABLE spogliatoio (
    idSpogliatoio INT AUTO_INCREMENT,
    nDocce INT NOT NULL,
    centrosportivo VARCHAR(20) NOT NULL,
    FOREIGN KEY (centrosportivo) REFERENCES centrosportivo(nome),
    PRIMARY KEY (idSpogliatoio)
) AUTO_INCREMENT = 0;

INSERT INTO spogliatoio(nDocce, centrosportivo) VALUES (3, 'Campi in fiore');
INSERT INTO spogliatoio(nDocce, centrosportivo) VALUES (5, 'Campi in fiore');
INSERT INTO spogliatoio(nDocce, centrosportivo) VALUES (2, 'Campi in fiore');

DROP TABLE IF EXISTS presidente;
CREATE TABLE presidente (
    codFis CHAR(16) NOT NULL,
    nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    PRIMARY KEY (codFis)
);

INSERT INTO presidente VALUES ('MTANDR99T31F839O', 'Andrea', 'Amato');

DROP TABLE IF EXISTS numTelefono;
CREATE TABLE numTelefono(
    numero CHAR(10) NOT NULL,
    presidente CHAR(16) NOT NULL,
    FOREIGN KEY (presidente) REFERENCES presidente(codFis)
);

INSERT INTO numTelefono VALUES ('3400000000', 'MTANDR99T31F839O');

DROP TABLE IF EXISTS dipendenti;
CREATE TABLE dipendenti(
    codFis CHAR(16) NOT NULL,
    nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    presidente CHAR(16) NOT NULL,
    PRIMARY KEY (codFis),
    FOREIGN KEY (presidente) REFERENCES presidente(codFis)
);

INSERT INTO dipendenti VALUES ('CNTGSP63A01F839A', 'Giuseppe', 'Conte', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('ZNGNCL63A01F839A', 'Nicola', 'Zingaretti', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('DMILGU63A01F839A', 'Luigi', 'Di Maio', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('RNZMTT63A01F839A', 'Matteo', 'Renzi', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('SLVMTT63A01F839A', 'Matteo', 'Salvini', 'MTANDR99T31F839O');

DROP TABLE IF EXISTS allenatore;
CREATE TABLE allenatore (
    dipendente CHAR(16) NOT NULL,
    FOREIGN KEY (dipendente) REFERENCES dipendenti(codFis)
);

INSERT INTO allenatore VALUES ('ZNGNCL63A01F839A');
INSERT INTO allenatore VALUES ('DMILGU63A01F839A');
INSERT INTO allenatore VALUES ('RNZMTT63A01F839A');

DROP TABLE IF EXISTS squadra;
CREATE TABLE squadra (
    nome VARCHAR(20) NOT NULL,
    etabambini SMALLINT NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    allenatore CHAR(16) NOT NULL,
    PRIMARY KEY (nome),
    FOREIGN KEY (allenatore) REFERENCES allenatore(dipendente)
);

INSERT INTO squadra VALUES ('Pulcini Recca', 5, 'Pulcini', 'ZNGNCL63A01F839A');
INSERT INTO squadra VALUES ('Juniores Recca', 12, 'Juniores', 'ZNGNCL63A01F839A');
INSERT INTO squadra VALUES ('Primavera Recca', 15, 'Primavera', 'DMILGU63A01F839A');
INSERT INTO squadra VALUES ('Primavera Recca B', 15, 'Primavera', 'DMILGU63A01F839A');
INSERT INTO squadra VALUES ('Juniores Recca B', 15, 'Juniores', 'RNZMTT63A01F839A');

DROP TABLE IF EXISTS torneo;
CREATE TABLE torneo (
    nome VARCHAR(20) NOT NULL,
    edizione SMALLINT NOT NULL,
    premio VARCHAR(200) NOT NULL,
    squadra VARCHAR(20) NOT NULL,
    FOREIGN KEY (squadra) REFERENCES squadra(nome),
    PRIMARY KEY (nome, edizione)
);

INSERT INTO torneo VALUES('Torneo Natalizio', 2019, 'Coppa in lega', 'Juniores Recca B');

DROP TABLE IF EXISTS scuolacalcio;
CREATE TABLE scuolacalcio(
    partitaIva CHAR(11) NOT NULL,
    fondazione SMALLINT NOT NULL,
    nome VARCHAR(20) NOT NULL,
    nFattureEmesse SMALLINT NOT NULL,
    centrosportivo VARCHAR(20) NOT NULL,
    presidente CHAR(16) NOT NULL,
    PRIMARY KEY (partitaIva),
    FOREIGN KEY (centrosportivo) REFERENCES centrosportivo(nome),
    FOREIGN KEY (presidente) REFERENCES presidente(codFis)
);

INSERT INTO scuolacalcio VALUES ('77253160640', 2000, 'Recca', 0, 'Campi in fiore', 'MTANDR99T31F839O');

DROP TABLE IF EXISTS bambino;
CREATE TABLE bambino(
    codFis CHAR(16) NOT NULL,
    nome VARCHAR(40) NOT NULL,
    eta SMALLINT NOT NULL,
    scuolacalcio CHAR(11) NOT NULL,
    squadra VARCHAR(20) NOT NULL,
    PRIMARY KEY (codFis),
    FOREIGN KEY (squadra) REFERENCES squadra(nome),
    FOREIGN KEY (scuolacalcio) REFERENCES scuolacalcio(partitaIva)
);

INSERT INTO bambino VALUES('CCCNTN05A01F839T', 'Antonio Cacciapuoti', 15, '77253160640', 'Primavera Recca');
INSERT INTO bambino VALUES('CCCGBR05A01F839T', 'Gabriele Cacciapuoti', 15, '77253160640', 'Primavera Recca B');
INSERT INTO bambino VALUES('CNTLSN09A01F839X', 'Alessandro Canto', 11, '77253160640', 'Juniores Recca');
INSERT INTO bambino VALUES('CCLMTT07A01F839X', 'Mattia Cicala', 13, '77253160640', 'Juniores Recca B');
INSERT INTO bambino VALUES('BGIPCA15A01F839X', 'Biagio Pace', 5, '77253160640', 'Pulcini Recca');
INSERT INTO bambino VALUES('SRGCLD05A01F839X', 'Claudio Sergio', 15, '77253160640', 'Primavera Recca');
INSERT INTO bambino VALUES('BRCRTR15A01F839X', 'Arturo Brachetti', 5, '77253160640', 'Pulcini Recca');
INSERT INTO bambino VALUES('BGLLDA14A01F839X', 'Aldo Baglio', 6, '77253160640', 'Pulcini Recca');
INSERT INTO bambino VALUES('PRTGCM06A01F839X', 'Giacomo Poretti', 14, '77253160640', 'Primavera Recca B');
INSERT INTO bambino VALUES('STRGVN08A01F839X', 'Giovanni Storti', 12, '77253160640', 'Juniores Recca B');

DROP TABLE IF EXISTS retta;
CREATE TABLE retta (
    progressivo SMALLINT AUTO_INCREMENT,
    importo SMALLINT NOT NULL,
    bambino CHAR(16) NOT NULL,
    PRIMARY KEY (progressivo),
    FOREIGN KEY (bambino) REFERENCES bambino(codFis)
) AUTO_INCREMENT = 0;

INSERT INTO retta(importo, bambino) VALUES (500, 'CCCNTN05A01F839T');
INSERT INTO retta(importo, bambino) VALUES (500, 'CCCGBR05A01F839T');
INSERT INTO retta(importo, bambino) VALUES (500, 'CNTLSN09A01F839X');
INSERT INTO retta(importo, bambino) VALUES (500, 'CCLMTT07A01F839X');
INSERT INTO retta(importo, bambino) VALUES (500, 'BGIPCA15A01F839X');
INSERT INTO retta(importo, bambino) VALUES (500, 'SRGCLD05A01F839X');
INSERT INTO retta(importo, bambino) VALUES (500, 'BRCRTR15A01F839X');
INSERT INTO retta(importo, bambino) VALUES (500, 'BGLLDA14A01F839X');
INSERT INTO retta(importo, bambino) VALUES (50, 'PRTGCM06A01F839X');
INSERT INTO retta(importo, bambino) VALUES (50, 'PRTGCM06A01F839X');
INSERT INTO retta(importo, bambino) VALUES (50, 'STRGVN08A01F839X');
INSERT INTO retta(importo, bambino) VALUES (50, 'STRGVN08A01F839X');

DROP TABLE IF EXISTS mensile;
CREATE TABLE mensile (
    retta SMALLINT NOT NULL,
    FOREIGN KEY (retta) REFERENCES retta(progressivo)
);

INSERT INTO mensile VALUES (9);
INSERT INTO mensile VALUES (10);
INSERT INTO mensile VALUES (11);
INSERT INTO mensile VALUES (12);

DROP TABLE IF EXISTS annuale;
CREATE TABLE annuale (
    retta SMALLINT NOT NULL,
    sconto SMALLINT NOT NULL,
    FOREIGN KEY (retta) REFERENCES retta(progressivo)
);

INSERT INTO annuale VALUES (1, 50);
INSERT INTO annuale VALUES (2, 50);
INSERT INTO annuale VALUES (3, 50);
INSERT INTO annuale VALUES (4, 50);
INSERT INTO annuale VALUES (5, 50);
INSERT INTO annuale VALUES (6, 50);
INSERT INTO annuale VALUES (7, 50);
INSERT INTO annuale VALUES (8, 50);


DROP TABLE IF EXISTS medicoSportivo;
CREATE TABLE medicoSportivo (
    nome VARCHAR(30),
    codFis CHAR(16) NOT NULL,
    PRIMARY KEY (codFis)
);

INSERT INTO medicoSportivo VALUES ('Antonio Giametta','GCMPCA63A01F839A');
INSERT INTO medicoSportivo VALUES ('Angela Finocchiaro','FNCNGL63A01F839A');

DROP TABLE IF EXISTS visita;
CREATE TABLE visita (
    medico CHAR(16) NOT NULL,
    bambino CHAR(16) NOT NULL,
    FOREIGN KEY (medico) REFERENCES medicoSportivo(codFis),
    FOREIGN KEY (bambino) REFERENCES bambino(codFis)
);

INSERT INTO visita VALUES ('GCMPCA63A01F839A', 'CNTLSN09A01F839X');
INSERT INTO visita VALUES ('GCMPCA63A01F839A', 'STRGVN08A01F839X');
INSERT INTO visita VALUES ('FNCNGL63A01F839A', 'BGIPCA15A01F839X');

DROP TABLE IF EXISTS fisioterapista;
CREATE TABLE fisioterapista (
    specialita VARCHAR(20),
    dipendente CHAR(16) NOT NULL,
    FOREIGN KEY (dipendente) REFERENCES dipendenti(codFis)
);

INSERT INTO fisioterapista VALUES ('Podologia', 'CNTGSP63A01F839A');

DROP TABLE IF EXISTS preparatoreAtletico;
CREATE TABLE preparatoreAtletico (
    allenaPortieri Bool NOT NULL,
    dipendente CHAR(16) NOT NULL,
    FOREIGN KEY (dipendente) REFERENCES dipendenti(codFis)
);

INSERT INTO preparatoreAtletico VALUES (0, 'SLVMTT63A01F839A');

DROP TABLE IF EXISTS allena;

DROP TABLE IF EXISTS prepara;
CREATE TABLE prepara (
    preparatoreAtletico CHAR(16) NOT NULL,
    squadra VARCHAR(20) NOT NULL,
    FOREIGN KEY (squadra) REFERENCES squadra(nome),
    FOREIGN KEY (preparatoreAtletico) REFERENCES preparatoreAtletico(dipendente)
);

INSERT INTO prepara VALUES ('SLVMTT63A01F839A', 'Juniores Recca B');

DROP TABLE IF EXISTS cura;
CREATE TABLE cura(
    bambino CHAR(16) NOT NULL,
    fisioterapista CHAR(16) NOT NULL,
    FOREIGN KEY (bambino) REFERENCES bambino(codFis),
    FOREIGN KEY (fisioterapista) REFERENCES fisioterapista(dipendente)
);

INSERT INTO cura VALUES ('BRCRTR15A01F839X', 'CNTGSP63A01F839A');

DROP TABLE IF EXISTS collabora;
CREATE TABLE collabora (
    scuolacalcio CHAR(11) NOT NULL,
    medico CHAR(16) NOT NULL,
    FOREIGN KEY (scuolacalcio) REFERENCES scuolacalcio(partitaIva),
    FOREIGN KEY (medico) REFERENCES medicoSportivo(codFis)
);

INSERT INTO collabora VALUES ('77253160640', 'GCMPCA63A01F839A');
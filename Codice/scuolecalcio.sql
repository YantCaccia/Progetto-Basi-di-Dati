DROP DATABASE IF EXISTS scuolecalciodb;
CREATE DATABASE scuolecalciodb;
USE scuolecalciodb;
DROP USER IF EXISTS 'userscuolecalcio'@'%';
CREATE USER 'userscuolecalcio'@'%' IDENTIFIED BY 'psw';
GRANT ALL ON scuolecalciodb.* TO 'userscuolecalcio'@'%';

DROP TABLE IF EXISTS centrosportivo;
CREATE TABLE centrosportivo (
    nome VARCHAR(20) NOT NULL,
    via VARCHAR(20) NOT NULL,
    citta VARCHAR(20) NOT NULL,
    civico SMALLINT NOT NULL,
    PRIMARY KEY (nome)
);

DROP TABLE IF EXISTS campo;
CREATE TABLE campo (
    idCampo INT AUTO_INCREMENT,
    tipoTerreno VARCHAR(20) NOT NULL,
    centrosportivo VARCHAR(20) NOT NULL,
    FOREIGN KEY (centrosportivo) REFERENCES centrosportivo(nome) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (idCampo, centrosportivo)
) AUTO_INCREMENT=0;

DROP TABLE IF EXISTS spogliatoio;
CREATE TABLE spogliatoio (
    idSpogliatoio INT AUTO_INCREMENT,
    nDocce INT NOT NULL,
    centrosportivo VARCHAR(20) NOT NULL,
    FOREIGN KEY (centrosportivo) REFERENCES centrosportivo(nome) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (idSpogliatoio, centrosportivo)
) AUTO_INCREMENT = 0;

DROP TABLE IF EXISTS presidente;
CREATE TABLE presidente (
    codFis CHAR(16) NOT NULL,
    nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    PRIMARY KEY (codFis)
);

DROP TABLE IF EXISTS numTelefono;
CREATE TABLE numTelefono(
    numero CHAR(10) NOT NULL,
    presidente CHAR(16) NOT NULL,
    FOREIGN KEY (presidente) REFERENCES presidente(codFis) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS dipendenti;
CREATE TABLE dipendenti(
    codFis CHAR(16) NOT NULL,
    nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    presidente CHAR(16) NOT NULL,
    PRIMARY KEY (codFis),
    FOREIGN KEY (presidente) REFERENCES presidente(codFis) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS allenatore;
CREATE TABLE allenatore (
    dipendente CHAR(16) NOT NULL,
    FOREIGN KEY (dipendente) REFERENCES dipendenti(codFis) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS squadra;
CREATE TABLE squadra (
    nome VARCHAR(20) NOT NULL,
    etabambini SMALLINT NOT NULL,
    categoria VARCHAR(20) NOT NULL,
    allenatore CHAR(16) NOT NULL,
    PRIMARY KEY (nome),
    FOREIGN KEY (allenatore) REFERENCES allenatore(dipendente) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS torneo;
CREATE TABLE torneo (
    nome VARCHAR(20) NOT NULL,
    edizione SMALLINT NOT NULL,
    premio VARCHAR(200) NOT NULL,
    squadra VARCHAR(20) NOT NULL,
    FOREIGN KEY (squadra) REFERENCES squadra(nome) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (nome, edizione)
);

DROP TABLE IF EXISTS scuolacalcio;
CREATE TABLE scuolacalcio(
    partitaIva CHAR(11) NOT NULL,
    fondazione SMALLINT NOT NULL,
    nome VARCHAR(20) NOT NULL,
    nFattureEmesse SMALLINT NOT NULL,
    centrosportivo VARCHAR(20) NOT NULL,
    presidente CHAR(16) NOT NULL,
    PRIMARY KEY (partitaIva),
    FOREIGN KEY (centrosportivo) REFERENCES centrosportivo(nome) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (presidente) REFERENCES presidente(codFis) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS bambino;
CREATE TABLE bambino(
    codFis CHAR(16) NOT NULL,
    nome VARCHAR(40) NOT NULL,
    eta SMALLINT NOT NULL,
    scuolacalcio CHAR(11) NOT NULL,
    squadra VARCHAR(20) NOT NULL,
    PRIMARY KEY (codFis),
    FOREIGN KEY (squadra) REFERENCES squadra(nome) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (scuolacalcio) REFERENCES scuolacalcio(partitaIva) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS retta;
CREATE TABLE retta (
    progressivo SMALLINT AUTO_INCREMENT,
    importo SMALLINT NOT NULL,
    bambino CHAR(16) NOT NULL,
    PRIMARY KEY (progressivo),
    FOREIGN KEY (bambino) REFERENCES bambino(codFis) ON DELETE CASCADE ON UPDATE CASCADE
) AUTO_INCREMENT = 0;

DROP TABLE IF EXISTS mensile;
CREATE TABLE mensile (
    retta SMALLINT NOT NULL,
    FOREIGN KEY (retta) REFERENCES retta(progressivo) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS annuale;
CREATE TABLE annuale (
    retta SMALLINT NOT NULL,
    sconto SMALLINT NOT NULL,
    FOREIGN KEY (retta) REFERENCES retta(progressivo) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS medicoSportivo;
CREATE TABLE medicoSportivo (
    nome VARCHAR(30),
    codFis CHAR(16) NOT NULL,
    PRIMARY KEY (codFis)
);

DROP TABLE IF EXISTS visita;
CREATE TABLE visita (
    medico CHAR(16) NOT NULL,
    bambino CHAR(16) NOT NULL,
    FOREIGN KEY (medico) REFERENCES medicoSportivo(codFis) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (bambino) REFERENCES bambino(codFis) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS fisioterapista;
CREATE TABLE fisioterapista (
    specialita VARCHAR(20),
    dipendente CHAR(16) NOT NULL,
    FOREIGN KEY (dipendente) REFERENCES dipendenti(codFis) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS preparatoreAtletico;
CREATE TABLE preparatoreAtletico (
    allenaPortieri Bool NOT NULL,
    dipendente CHAR(16) NOT NULL,
    FOREIGN KEY (dipendente) REFERENCES dipendenti(codFis) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS prepara;
CREATE TABLE prepara (
    preparatoreAtletico CHAR(16) NOT NULL,
    squadra VARCHAR(20) NOT NULL,
    FOREIGN KEY (squadra) REFERENCES squadra(nome) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (preparatoreAtletico) REFERENCES preparatoreAtletico(dipendente) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS cura;
CREATE TABLE cura(
    bambino CHAR(16) NOT NULL,
    fisioterapista CHAR(16) NOT NULL,
    FOREIGN KEY (bambino) REFERENCES bambino(codFis) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fisioterapista) REFERENCES fisioterapista(dipendente) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS collabora;
CREATE TABLE collabora (
    scuolacalcio CHAR(11) NOT NULL,
    medico CHAR(16) NOT NULL,
    FOREIGN KEY (scuolacalcio) REFERENCES scuolacalcio(partitaIva) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (medico) REFERENCES medicoSportivo(codFis) ON DELETE CASCADE ON UPDATE CASCADE
);
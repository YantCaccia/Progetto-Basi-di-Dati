INSERT INTO centrosportivo VALUES ('Campi in fiore', 'della Liberta', 'Villaricca', 2);

INSERT INTO campo(tipoTerreno, centrosportivo) VALUES ('Erba sintetica', 'Campi in fiore');
INSERT INTO campo(tipoTerreno, centrosportivo) VALUES ('Terra', 'Campi in fiore');
INSERT INTO campo(tipoTerreno, centrosportivo) VALUES ('Erba sintetica', 'Campi in fiore');

INSERT INTO spogliatoio(nDocce, centrosportivo) VALUES (3, 'Campi in fiore');
INSERT INTO spogliatoio(nDocce, centrosportivo) VALUES (5, 'Campi in fiore');
INSERT INTO spogliatoio(nDocce, centrosportivo) VALUES (2, 'Campi in fiore');

INSERT INTO presidente VALUES ('MTANDR99T31F839O', 'Andrea', 'Amato');

INSERT INTO numTelefono VALUES ('3400000000', 'MTANDR99T31F839O');

INSERT INTO dipendenti VALUES ('CNTGSP63A01F839A', 'Giuseppe', 'Conte', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('ZNGNCL63A01F839A', 'Nicola', 'Zingaretti', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('DMILGU63A01F839A', 'Luigi', 'Di Maio', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('RNZMTT63A01F839A', 'Matteo', 'Renzi', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('SLVMTT63A01F839A', 'Matteo', 'Salvini', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('CRBLRN63A01F839A', 'Lorenzo', 'Cherubini', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('JRDMCH63A01F839A', 'Micheal', 'Jordan', 'MTANDR99T31F839O');
INSERT INTO dipendenti VALUES ('HMLLWE63A01F839A', 'Lewis', 'Hamilton', 'MTANDR99T31F839O');

INSERT INTO allenatore VALUES ('ZNGNCL63A01F839A');
INSERT INTO allenatore VALUES ('DMILGU63A01F839A');
INSERT INTO allenatore VALUES ('RNZMTT63A01F839A');

INSERT INTO squadra VALUES ('Pulcini Recca', 5, 'Pulcini', 'ZNGNCL63A01F839A');
INSERT INTO squadra VALUES ('Juniores Recca', 12, 'Juniores', 'ZNGNCL63A01F839A');
INSERT INTO squadra VALUES ('Primavera Recca', 15, 'Primavera', 'DMILGU63A01F839A');
INSERT INTO squadra VALUES ('Primavera Recca B', 15, 'Primavera', 'DMILGU63A01F839A');
INSERT INTO squadra VALUES ('Juniores Recca B', 15, 'Juniores', 'RNZMTT63A01F839A');

INSERT INTO torneo VALUES('Torneo Natalizio', 2019, 'Coppa in lega', 'Juniores Recca B');
INSERT INTO torneo VALUES('Torneo Natalizio', 2020, 'Coppa in lega', 'Primavera Recca');
INSERT INTO torneo VALUES('Torneo Natalizio', 2018, 'Coppa in lega', 'Juniores Recca');
INSERT INTO torneo VALUES('Torneo di Primavera', 2018, 'Targa argento', 'Pulcini Recca');
INSERT INTO torneo VALUES('Torneo di Primavera', 2019, 'Targa argento', 'Primavera Recca B');
INSERT INTO torneo VALUES('Torneo di Primavera', 2020, 'Targa argento', 'Juniores Recca B');

INSERT INTO scuolacalcio VALUES ('77253160640', 2000, 'Recca', 0, 'Campi in fiore', 'MTANDR99T31F839O');

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

INSERT INTO mensile VALUES (9);
INSERT INTO mensile VALUES (10);
INSERT INTO mensile VALUES (11);
INSERT INTO mensile VALUES (12);

INSERT INTO annuale VALUES (1, 50);
INSERT INTO annuale VALUES (2, 50);
INSERT INTO annuale VALUES (3, 50);
INSERT INTO annuale VALUES (4, 50);
INSERT INTO annuale VALUES (5, 50);
INSERT INTO annuale VALUES (6, 50);
INSERT INTO annuale VALUES (7, 50);
INSERT INTO annuale VALUES (8, 50);

INSERT INTO medicoSportivo VALUES ('Antonio Giametta','GCMPCA63A01F839A');
INSERT INTO medicoSportivo VALUES ('Angela Finocchiaro','FNCNGL63A01F839A');

INSERT INTO visita VALUES ('GCMPCA63A01F839A', 'CNTLSN09A01F839X');
INSERT INTO visita VALUES ('GCMPCA63A01F839A', 'STRGVN08A01F839X');
INSERT INTO visita VALUES ('FNCNGL63A01F839A', 'BGIPCA15A01F839X');

INSERT INTO fisioterapista VALUES ('Podologia', 'CNTGSP63A01F839A');

INSERT INTO preparatoreAtletico VALUES (0, 'SLVMTT63A01F839A');
INSERT INTO preparatoreAtletico VALUES (0, 'CRBLRN63A01F839A');
INSERT INTO preparatoreAtletico VALUES (0, 'JRDMCH63A01F839A');
INSERT INTO preparatoreAtletico VALUES (1, 'HMLLWE63A01F839A');

INSERT INTO prepara VALUES ('SLVMTT63A01F839A', 'Juniores Recca B');
INSERT INTO prepara VALUES ('CRBLRN63A01F839A', 'Juniores Recca B');
INSERT INTO prepara VALUES ('JRDMCH63A01F839A', 'Pulcini Recca');
INSERT INTO prepara VALUES ('HMLLWE63A01F839A', 'Primavera Recca');

INSERT INTO cura VALUES ('BRCRTR15A01F839X', 'CNTGSP63A01F839A');

INSERT INTO collabora VALUES ('77253160640', 'GCMPCA63A01F839A');
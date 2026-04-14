
CREATE DATABASE BDCitas;
USE BDCitas;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE Profesional(
    docPro varchar(11) not null,
    nomPro varchar(30) not null,
    apePro varchar(30) not null,
    emaPro varchar(60) not null,
    tarPro varchar(10) not null,
    rolPro enum("Psicologo", "Medico", "Enfermero", "Practicante"),
    telPro varchar(11),
    PRIMARY KEY (docPro) 
);

CREATE TABLE Grupo(
    codGru varchar(10) not null,
    nomGru varchar(50) not null,
    fecIniGru date,
    fecFinGru date,
    ambGru varchar(10) not null,
    insLidGru varchar(50) not null,
    jorGru varchar(10),
    PRIMARY KEY (codGru)
);

CREATE TABLE Aprendiz(
    docApr varchar(11) not null,
    codGruApr varchar(10) not null,
    nomApr varchar(30) not null,
    apeApr varchar(30) not null,
    emaApr varchar(60) not null,
    epsApr varchar(60) not null,
    telProApr varchar(11) not null,
    conEmeApre varchar(100) not null,
    telConEmeApre varchar(11) not null,
    PRIMARY KEY (docApr),
    CONSTRAINT fk_grupo_aprendiz FOREIGN KEY (codGruApr) REFERENCES Grupo (codGru)
);

CREATE TABLE Agendamiento(
    codAge int auto_increment,
    docAprAge varchar(11) not null,
    docProAge varchar(11) not null,
    fecAge varchar(10) not null, 
    horAge varchar(7) not null, 
    motAge varchar(100),
    PRIMARY KEY (codAge),
    CONSTRAINT fk_aprendiz_age FOREIGN KEY (docAprAge) REFERENCES Aprendiz (docApr),
    CONSTRAINT fk_profesional_age FOREIGN KEY (docProAge) REFERENCES Profesional (docPro)
);

CREATE TABLE historialClinica( 
    codHisCli int auto_increment,
    docAprHisCli varchar(11) not null,
    codAgeCitHisCli int not null, 
    desCitHisCli varchar(300) not null,
    obsCitHisCli varchar(300) not null,
    PRIMARY KEY (codHisCli),
    CONSTRAINT fk_aprendiz_his FOREIGN KEY (docAprHisCli) REFERENCES Aprendiz (docApr),
    CONSTRAINT fk_agendamiento_his FOREIGN KEY (codAgeCitHisCli) REFERENCES Agendamiento (codAge) 
);

SET FOREIGN_KEY_CHECKS = 1;

-- Consulta de verificación (Unificada a BDCitas)
SELECT table_name, constraint_name, referenced_table_name
FROM information_schema.key_column_usage
WHERE table_schema = 'BDCitas' AND referenced_table_name IS NOT NULL 
ORDER BY table_name;

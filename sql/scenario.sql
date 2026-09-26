CREATE TABLE vsm.scenario
(
    mnemo varchar(16) NOT NULL,
    title varchar(128) NOT NULL,
    icon bytea,
    path varchar(256),
    upload_time timestamp without time zone,
    summary varchar(4096) NOT NULL,
    created timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated timestamp without time zone,
    status varchar(1) DEFAULT 'D',
    CONSTRAINT scenario_pkey PRIMARY KEY (mnemo)
);

COMMENT ON TABLE vsm.scenario
    IS 'Сценарии стресовых ситуаций';
    
COMMENT ON COLUMN vsm.scenario.mnemo
    IS 'Мнемокод (короткое имя) сценария';
    
COMMENT ON COLUMN vsm.scenario.title
    IS 'Название сценария';
    
COMMENT ON COLUMN vsm.scenario.icon
    IS 'Иконка сценария';    
    
COMMENT ON COLUMN vsm.scenario.path
    IS 'URI файла';    
    
COMMENT ON COLUMN vsm.scenario.upload_time
    IS 'Временная метка загрузки или удаления файла';    

COMMENT ON COLUMN vsm.scenario.summary
    IS 'Краткое описание сценария';    

COMMENT ON COLUMN vsm.scenario.created
    IS 'Время создания сценария';    

COMMENT ON COLUMN vsm.scenario.updated
    IS 'Время изменения сценария';    

COMMENT ON COLUMN vsm.scenario.status
    IS 'Статус сценария E (Enable), D (Disable)';    
    
INSERT INTO vsm.scenario (mnemo, title, summary) VALUES ('SCENA_1', 'Сцена 1', 'Тестова сцена 1');
INSERT INTO vsm.scenario (mnemo, title, summary) VALUES ('SCENA_2', 'Сцена 2', 'Тестова сцена 2');
INSERT INTO vsm.scenario (mnemo, title, summary) VALUES ('SCENA_3', 'Сцена 3', 'Тестова сцена 3');
    
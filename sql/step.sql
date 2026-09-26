CREATE TABLE vsm.step
(
    mnemo varchar(12) NOT NULL,
    step varchar(12) NOT NULL,
    title varchar(128) NOT NULL,
    icon bytea,
    path varchar(256),
    upload_time timestamp without time zone,
    description text,
    CONSTRAINT step_pkey PRIMARY KEY (mnemo, step),
    CONSTRAINT step_scenario_fkey FOREIGN KEY (mnemo)
        REFERENCES vsm.scenario (mnemo) MATCH FULL
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

COMMENT ON TABLE vsm.step
    IS 'Этапы (шаги) сценария';
    
COMMENT ON COLUMN vsm.step.mnemo
    IS 'Мнемокод (короткое имя) сценария';
    
COMMENT ON COLUMN vsm.step.step
    IS 'Код шага (короткое обозначение) сценария';

COMMENT ON COLUMN vsm.scenario.path
    IS 'URI файла';    

COMMENT ON COLUMN vsm.step.icon
    IS 'Иконка шага сценария';    
    
COMMENT ON COLUMN vsm.scenario.filename
    IS 'Оригинальное имя файла';    
    
COMMENT ON COLUMN vsm.scenario.upload_time
    IS 'Временная метка загрузки файла';    

COMMENT ON COLUMN vsm.step.description
    IS 'Описание шага сценария'; 
    
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_1', 'Шаг 1 тестовой сцена 1', 'Описание шага 1 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_2', 'Шаг 2 тестовой сцена 1', 'Описание шага 2 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_3', 'Шаг 3 тестовой сцена 1', 'Описание шага 3 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_4', 'Шаг 4 тестовой сцена 1', 'Описание шага 4 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_5', 'Шаг 5 тестовой сцена 1', 'Описание шага 5 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_6', 'Шаг 6 тестовой сцена 1', 'Описание шага 6 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_7', 'Шаг 7 тестовой сцена 1', 'Описание шага 7 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_8', 'Шаг 8 тестовой сцена 1', 'Описание шага 8 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_9', 'Шаг 9 тестовой сцена 1', 'Описание шага 9 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_10', 'Шаг 10 тестовой сцена 1', 'Описание шага 10 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_11', 'Шаг 11 тестовой сцена 1', 'Описание шага 11 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_12', 'Шаг 12 тестовой сцена 1', 'Описание шага 12 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_13', 'Шаг 13 тестовой сцена 1', 'Описание шага 13 тестовой сцена 1');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_1', 'STEP_14', 'Шаг 14 тестовой сцена 1', 'Описание шага 14 тестовой сцена 1');

INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_1', 'Шаг 1 тестовой сцена 2', 'Описание шага 1 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_2', 'Шаг 2 тестовой сцена 2', 'Описание шага 2 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_3', 'Шаг 3 тестовой сцена 2', 'Описание шага 3 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_4', 'Шаг 4 тестовой сцена 2', 'Описание шага 4 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_5', 'Шаг 5 тестовой сцена 2', 'Описание шага 5 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_6', 'Шаг 6 тестовой сцена 2', 'Описание шага 6 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_7', 'Шаг 7 тестовой сцена 2', 'Описание шага 7 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_8', 'Шаг 8 тестовой сцена 2', 'Описание шага 8 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_9', 'Шаг 9 тестовой сцена 2', 'Описание шага 9 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_10', 'Шаг 10 тестовой сцена 2', 'Описание шага 10 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_11', 'Шаг 11 тестовой сцена 2', 'Описание шага 11 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_12', 'Шаг 12 тестовой сцена 2', 'Описание шага 12 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_13', 'Шаг 13 тестовой сцена 2', 'Описание шага 13 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_14', 'Шаг 14 тестовой сцена 2', 'Описание шага 14 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_15', 'Шаг 15 тестовой сцена 2', 'Описание шага 15 тестовой сцена 2');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_2', 'STEP_16', 'Шаг 16 тестовой сцена 2', 'Описание шага 16 тестовой сцена 2');

INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_1', 'Шаг 1 тестовой сцена 3', 'Описание шага 1 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_2', 'Шаг 2 тестовой сцена 3', 'Описание шага 2 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_3', 'Шаг 3 тестовой сцена 3', 'Описание шага 3 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_4', 'Шаг 4 тестовой сцена 3', 'Описание шага 4 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_5', 'Шаг 5 тестовой сцена 3', 'Описание шага 5 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_6', 'Шаг 6 тестовой сцена 3', 'Описание шага 6 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_7', 'Шаг 7 тестовой сцена 3', 'Описание шага 7 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_8', 'Шаг 8 тестовой сцена 3', 'Описание шага 8 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_9', 'Шаг 9 тестовой сцена 3', 'Описание шага 9 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_10', 'Шаг 10 тестовой сцена 3', 'Описание шага 10 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_11', 'Шаг 11 тестовой сцена 3', 'Описание шага 11 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_12', 'Шаг 12 тестовой сцена 3', 'Описание шага 12 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_13', 'Шаг 13 тестовой сцена 3', 'Описание шага 13 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_14', 'Шаг 14 тестовой сцена 3', 'Описание шага 14 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_15', 'Шаг 15 тестовой сцена 3', 'Описание шага 15 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_16', 'Шаг 16 тестовой сцена 3', 'Описание шага 16 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_17', 'Шаг 17 тестовой сцена 3', 'Описание шага 17 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_18', 'Шаг 18 тестовой сцена 3', 'Описание шага 18 тестовой сцена 3');
INSERT INTO vsm.step (mnemo, step, title, description) VALUES ('SCENA_3', 'STEP_19', 'Шаг 19 тестовой сцена 3', 'Описание шага 19 тестовой сцена 3');


CREATE TABLE vsm.choice (
    mnemo             varchar(12) NOT NULL,
    step              varchar(12) NOT NULL,
    choice            varchar(12) NOT NULL,
    next_step         varchar(12) NOT NULL,
    body              text NOT NULL,
    d_loyalty         smallint NOT NULL,
    d_safety          smallint NOT NULL,
    competence        varchar(12) --NOT NULL,
    competence_gain   numeric(6, 2) NOT NULL CHECK (competence_gain >= 0),
    is_preferred      boolean NOT NULL DEFAULT false,
    hint_text         text NOT NULL,
    better_text       text NOT NULL,
    is_timeout        boolean NOT NULL DEFAULT false,
    CONSTRAINT choice_pkey PRIMARY KEY (mnemo, step, choice),
    CONSTRAINT cohoice_step_fkey FOREIGN KEY (mnemo, step)
        REFERENCES vsm.step (mnemo, step) MATCH FULL
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT choice_next_step_fkey FOREIGN KEY (mnemo, next_step)
        REFERENCES vsm.step (mnemo, step) MATCH FULL
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE vsm.choice
    IS 'Выбор действия на этапе (шаге) сценария';
    
COMMENT ON COLUMN vsm.choice.mnemo
    IS 'Мнемокод (короткое имя) сценария';

COMMENT ON COLUMN vsm.choice.step
    IS 'Код шага (короткое обозначение) сценария';

COMMENT ON COLUMN vsm.choice.choice
    IS 'Вариант (короткое обозначение) выбора действия на шаге сценария';

COMMENT ON COLUMN vsm.choice.next_step
    IS 'Код следующего шага (короткое обозначение) сценария';
    
--                             mnemo      step      choice      next_step body                               d_loyalty
--                                                                                                              d_safety
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_1', 'CHOICE_1', 'STEP_2', 'Выбор 1 шага 1 тестовой сцена 1', 1, 2, 'Описание выбора 1 шага 1 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_1', 'CHOICE_2', 'STEP_3', 'Выбор 2 шага 1 тестовой сцена 1', 2, 3, 'Описание выбора 2 шага 1 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_1', 'CHOICE_3', 'STEP_4', 'Выбор 3 шага 1 тестовой сцена 1', 3, 4, 'Описание выбора 3 шага 1 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_2', 'CHOICE_1', 'STEP_5', 'Выбор 1 шага 2 тестовой сцена 1', 1, 2, 'Описание выбора 1 шага 2 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_2', 'CHOICE_2', 'STEP_6', 'Выбор 2 шага 2 тестовой сцена 1', 2, 3, 'Описание выбора 2 шага 2 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_3', 'CHOICE_1', 'STEP_12', 'Выбор 1 шага 3 тестовой сцена 1', 1, 2, 'Описание выбора 1 шага 3 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_3', 'CHOICE_2', 'STEP_13', 'Выбор 2 шага 3 тестовой сцена 1', 2, 3, 'Описание выбора 2 шага 3 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_3', 'CHOICE_3', 'STEP_14', 'Выбор 3 шага 3 тестовой сцена 1', 3, 4, 'Описание выбора 3 шага 3 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_4', 'CHOICE_1', 'STEP_9', 'Выбор 1 шага 4 тестовой сцена 1', 1, 2, 'Описание выбора 1 шага 4 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_4', 'CHOICE_2', 'STEP_10', 'Выбор 2 шага 4 тестовой сцена 1', 2, 3, 'Описание выбора 3 шага 4 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_5', 'CHOICE_1', 'STEP_6', 'Выбор 1 шага 5 тестовой сцена 1', 1, 2, 'Описание выбора 1 шага 5 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_5', 'CHOICE_2', 'STEP_7', 'Выбор 2 шага 5 тестовой сцена 1', 2, 3, 'Описание выбора 2 шага 5 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_5', 'CHOICE_3', 'STEP_8', 'Выбор 3 шага 5 тестовой сцена 1', 3, 4, 'Описание выбора 3 шага 5 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_5', 'CHOICE_4', 'STEP_14', 'Выбор 4 шага 5 тестовой сцена 1', 4, 5, 'Описание выбора 4 шага 5 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_6', 'CHOICE_1', 'STEP_9', 'Выбор 1 шага 6 тестовой сцена 1', 1, 2, 'Описание выбора 1 шага 6 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_6', 'CHOICE_2', 'STEP_10', 'Выбор 2 шага 6 тестовой сцена 1', 2, 3, 'Описание выбора 2 шага 6 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_6', 'CHOICE_3', 'STEP_11', 'Выбор 3 шага 6 тестовой сцена 1', 3, 4, 'Описание выбора 3 шага 6 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_7', 'CHOICE_1', 'STEP_12', 'Выбор 1 шага 7 тестовой сцена 1', 1, 2, 'Описание выбора 4 шага 5 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_7', 'CHOICE_2', 'STEP_14', 'Выбор 2 шага 7 тестовой сцена 1', 2, 3, 'Описание выбора 4 шага 5 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_8', 'CHOICE_1', 'STEP_13', 'Выбор 1 шага 8 тестовой сцена 1', 1, 2, 'Описание выбора 4 шага 5 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_8', 'CHOICE_2', 'STEP_14', 'Выбор 2 шага 8 тестовой сцена 1', 2, 3, 'Описание выбора 4 шага 5 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_9', 'CHOICE_1', 'STEP_10', 'Выбор 1 шага 9 тестовой сцена 1', 1, 2, 'Описание выбора 4 шага 5 тестовой сцена 1');
INSERT INTO vsm.choice VALUES ('SCENA_1', 'STEP_9', 'CHOICE_2', 'STEP_11', 'Выбор 2 шага 9 тестовой сцена 1', 2, 3, 'Описание выбора 4 шага 5 тестовой сцена 1');

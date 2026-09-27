-- Для H2 удалить MATCH FULL в описании вторичных ключей. H2 не поддерживает эту конструкцию  

CREATE TABLE vsm.block (
    id character varying(32) NOT NULL,
    title character varying(32),
    type character varying(32),
    timelimitsec integer,
    text character varying(4094),
    timetext character varying(4094),
    dloyalty integer,
    dsafety integer,
    CONSTRAINT block_pk PRIMARY KEY (id)
);

CREATE TABLE vsm.bl_setflag (
    bl_id character varying(32) NOT NULL,
    flag character varying(32) NOT NULL,
    CONSTRAINT bl_setflag_pk PRIMARY KEY (bl_id, flag),
    CONSTRAINT bl_setflag_block_fk FOREIGN KEY (bl_id)
            REFERENCES vsm.block (id) MATCH FULL
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE vsm.audiotimeline (
    bl_id character varying(32) NOT NULL,
    assetid character varying(32) NOT NULL,
    offsetms integer,
    volume numeric(4,2),
    loop boolean DEFAULT false,
    stop character varying(32),
    CONSTRAINT audiotimeline_pk PRIMARY KEY (bl_id, assetid),
    CONSTRAINT audiotimeline_block_fk FOREIGN KEY (bl_id)
            REFERENCES vsm.block (id) MATCH FULL
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE vsm.choice (
    bl_id character varying(32) NOT NULL,
    id character varying(32) NOT NULL,
    text character varying(1024),
    dloyalty integer,
    dsafery integer,
    CONSTRAINT choice_pk PRIMARY KEY (bl_id, id),
    CONSTRAINT choice_block_fk FOREIGN KEY (bl_id)
            REFERENCES vsm.block (id) MATCH FULL
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE vsm.setflag (
    bl_id character varying(32) NOT NULL,
    ch_id character varying(32) NOT NULL,
    flag character varying(32) NOT NULL,
    CONSTRAINT setflag_pk PRIMARY KEY (bl_id, ch_id, flag),
    CONSTRAINT setflag_choice_fk FOREIGN KEY (bl_id, ch_id)
            REFERENCES vsm.choice (bl_id, id) MATCH FULL
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE vsm.run (
    id character varying(32) NOT NULL,
    title character varying(32) NOT NULL,
    submission boolean DEFAULT false,
    jurypool boolean DEFAULT false,
    startnode character varying(32),
    CONSTRAINT run_pk PRIMARY KEY (id)
);

CREATE TABLE vsm.node (
    rn_id character varying(32) NOT NULL,
    id character varying(32) NOT NULL,
    template character varying(32) NOT NULL,
    CONSTRAINT node_pk PRIMARY KEY (rn_id, id),
	CONSTRAINT node_run_fk FOREIGN KEY (rn_id)
            REFERENCES vsm.run (id) MATCH FULL
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

CREATE TABLE vsm.next (
    rn_id character varying(32) NOT NULL,
    nd_id character varying(32) NOT NULL,
    choice character varying(32) NOT NULL,
    next character varying(32),
    CONSTRAINT next_pk PRIMARY KEY (rn_id, nd_id, choice),    CONSTRAINT next_node_fk FOREIGN KEY (rn_id, nd_id)
            REFERENCES vsm.node (rn_id, id) MATCH FULL
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
);

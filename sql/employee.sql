CREATE TABLE IF NOT EXISTS vsm.employee
(
    tnumber varchar(10) NOT NULL,
    fname varchar(32) NOT NULL,
    lname varchar(64) NOT NULL,
    pname varchar(32) NOT NULL,
    telephone varchar(12) NOT NULL,
    email varchar(64) NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (tnumber)
);

COMMENT ON TABLE vsm.employee
    IS 'Сотрудники';
    
COMMENT ON COLUMN vsm.employee.tnumber
    IS 'Табельный номер';

COMMENT ON COLUMN vsm.employee.fname
    IS 'Имя';

COMMENT ON COLUMN vsm.employee.lname
    IS 'Фамилия';

COMMENT ON COLUMN vsm.employee.pname
    IS 'Отчество';
    
COMMENT ON COLUMN vsm.employee.telephone
    IS 'Телефон';

COMMENT ON COLUMN vsm.employee.email
    IS 'Электронная почта';
    
INSERT INTO vsm.employee VALUES ('11110001', 'Имя1', 'Фамилия1', 'Отчество1', '+79999990001', 'user1@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110002', 'Имя2', 'Фамилия2', 'Отчество2', '+79999990002', 'user2@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110003', 'Имя3', 'Фамилия3', 'Отчество3', '+79999990003', 'user3@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110004', 'Имя4', 'Фамилия4', 'Отчество4', '+79999990004', 'user4@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110005', 'Имя5', 'Фамилия5', 'Отчество5', '+79999990005', 'user5@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110006', 'Имя6', 'Фамилия6', 'Отчество6', '+79999990006', 'user6@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110007', 'Имя7', 'Фамилия7', 'Отчество7', '+79999990007', 'user7@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110008', 'Имя8', 'Фамилия8', 'Отчество8', '+79999990008', 'user8@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110009', 'Имя9', 'Фамилия9', 'Отчество9', '+79999990009', 'user9@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110010', 'Имя10', 'Фамилия10', 'Отчество10', '+79999990010', 'user10@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110011', 'Имя11', 'Фамилия11', 'Отчество11', '+79999990011', 'user11@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110012', 'Имя12', 'Фамилия12', 'Отчество12', '+79999990012', 'user12@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110013', 'Имя13', 'Фамилия13', 'Отчество13', '+79999990013', 'user13@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110014', 'Имя14', 'Фамилия14', 'Отчество14', '+79999990014', 'user14@domain.ru');    
INSERT INTO vsm.employee VALUES ('11110015', 'Имя15', 'Фамилия15', 'Отчество15', '+79999990015', 'user15@domain.ru');    
    
        
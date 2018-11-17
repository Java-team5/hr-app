
CREATE SCHEMA `hr_app` ;

use hr_app;

create table if not exists ROLE
( `ID` bigint not null AUTO_INCREMENT
, `NAME` varchar(255) not null
, primary key (`ID`)
, unique key (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=1001;

insert into role (`NAME`) values ('Administrator');

insert into role (`NAME`) values ('Developer');

insert into role (`NAME`) values ('Manager');

create table if not exists USER
( `ID` bigint not null AUTO_INCREMENT
, `EMAIL` varchar(100) not null
, `PASSWORD` varchar(255) not null
, `NAME` varchar(255) not null
, `SURNAME` varchar(255)
, `USER_STATE` enum ('Active','Archive')
, primary key (`ID`)
, unique key (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=101;

insert into user (`EMAIL`, `NAME`, `SURNAME`, `PASSWORD`,`USER_STATE`) values ('admin@sj.com','Administrator','','111111','Active');

insert into user (`EMAIL`, `NAME`, `SURNAME`, `PASSWORD`,`USER_STATE`) values ('developer@sj.com','Developer','','111111','Active');

insert into user (`EMAIL`, `NAME`, `SURNAME`, `PASSWORD`,`USER_STATE`) values ('manager@sj.com','Manager','','111111','Active');

create table if not exists USER_ROLES
( `USER_ID` bigint not null
, `ROLE_ID` bigint not null
, unique key (`USER_ID`,`ROLE_ID`)
);

alter table USER_ROLES add constraint userRolesFK_userId foreign key (USER_ID) references `USER`(ID) on delete cascade on update cascade;

alter table USER_ROLES add constraint userRolesFK_roleId foreign key (ROLE_ID) references `ROLE`(ID) on delete cascade on update cascade;

insert into USER_ROLES (`USER_ID`, `ROLE_ID`) values ((select `ID` from USER where EMAIL = 'admin@sj.com'), (select `ID` from ROLE where NAME = 'Administrator'));

insert into USER_ROLES (`USER_ID`, `ROLE_ID`) values ((select `ID` from USER where EMAIL = 'developer@sj.com'), (select `ID` from ROLE where NAME = 'Developer'));

insert into USER_ROLES (`USER_ID`, `ROLE_ID`) values ((select `ID` from USER where EMAIL = 'manager@sj.com'), (select `ID` from ROLE where NAME = 'Developer'));


create table if not exists CANDIDATE_STATE
( `NAME` varchar(255) not null
, primary key (`NAME`)
) ENGINE=InnoDB;

insert into CANDIDATE_STATE (`NAME`) values ('Активен');

insert into CANDIDATE_STATE (`NAME`) values ('В архиве');


create table if not exists SUITABLE_STATE
( `NAME` varchar(255) not null
, primary key (`NAME`)
) ENGINE=InnoDB;

insert into SUITABLE_STATE (`NAME`) values ('Соответствует');

insert into SUITABLE_STATE (`NAME`) values ('Не соответствует');


create table if not exists FEEDBACK_STATE
( `NAME` varchar(255) not null
, primary key (`NAME`)
) ENGINE=InnoDB;

insert into FEEDBACK_STATE (`NAME`) values ('Принять');

insert into FEEDBACK_STATE (`NAME`) values ('Отказать');


create table if not exists SKILL
( `NAME` varchar(255) not null
, primary key (`NAME`)
) ENGINE=InnoDB;

insert into SKILL (`NAME`) values ('Java');

insert into SKILL (`NAME`) values ('JS');

insert into SKILL (`NAME`) values ('RDB');

insert into SKILL (`NAME`) values ('.Net');

insert into SKILL (`NAME`) values ('C++');

insert into SKILL (`NAME`) values ('PL/SQL');


create table if not exists REQUIREMENT
( `NAME` varchar(255) not null
, primary key (`NAME`)
) ENGINE=InnoDB;

insert into REQUIREMENT (`NAME`) values ('Стрессоустойчивость');

insert into REQUIREMENT (`NAME`) values ('Внятная речь');

insert into REQUIREMENT (`NAME`) values ('Способность принимать решения');

insert into REQUIREMENT (`NAME`) values ('Высокие нравственные устои');

insert into REQUIREMENT (`NAME`) values ('Широкий кругозор');

insert into REQUIREMENT (`NAME`) values ('Частые командировки');

insert into REQUIREMENT (`NAME`) values ('Способность работать overtime');

insert into REQUIREMENT (`NAME`) values ('Умение ходить ногами');

insert into REQUIREMENT (`NAME`) values ('Умение видеть глазами');

insert into REQUIREMENT (`NAME`) values ('Умение думать головой');


create table if not exists RESPONSIBILITY
( `NAME` varchar(255) not null
, primary key (`NAME`)
) ENGINE=InnoDB;

insert into RESPONSIBILITY (`NAME`) values ('Работа в качестве менеджера проекта');

insert into RESPONSIBILITY (`NAME`) values ('Организационная и бумажная работа');

insert into RESPONSIBILITY (`NAME`) values ('Функции администратора БД');


create table if not exists CANDIDATE
( `ID` bigint not null AUTO_INCREMENT
, `NAME` varchar(255) not null
, `SURNAME` varchar(255)
, `BIRTHDAY` date
, `SALARY` decimal(10,2) check(`SALARY`>=0)
, `CANDIDATE_STATE` varchar(255)
, primary key (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=201;

alter table CANDIDATE add constraint candidateFK_stacandidatete foreign key (CANDIDATE_STATE) references `CANDIDATE_STATE`(NAME) on delete cascade on update cascade;

insert into CANDIDATE (`NAME`, `SURNAME`, `BIRTHDAY`, `SALARY`, `CANDIDATE_STATE`) values ('Пупкин','Вася','1995-12-27', 650, 'Активен');

insert into CANDIDATE (`NAME`, `SURNAME`, `BIRTHDAY`, `SALARY`, `CANDIDATE_STATE`) values ('Зайцев','Стёпа','1997-02-20', 450.55, 'Активен');


create table if not exists CONTACT_DETAILS
( `CANDIDATE_ID` bigint not null
, `CONTACT_TYPE` enum ('Mobile phone','E-mail','Address') not null
, `CONTACT_DETAILS` varchar(1000) not null
, index `contactDetails_I01` (`CANDIDATE_ID`)
, index `contactDetails_I02` (`CONTACT_TYPE`)
);

alter table CONTACT_DETAILS add constraint contactDetailsFK_candidate foreign key (CANDIDATE_ID) references `CANDIDATE`(ID) on delete cascade on update cascade;

insert into CONTACT_DETAILS (`CANDIDATE_ID`, `CONTACT_TYPE`, `CONTACT_DETAILS`)
values (
  (select `ID` from CANDIDATE where NAME = 'Пупкин')
, 'Mobile phone'
, '+375 29 123 45 78'
);

insert into CONTACT_DETAILS (`CANDIDATE_ID`, `CONTACT_TYPE`, `CONTACT_DETAILS`)
values (
  (select `ID` from CANDIDATE where NAME = 'Пупкин')
, 'E-mail'
, 'poupkine@sj.com'
);

insert into CONTACT_DETAILS (`CANDIDATE_ID`, `CONTACT_TYPE`, `CONTACT_DETAILS`)
values (
  (select `ID` from CANDIDATE where NAME = 'Зайцев')
, 'Mobile phone'
, '+375 44 999 88 11'
);

insert into CONTACT_DETAILS (`CANDIDATE_ID`, `CONTACT_TYPE`, `CONTACT_DETAILS`)
values (
  (select `ID` from CANDIDATE where NAME = 'Зайцев')
, 'E-mail'
, 'zaytsev@sj.com'
);

insert into CONTACT_DETAILS (`CANDIDATE_ID`, `CONTACT_TYPE`, `CONTACT_DETAILS`)
values (
  (select `ID` from CANDIDATE where NAME = 'Зайцев')
, 'Address'
, 'Минск, пл. Парижской Коммуны, 1'
);


create table if not exists CANDIDATE_EXPERIENCE
( `ID` bigint not null AUTO_INCREMENT
, `CANDIDATE_ID` bigint not null
, `DATE_FROM` date not null
, `DATE_TO` date not null
, `JOB_DESCRIPTION` varchar(4000)
, `JOB_POSITION` varchar(1000)
, `COMPANY_NAME` varchar(1000)
, primary key (`ID`)
, index `contactDetails_I01` (`CANDIDATE_ID`)
, index `contactDetails_I02` (`DATE_FROM`)
) ENGINE=InnoDB AUTO_INCREMENT=501;

alter table CANDIDATE_EXPERIENCE add constraint candidateExperienceFK_candidate foreign key (CANDIDATE_ID) references `CANDIDATE`(ID)  on delete cascade on update cascade;

create table if not exists CANDIDATE_RESPONSIBILITY
(`CANDIDATE_EXPERIENCE_ID` bigint not null
, `RESPONSIBILITY` varchar(255) not null
);

alter table CANDIDATE_RESPONSIBILITY add constraint candidateResponsibilityFK_candidateExperience foreign key (CANDIDATE_EXPERIENCE_ID) references `CANDIDATE_EXPERIENCE`(ID) on delete cascade on update cascade;

alter table CANDIDATE_RESPONSIBILITY add constraint candidateResponsibilityFK_responsibility foreign key (RESPONSIBILITY) references `RESPONSIBILITY`(NAME) on delete cascade on update cascade;

create table if not exists ATTACHMENT
( `CANDIDATE_ID` bigint not null
, `FILE_PATH` varchar(1000)
, `ATTACHMENT_TYPE` enum ('CV', 'Cover Letter','Photo') not null
);

alter table ATTACHMENT add constraint attachmentFK_candidate foreign key (CANDIDATE_ID) references `CANDIDATE`(ID) on delete cascade on update cascade;


create table if not exists CANDIDATE_COMPETENCE
( `CANDIDATE_ID` bigint not null
, `SKILL` varchar(255) not null
, unique key (`CANDIDATE_ID`,`SKILL`)
);

alter table CANDIDATE_COMPETENCE add constraint candidateCompetenceFK_candidate foreign key (CANDIDATE_ID) references `CANDIDATE`(ID)  on delete cascade on update cascade;

alter table CANDIDATE_COMPETENCE add constraint candidateCompetenceFK_skill foreign key (SKILL) references `SKILL`(NAME) on delete cascade on update cascade;


create table if not exists VACANCY
( `ID` bigint not null AUTO_INCREMENT
, `POSITION` varchar(1000)
, `DEVELOPER_ID` bigint not null
, `SALARY_FROM` decimal(10,2) not null check(`SALARY_FROM`>=0)
, `SALARY_TO` decimal(10,2) not null check(`SALARY_TO`>=0)
, `VACANCY_STATE` enum ('Active','Archive') not null
, `EXPERIENCE_YEARS_REQUIRE` decimal (10,2) not null check(`EXPERIENCE_YEARS_REQUIRE` > 0)
, primary key (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2001;

alter table VACANCY add constraint vacancyFK_developer foreign key (DEVELOPER_ID) references `USER`(ID) on delete cascade on update cascade;

create table if not exists VACANCY_REQUIREMENT
( `VACANCY_ID` bigint not null
, `REQUIREMENT` varchar(255) not null
, unique key (`VACANCY_ID`,`REQUIREMENT`)
);

alter table VACANCY_REQUIREMENT add constraint vacancyRequirementFK_vacancy foreign key (VACANCY_ID) references `VACANCY`(ID) on delete cascade on update cascade;

alter table VACANCY_REQUIREMENT add constraint vacancyRequirementFK_requirement foreign key (REQUIREMENT) references `REQUIREMENT`(NAME) on delete cascade on update cascade;


create table if not exists VACANCY_CANDIDATES
( `VACANCY_ID` bigint not null
, `CANDIDATE_ID` bigint not null
, `SUITABLE_STATE` varchar(255)
, `REASON` varchar(1000)
, unique key (`VACANCY_ID`,`CANDIDATE_ID`)
);

alter table VACANCY_CANDIDATES add constraint vacancyCandidatesFK_vacancy foreign key (VACANCY_ID) references `VACANCY`(ID) on delete cascade on update cascade;

alter table VACANCY_CANDIDATES add constraint vacancyCandidatesFK_candidate foreign key (CANDIDATE_ID) references `CANDIDATE`(ID)  on delete cascade on update cascade;

alter table VACANCY_CANDIDATES add constraint vacancyCandidatesFK_state foreign key (SUITABLE_STATE) references `SUITABLE_STATE`(NAME) on delete cascade on update cascade;

create table if not exists INTERVIEW
( `ID` bigint not null AUTO_INCREMENT
, `VACANCY_ID` bigint not null
, `PLAN_DATE` datetime not null
, `CANDIDATE_ID` bigint not null
, `FACT_DATE` datetime
, unique key (`VACANCY_ID`,`CANDIDATE_ID`,`PLAN_DATE`)
, primary key (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3001;

alter table INTERVIEW add constraint interviewFK_vacancy foreign key (VACANCY_ID) references `VACANCY`(ID) on delete cascade on update cascade;

alter table INTERVIEW add constraint interviewFK_candidate foreign key (CANDIDATE_ID) references `CANDIDATE`(ID)  on delete cascade on update cascade;


create table if not exists CANDIDATE_FEEDBACK
( `ID` bigint not null AUTO_INCREMENT
, `CANDIDATE_ID` bigint not null
, `INTERVIEW_ID` bigint 
, `INTERVIEWER_ID` bigint not null
, `FEEDBACK_STATE` varchar(255)
, `FEEDBACK_TEXT` varchar(4000)
, primary key (`ID`)
, unique key (`CANDIDATE_ID`,`INTERVIEWER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4001;

alter table CANDIDATE_FEEDBACK add constraint candidateFeedbackFK_candidate foreign key (CANDIDATE_ID) references `CANDIDATE`(ID)  on delete cascade on update cascade;

alter table CANDIDATE_FEEDBACK add constraint candidateFeedbackFK_interview foreign key (INTERVIEW_ID) references `INTERVIEW`(ID) on delete cascade on update cascade;

alter table CANDIDATE_FEEDBACK add constraint candidateFeedbackFK_interviewer foreign key (INTERVIEWER_ID) references `USER`(ID) on delete cascade on update cascade;

alter table CANDIDATE_FEEDBACK add constraint candidateFeedbackFK_state foreign key (FEEDBACK_STATE) references `FEEDBACK_STATE`(NAME) on delete cascade on update cascade;

create table if not exists FEEDBACK_DETAILS
( `CANDIDATE_FEEDBACK_ID` bigint not null
, `REQUIREMENT` varchar(255) not null
, `VERIFY_STATE` varchar(255)
, unique key (`CANDIDATE_FEEDBACK_ID`,`REQUIREMENT`)
);

alter table FEEDBACK_DETAILS add constraint feedbackDetailsFK_interview foreign key (CANDIDATE_FEEDBACK_ID) references `CANDIDATE_FEEDBACK`(ID) on delete cascade on update cascade;

alter table FEEDBACK_DETAILS add constraint feedbackDetailsFK_state foreign key (VERIFY_STATE) references `SUITABLE_STATE`(NAME) on delete cascade on update cascade;

alter table FEEDBACK_DETAILS add constraint feedbackDetailsFK_requirement foreign key (REQUIREMENT) references `REQUIREMENT`(NAME) on delete cascade on update cascade;


commit;
create sequence hibernate_sequence start 1 increment 1;

create table academic_discipline
(
    id              int8 not null,
    discipline_name varchar(255),
    primary key (id)
);

create table criteria_score
(
    id                int8 not null,
    score             int4 not null,
    criteria_score_id int8,
    primary key (id)
);

create table position
(
    id            int8 not null,
    position_name varchar(255),
    primary key (id)
);

create table schedule
(
    id              int8 not null,
    visiting_week   varchar(255),
    visited_user_id int8,
    visiter_user_id int8,
    primary key (id)
);

create table schedule_status
(
    schedule_id int8 not null,
    status      varchar(255)
);

create table study_group
(
    id         int8 not null,
    group_name varchar(255),
    primary key (id)
);

create table user_roles
(
    user_id int8 not null,
    roles   varchar(255)
);

create table usr
(
    id       int8    not null,
    active   boolean not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

create table visiting
(
    id                     int8 not null,
    purpose_of_the_lesson  varchar(255),
    date                   timestamp,
    lesson_topic           varchar(9999),
    number_of_students     int4 not null,
    purpose_of_the_visit   varchar(9999),
    academic_discipline_id int8,
    position_id            int8,
    schedule_id            int8,
    study_group_id         int8,
    primary key (id)
);

create table visiting_criteria
(
    id                   int8 not null,
    criteria_name        varchar(255),
    score                int4 not null,
    value_of_one_point   varchar(9999),
    value_of_three_point varchar(9999),
    value_of_two_point   varchar(9999),
    criteria_id          int8,
    primary key (id)
);

alter table if exists criteria_score add constraint FKlov23qgx7t7jq86rrvnxd1dd7 foreign key (criteria_score_id) references visiting;
alter table if exists schedule add constraint FK7t8e7sy54wattiem91hufa681 foreign key (visited_user_id) references usr;
alter table if exists schedule add constraint FKs7jbi4bjta0km0dswu6pijexs foreign key (visiter_user_id) references usr;
alter table if exists schedule_status add constraint FKpdr3ff1sl0r0kr8poja4bmeep foreign key (schedule_id) references schedule;
alter table if exists user_roles add constraint FKg6agnwreityp2vf23bm2jgjm3 foreign key (user_id) references usr;
alter table if exists visiting add constraint FKk7kelh13kx6f8qlqeeajlknxv foreign key (academic_discipline_id) references academic_discipline;
alter table if exists visiting add constraint FKbh6sfou4yn415402d4pshsben foreign key (position_id) references position;
alter table if exists visiting add constraint FKmb6ihpib32v6jou4bg82smmis foreign key (schedule_id) references schedule;
alter table if exists visiting add constraint FKmvfkano7958hay5o7mhoy1ywf foreign key (study_group_id) references study_group;
alter table if exists visiting_criteria add constraint FKpdbbt1wo15m2loi0jrx9fhiqf foreign key (criteria_id) references visiting;
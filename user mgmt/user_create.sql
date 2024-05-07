create table User_group_mapping_table
(
    User_group_id bigint not null auto_increment,
    group_id      bigint,
    User_id       bigint,
    primary key (User_group_id)
) engine=InnoDB;
create table User_table
(
    User_id            bigint not null auto_increment,
    create_date        datetime,
    first_name         varchar(255),
    last_modified_date datetime,
    last_name          varchar(255),
    User_name          varchar(255),
    primary key (User_id)
) engine=InnoDB;
alter table User_group_mapping_table
    add constraint UK5rhnboke9fcw3ok6rfpvfeda4 unique (User_id, group_id);
alter table User_table
    add constraint UK_p50irg6kthpq3f33xu9r1kw4x unique (User_name);

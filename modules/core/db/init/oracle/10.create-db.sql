-- begin DBCHS_HELP_CONTEXT
create table DBCHS_HELP_CONTEXT (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50),
    --
    SCREEN_ID varchar2(255),
    COMPONENT_ID varchar2(255),
    --
    primary key (ID)
)^
-- end DBCHS_HELP_CONTEXT
-- begin DBCHS_HELPTEXT
create table DBCHS_HELPTEXT (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50),
    --
    TEXT clob not null,
    HELP_CONTEXT_ID varchar2(32),
    CATEGORY_ID varchar2(32) not null,
    --
    primary key (ID)
)^
-- end DBCHS_HELPTEXT
-- begin DBCHS_HELPTEXT_CATEGORY
create table DBCHS_HELPTEXT_CATEGORY (
    ID varchar2(32),
    VERSION number(10) not null,
    CREATE_TS timestamp,
    CREATED_BY varchar2(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar2(50),
    DELETE_TS timestamp,
    DELETED_BY varchar2(50),
    --
    NAME varchar2(255) not null,
    CODE varchar2(255) not null,
    CONTEXT_INDEPENDENT char(1) not null,
    --
    primary key (ID)
)^
-- end DBCHS_HELPTEXT_CATEGORY

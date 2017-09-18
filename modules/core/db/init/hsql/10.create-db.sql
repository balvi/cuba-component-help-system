-- begin DBCHS_HELP_CONTEXT
create table DBCHS_HELP_CONTEXT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SCREEN_ID varchar(255),
    COMPONENT_ID varchar(255),
    --
    primary key (ID)
)^
-- end DBCHS_HELP_CONTEXT
-- begin DBCHS_HELPTEXT
create table DBCHS_HELPTEXT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TEXT longvarchar not null,
    HELP_CONTEXT_ID varchar(36) not null,
    CATEGORY_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end DBCHS_HELPTEXT
-- begin DBCHS_HELPTEXT_CATEGORY
create table DBCHS_HELPTEXT_CATEGORY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    CODE varchar(255) not null,
    CONTEXT_INDEPENDENT boolean not null,
    --
    primary key (ID)
)^
-- end DBCHS_HELPTEXT_CATEGORY

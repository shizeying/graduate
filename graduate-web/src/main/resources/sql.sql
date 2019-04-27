create table department (
  "id"  integer(11)  primary key,
  "name" varchar(32) DEFAULT NULL,
  "parentId" integer(11) DEFAULT NULL,
  "depPath" varchar(255) DEFAULT NULL,
  "enabled"  boolean default true ,
  "isParent" boolean default false 
);
create table employee (
  "id" integer(11) primary key,
  "name" varchar(10) default null,
  "gender" varchar(4) default null,
  "birthday" date default null,
  "idCard" char(18) default null,
  "wedlock" varchar(8) default null,
  "nationId" integer(8) default null,
  "nativePlace" varchar(20) default null,
  "politicId" integer(8) default null,
  "email" varchar(30) default null,
  "phone" varchar(11) default null,
  "address" varchar(64) default null,
  "departmentId" integer(11) default null,
  "jobLevelId" integer(11)default null,
  "posId" integer(11) default null,
  "engageForm" varchar(8) default null,
  "tiptopDegree" varchar(20) default null,
  "specialty" varchar(32) default null ,
  "school" varchar(32) default null,
  "beginDate" date default null,
  "workState" varchar(8) default '在职',
  "workID" char(8) default null,
  "contractTerm" double default null,
  "conversionTime" date default null,
  "notWorkDate" date default null,
  "beginContract" date default null,
  "endContract" date default null,
  "workAge" integer(11) default null
);



create table empsalary (
 "id"  integer(11)  NOT NULL primary key,
  "eid" integer(11)  DEFAULT NULL,
  "sid" integer(11)  DEFAULT NULL
) ;
create table hr (
  "id" integer(11) primary key , 
  "name" varchar(32)  default null,
  "phone" char(11)  default null ,
  "telephone" varchar(16)  default null ,
  "address" varchar(64) default null,
  "enabled" BOOLEAN default TRUE,
  "username" varchar(255)  default null,
  "password" varchar(255)  default null,
  "userface" varchar(255)  default null,
  "remark" varchar(255)  default null
) ;
create table hr_role (
 "id"  integer(11)  primary key,
  "hrid" integer(11) DEFAULT NULL ,
  "rid" integer(11) DEFAULT NULL
) ;
create table joblevel (
 "id"  integer(11)  primary key,
  "name" varchar(32)  DEFAULT NULL,
  "titleLevel" varchar(20) DEFAULT NULL,
  "createDate" timestamp   DEFAULT NULL,
  "enabled" boolean default true
) ;
create table menu (
 "id"  Integer(11)  primary key,
  "url" Varchar(64) DEFAULT NULL,
  "path" Varchar(64) DEFAULT NULL,
  "component" varchar(64) DEFAULT NULL,
  "name" Varchar(64) DEFAULT NULL,
  "iconCls" Varchar(64) DEFAULT NULL,
  "keepAlive" Boolean DEFAULT NULL ,
  "requireAuth" Boolean DEFAULT NULL  ,
  "parentId" integer(11) DEFAULT NULL ,
  "enabled" Boolean default true
) ;

create table menu_role (
 "id"  integer(11)  primary key,
  "mid" integer(11) DEFAULT NULL ,
  "rid" integer(11) DEFAULT NULL
) ;

create table nation (
 "id"  integer(11)  primary key,
  "name" varchar(32)  DEFAULT NULL
) ;
create table politicsstatus (
 "id"  integer(11)  primary key,
  "name" varchar(32) DEFAULT NULL
) ;
create table position (
 "id"  integer(11)  PRIMARY KEY,
  "name" varchar(32) DEFAULT NULL,
  "createDate" timestamp ,
  "enabled" boolean default true
);
create table role (
 "id"  integer(11)  primary key,
  "name" varchar(64)  DEFAULT NULL,
  "nameZh" varchar(64) DEFAULT NULL
);
create table salary (
 "id"  integer(11)  primary key,
  "basicSalary" integer(11)  DEFAULT NULL,
  "bonus" integer(11)  DEFAULT NULL,
  "lunchSalary" integer(11) DEFAULT NULL,
  "trafficSalary" integer(11)  DEFAULT NULL,
  "allSalary" integer(11)  DEFAULT NULL,
  "pensionBase" integer(11)  DEFAULT NULL,
  "pensionPer" float  DEFAULT NULL,
  "createDate" timestamp NULL DEFAULT NULL ,
  "medicalBase" integer(11) DEFAULT NULL ,
  "medicalPer" float DEFAULT NULL  ,
  "accumulationFundBase" integer(11) DEFAULT NULL ,
  "accumulationFundPer" float DEFAULT NULL ,
  "name" varchar(32) DEFAULT NULL 
) ;

create table sysmsg (
  "id" integer(11)   primary key,
  "mid" integer(11) DEFAULT NULL,
  "type" integer(11) default 0,
  "hrid" integer(11) DEFAULT NULL,
  "state" integer(11) default 0
) ;

create table msgcontent (
  "id" integer(11)primary key,
  "title" varchar(64) DEFAULT NULL,
  "message" varchar(255) DEFAULT NULL,
  "createDate" timestamp DEFAULT CURRENT_TIMESTAMP
) ;

create table job_info(
 "id" integer(11) primary key ,
 "did" integer(11) DEFAULT NULL ,
 "info" varchar(233) DEFAULT NULL ,
 "create_date" timestamp DEFAULT NULL   
);

create table job_basic(
 "id" integer(11) primary key,
 "did" integer(11)  ,
 "name" varchar(233) DEFAULT NULL,
 "age" integer(11)  DEFAULT NULL,
 "gender" varchar(4) DEFAULT NULL,
 "phone"  varchar(11) DEFAULT NULL,
 "spare_phone" varchar(11) DEFAULT NULL,
 "interview_date" date  DEFAULT NULL
);
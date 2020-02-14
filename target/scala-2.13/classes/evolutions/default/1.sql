# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table additional_information (
  id                            bigint auto_increment not null,
  dificulty                     varchar(255),
  guests                        integer,
  price                         varchar(255),
  kal                           varchar(255),
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_additional_information primary key (id)
);

create table recipe (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  estimated_time                varchar(255),
  image_url                     varchar(255),
  how_to_make                   varchar(255),
  additional_information_id     bigint,
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint uq_recipe_additional_information_id unique (additional_information_id),
  constraint pk_recipe primary key (id)
);

alter table recipe add constraint fk_recipe_additional_information_id foreign key (additional_information_id) references additional_information (id) on delete restrict on update restrict;


# --- !Downs

alter table recipe drop constraint if exists fk_recipe_additional_information_id;

drop table if exists additional_information;

drop table if exists recipe;


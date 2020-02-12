# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table recipe (
  id                            bigint auto_increment not null,
  title                         varchar(255),
  estimated_time                varchar(255),
  image_url                     varchar(255),
  how_to_make                   varchar(255),
  version                       bigint not null,
  when_created                  timestamp not null,
  when_updated                  timestamp not null,
  constraint pk_recipe primary key (id)
);


# --- !Downs

drop table if exists recipe;


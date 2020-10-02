CREATE DATABASE agendamentoemaildb;

CREATE TABLE agendamentoemail(
  id        serial  primary key,
  email     text    not null,
  assunto   text    not null,
  mensagem  text    not null,
  agendado  text    not null
);
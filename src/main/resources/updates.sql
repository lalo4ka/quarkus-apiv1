ALTER TABLE public.productos ALTER COLUMN clave TYPE varchar(4) USING clave::varchar(4);
ALTER TABLE public.productos add column deleted boolean default false;


create sequence refresh_token_id_seq;

create table refresh_token(
id integer default nextval('refresh_token_id_seq') primary key,
token text,
expiry_date timestamp,
customer_id int  references cliente(id) 
);
ALTER TABLE public.contiene ALTER COLUMN cnt TYPE float4 USING cnt::float4;
ALTER TABLE public.contiene ALTER COLUMN cnt2 TYPE float4 USING cnt2::float4;
ALTER TABLE public.contiene ALTER COLUMN precio TYPE float4 USING precio::float4;
ALTER TABLE public.lista_producto ALTER COLUMN precio_anterior TYPE float4 USING precio_anterior::float4;
ALTER TABLE public.lista_producto ALTER COLUMN precio TYPE float4 USING precio::float4;
ALTER TABLE public.productos ALTER COLUMN clave TYPE varchar(4) USING clave::varchar(4);
ALTER TABLE public.productos add column deleted boolean default false;
ALTER TABLE public.cliente add column role text;


create sequence refresh_token_id_seq;

create table refresh_token(
id integer default nextval('refresh_token_id_seq') primary key,
token text,
expiry_date timestamp,
customer_id int  references cliente(id) 
);
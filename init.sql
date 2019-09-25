-- Drop table

-- DROP TABLE public.t_info;

CREATE TABLE public.t_info (
	id serial NOT NULL,
	name varchar(32) NULL,
	email varchar(64) NULL,
	"date" date NULL,
	title varchar(32) NULL,
	evaluate varchar(32) NULL,
	state varchar(32) NULL,
	CONSTRAINT t_info_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.t_info OWNER TO postgres;
GRANT ALL ON TABLE public.t_info TO postgres;


-- Drop table

-- DROP TABLE public.t_user;

CREATE TABLE public.t_user (
	id serial NOT NULL,
	username varchar(32) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT t_user_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.t_user OWNER TO postgres;
GRANT ALL ON TABLE public.t_user TO postgres;
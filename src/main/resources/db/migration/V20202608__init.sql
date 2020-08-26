CREATE TABLE public.albumauth (
  id serial,
  userid integer NOT NULL,
  albumid integer NOT NULL,
  CONSTRAINT albumauth_pkey PRIMARY KEY (id),
  CONSTRAINT albumauth_unique UNIQUE (userid, albumid)
)
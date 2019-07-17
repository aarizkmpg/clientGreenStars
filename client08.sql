--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: attribute; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE attribute (
    attribute_id bigint NOT NULL,
    description character varying(255),
    name character varying(255),
    product_id bigint
);


ALTER TABLE public.attribute OWNER TO postgres;

--
-- Name: attribute_aud; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE attribute_aud (
    attribute_id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    description character varying(255),
    name character varying(255),
    product_id bigint
);


ALTER TABLE public.attribute_aud OWNER TO postgres;

--
-- Name: company; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE company (
    company_id bigint NOT NULL,
    create_by character varying(255),
    create_dt timestamp without time zone,
    lastupd_by character varying(255),
    lastupd_dt timestamp without time zone,
    address character varying(255),
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.company OWNER TO postgres;

--
-- Name: company_aud; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE company_aud (
    company_id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    create_by character varying(255),
    create_dt timestamp without time zone,
    lastupd_by character varying(255),
    lastupd_dt timestamp without time zone,
    address character varying(255),
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.company_aud OWNER TO postgres;

--
-- Name: demo_product_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE demo_product_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.demo_product_seq OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE products (
    product_id bigint NOT NULL,
    create_by character varying(255),
    create_dt timestamp without time zone,
    lastupd_by character varying(255),
    lastupd_dt timestamp without time zone,
    description character varying(255),
    name character varying(255),
    unit_price bigint,
    company_id bigint
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_aud; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE products_aud (
    product_id bigint NOT NULL,
    rev integer NOT NULL,
    revtype smallint,
    create_by character varying(255),
    create_dt timestamp without time zone,
    lastupd_by character varying(255),
    lastupd_dt timestamp without time zone,
    description character varying(255),
    name character varying(255),
    unit_price bigint,
    company_id bigint
);


ALTER TABLE public.products_aud OWNER TO postgres;

--
-- Name: revinfo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE revinfo (
    revision_number integer NOT NULL,
    revision_timestamp bigint
);


ALTER TABLE public.revinfo OWNER TO postgres;

--
-- Data for Name: attribute; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY attribute (attribute_id, description, name, product_id) FROM stdin;
10	Can eat	Apple	100
11	Can eat	Banana	100
12	Can catch mouse	Cat	100
13	Can Play Games	Mouse	100
15	No use	KeyBoard	100
42	dsa	das	101
43	2	1	101
45	4	4	101
51	1	1	101
53	7	76	101
\.


--
-- Data for Name: attribute_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY attribute_aud (attribute_id, rev, revtype, description, name, product_id) FROM stdin;
10	14	0	Can eat	Apple	100
11	14	0	Can eat	Banana	100
12	14	0	Can catch mouse	Cat	100
13	14	0	Can Play Games	Mouse	100
15	16	0	No use	KeyBoard	100
10	16	1	Can eat	Apple	100
11	16	1	Can eat	Banana	100
12	16	1	Can catch mouse	Cat	100
13	16	1	Can Play Games	Mouse	100
10	17	1	Can eat	Apple	100
11	17	1	Can eat	Banana	100
12	17	1	Can catch mouse	Cat	100
13	17	1	Can Play Games	Mouse	100
15	17	1	No use	KeyBoard	100
10	18	1	Can eat	Apple	100
11	18	1	Can eat	Banana	100
12	18	1	Can catch mouse	Cat	100
13	18	1	Can Play Games	Mouse	100
15	18	1	No use	KeyBoard	100
10	19	1	Can eat	Apple	100
11	19	1	Can eat	Banana	100
12	19	1	Can catch mouse	Cat	100
13	19	1	Can Play Games	Mouse	100
15	19	1	No use	KeyBoard	100
42	44	0	dsa	das	101
43	44	0	2	1	101
45	46	0	4	4	101
42	46	1	dsa	das	101
43	46	1	2	1	101
47	48	0	5	5	101
42	48	1	dsa	das	101
43	48	1	2	1	101
45	48	1	4	4	101
47	49	2	\N	\N	\N
42	50	1	dsa	das	101
43	50	1	2	1	101
45	50	1	4	4	101
51	52	0	1	1	101
42	52	1	dsa	das	101
43	52	1	2	1	101
45	52	1	4	4	101
53	54	0	7	76	101
42	54	1	dsa	das	101
43	54	1	2	1	101
45	54	1	4	4	101
51	54	1	1	1	101
42	55	1	dsa	das	101
43	55	1	2	1	101
45	55	1	4	4	101
51	55	1	1	1	101
53	55	1	7	76	101
\.


--
-- Data for Name: company; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY company (company_id, create_by, create_dt, lastupd_by, lastupd_dt, address, description, name) FROM stdin;
3	admin1	2019-07-03 03:09:16.853	admin2	2019-07-03 03:09:16.853	Singapore 2	Video funs	YouTube
1	admin1	2019-07-03 03:08:32.13	admin2	2019-07-03 03:09:51.955	England~	Sharing Happy Life	FaceBook
8	admin1	2019-07-03 03:10:51.076	admin2	2019-07-09 10:19:12.888	Am2	APPLE	APPLE
40	admin1	2019-07-09 10:20:40.695	admin2	2019-07-09 10:20:40.695	new	new 	Test new 
\.


--
-- Data for Name: company_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY company_aud (company_id, rev, revtype, create_by, create_dt, lastupd_by, lastupd_dt, address, description, name) FROM stdin;
1	2	0	admin1	2019-07-03 03:08:32.13	admin2	2019-07-03 03:08:32.13	Singapore	Sharing Life	FaceBook
3	4	0	admin1	2019-07-03 03:09:16.853	admin2	2019-07-03 03:09:16.853	Singapore 2	Video funs	YouTube
1	5	1	admin	2019-07-03 03:09:27.397	admin2	2019-07-03 03:09:27.397	Singapore 3	Sharing Life??	FaceBook
1	6	1	admin	2019-07-03 03:09:42.038	admin2	2019-07-03 03:09:42.039	Singapore 3	Sharing Happy Life	FaceBook
1	7	1	admin	2019-07-03 03:09:51.95	admin2	2019-07-03 03:09:51.955	England~	Sharing Happy Life	FaceBook
8	9	0	admin1	2019-07-03 03:10:51.076	admin2	2019-07-03 03:10:51.076	Am	APPLE	APPLE
8	20	1	admin	2019-07-08 22:30:51.482	admin2	2019-07-08 22:30:51.488	Am1	APPLE	APPLE
8	21	1	admin	2019-07-08 22:34:16.525	admin2	2019-07-08 22:34:16.53	Am11	APPLE	APPLE
8	22	1	admin	2019-07-08 22:42:25.52	admin2	2019-07-08 22:42:25.526	Am	APPLE	APPLE
8	23	1	admin	2019-07-08 22:57:51.376	admin2	2019-07-08 22:57:51.381	Am7	APPLE	APPLE
8	24	1	admin	2019-07-08 23:24:36.694	admin2	2019-07-08 23:24:36.699	Am77	APPLE	APPLE
8	25	1	admin	2019-07-09 00:20:11.317	admin2	2019-07-09 00:20:11.326	Am	APPLE	APPLE
8	26	1	admin	2019-07-09 00:23:00.987	admin2	2019-07-09 00:23:00.993	Am1	APPLE	APPLE
8	27	1	admin	2019-07-09 00:25:56.569	admin2	2019-07-09 00:25:56.57	Am12	APPLE	APPLE
8	28	1	admin	2019-07-09 00:34:36.912	admin2	2019-07-09 00:34:36.92	Am11	APPLE	APPLE
8	29	1	admin	2019-07-09 00:42:14.051	admin2	2019-07-09 00:42:14.056	Am	APPLE	APPLE
8	30	1	admin	2019-07-09 00:46:46.323	admin2	2019-07-09 00:46:46.342	Am2	APPLE	APPLE
8	31	1	admin	2019-07-09 00:47:02.421	admin2	2019-07-09 00:47:02.423	Am27	APPLE	APPLE
8	32	1	admin	2019-07-09 00:47:20.42	admin2	2019-07-09 00:47:20.421	Am2	APPLE	APPLE
8	33	1	admin	2019-07-09 00:52:22.439	admin2	2019-07-09 00:52:22.443	Am22	APPLE	APPLE
8	34	1	admin	2019-07-09 00:52:35.517	admin2	2019-07-09 00:52:35.518	Am22	APPLE	APPLE
8	35	1	admin	2019-07-09 00:53:30.276	admin2	2019-07-09 00:53:30.281	Am2	APPLE	APPLE
8	36	1	admin	2019-07-09 00:53:56.382	admin2	2019-07-09 00:53:56.383	Am29	APPLE	APPLE
8	37	1	admin	2019-07-09 00:54:06.895	admin2	2019-07-09 00:54:06.896	Am299	APPLE	APPLE
8	38	1	admin	2019-07-09 10:19:12.884	admin2	2019-07-09 10:19:12.888	Am2	APPLE	APPLE
40	41	0	admin1	2019-07-09 10:20:40.695	admin2	2019-07-09 10:20:40.695	new	new 	Test new 
\.


--
-- Name: demo_product_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('demo_product_seq', 101, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 55, true);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY products (product_id, create_by, create_dt, lastupd_by, lastupd_dt, description, name, unit_price, company_id) FROM stdin;
100	admin	2019-07-03 03:11:44.867	admin	2019-07-03 04:16:36.864	Mac Book 2017	MacBook Pro	3406	8
101	admin	2019-07-09 10:46:48.953	admin	2019-07-09 03:37:44.875	123u7333	Mac 2	1237	8
\.


--
-- Data for Name: products_aud; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY products_aud (product_id, rev, revtype, create_by, create_dt, lastupd_by, lastupd_dt, description, name, unit_price, company_id) FROM stdin;
100	14	0	admin	2019-07-03 03:11:44.867	admin	2019-07-03 03:11:44.867	Mac Book	MacBook Pro	3400	8
100	16	1	admin	2019-07-03 03:12:08.948	admin	2019-07-03 03:12:08.948	Mac Book	MacBook Pro	3406	8
100	17	1	admin	2019-07-03 03:24:57.779	admin	2019-07-03 03:24:57.779	Mac Book 2017	MacBook Pro	3406	8
100	18	1	admin	2019-07-03 04:16:18.245	admin	2019-07-03 04:16:18.245	Mac Book 2017	MacBook Pro	340666	8
100	19	1	admin	2019-07-03 04:16:36.864	admin	2019-07-03 04:16:36.864	Mac Book 2017	MacBook Pro	3406	8
101	44	0	admin	2019-07-09 10:46:48.953	admin	2019-07-09 10:46:48.953	123	Mac 2	123	8
101	46	1	admin	2019-07-09 10:47:29.861	admin	2019-07-09 10:47:29.861	123	Mac 2	123	8
101	48	1	admin	2019-07-09 10:49:11.666	admin	2019-07-09 10:49:11.666	123	Mac 2	123	8
101	50	1	admin	2019-07-09 10:50:40.041	admin	2019-07-09 10:50:40.041	123u7	Mac 2	1237	8
101	52	1	admin	2019-07-09 11:10:39.086	admin	2019-07-09 11:10:39.086	123u7	Mac 2	1237	8
101	54	1	admin	2019-07-09 11:12:21.871	admin	2019-07-09 11:12:21.871	123u7	Mac 2	1237	8
101	55	1	admin	2019-07-09 03:37:44.875	admin	2019-07-09 03:37:44.875	123u7333	Mac 2	1237	8
\.


--
-- Data for Name: revinfo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY revinfo (revision_number, revision_timestamp) FROM stdin;
2	1562123312172
4	1562123356862
5	1562123367415
6	1562123382040
7	1562123391959
9	1562123451088
14	1562123504936
16	1562123528967
17	1562124297878
18	1562127378265
19	1562127396886
20	1562596251941
21	1562596456947
22	1562596945908
23	1562597871717
24	1562599477060
25	1562602811614
26	1562602981272
27	1562603156826
28	1562603677211
29	1562604134348
30	1562604408668
31	1562604422711
32	1562604440724
33	1562604742813
34	1562604755813
35	1562604810564
36	1562604836632
37	1562604847255
38	1562638753167
41	1562638841324
44	1562640410876
46	1562640451401
48	1562640553447
49	1562640640344
50	1562640642848
52	1562641840897
54	1562641944717
55	1562643464955
\.


--
-- Name: attribute_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY attribute_aud
    ADD CONSTRAINT attribute_aud_pkey PRIMARY KEY (attribute_id, rev);


--
-- Name: attribute_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY attribute
    ADD CONSTRAINT attribute_pkey PRIMARY KEY (attribute_id);


--
-- Name: company_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY company_aud
    ADD CONSTRAINT company_aud_pkey PRIMARY KEY (company_id, rev);


--
-- Name: company_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY company
    ADD CONSTRAINT company_pkey PRIMARY KEY (company_id);


--
-- Name: products_aud_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY products_aud
    ADD CONSTRAINT products_aud_pkey PRIMARY KEY (product_id, rev);


--
-- Name: products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- Name: revinfo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY revinfo
    ADD CONSTRAINT revinfo_pkey PRIMARY KEY (revision_number);


--
-- Name: fk3w3cy3erclfbf1ynt7hou07ni; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY products_aud
    ADD CONSTRAINT fk3w3cy3erclfbf1ynt7hou07ni FOREIGN KEY (rev) REFERENCES revinfo(revision_number);


--
-- Name: fk5frko5ebyla92lg3fqwvflugf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY products
    ADD CONSTRAINT fk5frko5ebyla92lg3fqwvflugf FOREIGN KEY (company_id) REFERENCES company(company_id);


--
-- Name: fk8aegcwn9i189d8wqxgrv1eb95; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY attribute
    ADD CONSTRAINT fk8aegcwn9i189d8wqxgrv1eb95 FOREIGN KEY (product_id) REFERENCES products(product_id);


--
-- Name: fke06y42hndltybxy3f3xfx9a7r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY attribute_aud
    ADD CONSTRAINT fke06y42hndltybxy3f3xfx9a7r FOREIGN KEY (rev) REFERENCES revinfo(revision_number);


--
-- Name: fkfxehufeh9tbk2yhg1v31tvkds; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY company_aud
    ADD CONSTRAINT fkfxehufeh9tbk2yhg1v31tvkds FOREIGN KEY (rev) REFERENCES revinfo(revision_number);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--


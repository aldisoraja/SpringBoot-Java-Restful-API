PGDMP                          |            kantor    13.13    13.13     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    19243    kantor    DATABASE     j   CREATE DATABASE kantor WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE kantor;
                postgres    false            �            1259    19267 	   addresses    TABLE     �   CREATE TABLE public.addresses (
    id text NOT NULL,
    contact_id text NOT NULL,
    street text,
    city text,
    country text NOT NULL,
    province text,
    postal_code text
);
    DROP TABLE public.addresses;
       public         heap    postgres    false            �            1259    19254    contacts    TABLE     �   CREATE TABLE public.contacts (
    id text NOT NULL,
    username text NOT NULL,
    first_name text NOT NULL,
    last_name text,
    email text,
    phone text
);
    DROP TABLE public.contacts;
       public         heap    postgres    false            �            1259    19244    users    TABLE     �   CREATE TABLE public.users (
    username text NOT NULL,
    password text NOT NULL,
    name text NOT NULL,
    token text,
    token_expired_at bigint
);
    DROP TABLE public.users;
       public         heap    postgres    false            �          0    19267 	   addresses 
   TABLE DATA           a   COPY public.addresses (id, contact_id, street, city, country, province, postal_code) FROM stdin;
    public          postgres    false    202   <       �          0    19254    contacts 
   TABLE DATA           U   COPY public.contacts (id, username, first_name, last_name, email, phone) FROM stdin;
    public          postgres    false    201   �       �          0    19244    users 
   TABLE DATA           R   COPY public.users (username, password, name, token, token_expired_at) FROM stdin;
    public          postgres    false    200   �       2           2606    19274    addresses addresses_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.addresses DROP CONSTRAINT addresses_pkey;
       public            postgres    false    202            0           2606    19261    contacts contacts_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.contacts DROP CONSTRAINT contacts_pkey;
       public            postgres    false    201            ,           2606    19251    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    200            .           2606    19253    users users_token_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_token_key UNIQUE (token);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_token_key;
       public            postgres    false    200            3           2606    19262    contacts fk    FK CONSTRAINT     q   ALTER TABLE ONLY public.contacts
    ADD CONSTRAINT fk FOREIGN KEY (username) REFERENCES public.users(username);
 5   ALTER TABLE ONLY public.contacts DROP CONSTRAINT fk;
       public          postgres    false    201    200    2860            4           2606    19275    addresses fk_contacts_addresses    FK CONSTRAINT     �   ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT fk_contacts_addresses FOREIGN KEY (contact_id) REFERENCES public.contacts(id);
 I   ALTER TABLE ONLY public.addresses DROP CONSTRAINT fk_contacts_addresses;
       public          postgres    false    201    2864    202            �   �   x���=R�0�Z>�/��v��k�H�4i���8���������5�{	2(-b�q(��B���Zc4�V����HU#�Dٌ����~��}��/��{=v�p/�r���
w�7����]��T���	!fR ��X�%��9��Դ�ԡ m����(a���f��oz��ٯO�4� �DI%      �   �   x�U�1
�0��9���mɖ��g��Erb��h��)�ty���CBRзd�@�d�j��Y@k���2\�s۟���8���鲎u�_b�DE�����\��YI�*e(X2dm)	�����~�?7eb	����{�3      �   �   x����  �3<E� ���C�e[kN�\�_��ku�|�����	SȤ`�=�� �M�,?7K���໸m�ih�*�_%�&=������H�����'NH�,�������|T:��� &Yk���(g�%��� �?&C     
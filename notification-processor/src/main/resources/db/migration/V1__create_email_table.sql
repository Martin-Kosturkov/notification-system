create table email(
    id bigserial primary key,
    template varchar(100),
    payload jsonb,
    recipient_id integer,
    inserted_at timestamp default now()
);

create index ix_email_inserted_at on email (inserted_at);
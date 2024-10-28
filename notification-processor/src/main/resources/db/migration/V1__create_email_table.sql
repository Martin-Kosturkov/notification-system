create table email(
    id bigserial primary key,
    template varchar(60) not null ,
    payload jsonb not null,
    recipient_id integer not null,
    inserted_at timestamp not null default now()
);

create index ix_email_inserted_at on email (inserted_at);
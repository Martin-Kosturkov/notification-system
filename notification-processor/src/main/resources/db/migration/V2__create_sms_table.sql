create table sms(
    id bigserial primary key,
    message varchar(300) not null ,
    recipient_id integer not null,
    inserted_at timestamp not null default now()
);

create index ix_sms_inserted_at on sms (inserted_at);
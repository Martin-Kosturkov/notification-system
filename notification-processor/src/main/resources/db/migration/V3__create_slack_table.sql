create table slack(
    id bigserial primary key,
    message varchar(1000) not null,
    org_id integer not null,
    channel_name varchar(60) not null,
    inserted_at timestamp not null default now()
);

create index ix_slack_inserted_at on slack (inserted_at);
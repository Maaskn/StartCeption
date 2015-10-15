CREATE SCHEMA StartCeptionSchema;

CREATE TABLE StartCeptionSchema.Client(EMAIL VARCHAR(255), PASSWORD VARCHAR(255), PRIMARY KEY(EMAIL));

INSERT INTO StartCeptionSchema.Client values ('admin@admin', 'admin');
CREATE SCHEMA StartCeption;

CREATE TABLE StartCeption.Client(EMAIL VARCHAR(255), PASSWORD VARCHAR(255), PRIMARY KEY(EMAIL));

--Samma sak som ('admin@admin', 'admin')
INSERT INTO StartCeption.Client values ('acaef577424cdceb96c77ee0528c95398154cedefb73a9a3efc84e1b295654', '2ee4c8ab41cd0cc9a91dabe674bca033ee7c8a4f6ee0dff7a93a47ca7e140');
CREATE TABLE IF NOT EXISTS team(id integer, name varchar, primary key (id));
CREATE TABLE IF NOT EXISTS campaign(id integer, name varchar, start_effective_date date, end_effective_date date, id_team integer, create_at timestamp, update_at timestamp, primary key (id));
CREATE TABLE IF NOT EXISTS campaign_subscription(id integer, id_campaign integer, id_team integer, id_club_supporter integer, create_at timestamp, update_at timestamp, primary key (id));
CREATE TABLE IF NOT EXISTS club_supporter(id integer, name varchar, email varchar, birth_date date, id_team integer, active boolean, create_at timestamp, update_at timestamp, primary key (id));

CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1;

INSERT INTO team VALUES (1, 'Flamengo')ON CONFLICT (id) DO NOTHING;
INSERT INTO team VALUES (2, 'Palmeiras') ON CONFLICT (id) DO NOTHING;
INSERT INTO team VALUES (3, 'Corinthians') ON CONFLICT (id) DO NOTHING;
INSERT INTO team VALUES (4, 'Santos') ON CONFLICT (id) DO NOTHING;
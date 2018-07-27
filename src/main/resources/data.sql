-- roles table  population
INSERT INTO roles
VALUES (1,'user')
ON CONFLICT (id)
DO UPDATE SET value = 'user';

INSERT INTO roles
VALUES (2,'admin')
ON CONFLICT (id)
DO UPDATE SET value = 'admin';

INSERT INTO roles
VALUES (3,'anonymous')
ON CONFLICT (id)
DO UPDATE SET value = 'anonymous';

-- users table  population
INSERT INTO users
VALUES (1,'password', 'Alex')
ON CONFLICT (id)
DO NOTHING;

INSERT INTO users
VALUES (2,'pwd123', 'Eugene')
ON CONFLICT (id)
DO NOTHING;

INSERT INTO users
VALUES (3,'123password', 'John')
ON CONFLICT (id)
DO NOTHING;